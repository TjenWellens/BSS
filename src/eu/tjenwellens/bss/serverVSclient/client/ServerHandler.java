package eu.tjenwellens.bss.serverVSclient.client;

import eu.tjenwellens.bss.serverVSclient.Updatable;
import eu.tjenwellens.bss.serverVSclient.communication.CloseStream;
import eu.tjenwellens.bss.serverVSclient.communication.Command;
import eu.tjenwellens.bss.serverVSclient.communication.init.InitStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Tjen
 */
public abstract class ServerHandler implements Updatable
{
    private static final int PORT = 1234;     // server details
    private String HOST = "127.0.0.1";
    private Socket sock;
    // Streams
    private ObjectInputStream in;     // i/o for the client
    private BufferedInputStream checkForInputStream;        // to chech if object in 'in'
    private ObjectOutputStream out;
//    private PrintWriter out;
//    private BufferedReader in;
    // end
    private volatile boolean linkOpen = false;

    protected ServerHandler()
    {
        makeContact(HOST, PORT);
    }

    protected ServerHandler(String HOST)
    {
        System.out.println("Host initialized");
        this.HOST = HOST;
        makeContact(HOST, PORT);
    }

    @Override
    public void update()
    {
        if (this.linkOpen)
        {
            try
            {
                // handle input
                while (linkOpen && this.checkForInputStream.available() > 0)
                {
                    Object leesObject = this.in.readObject();
                    if (leesObject instanceof Command)
                    {
                        handleInput((Command) leesObject);
                    } else
                    {
                        System.out.println("Error: not a command: " + leesObject);
                    }
                }
                // handle output?
            } catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    private void makeContact(String host, int port)
    {
        if (sock != null && sock.isConnected())
        {
            closeLink();
        }
        try
        {
            // socket
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(new InitStream());
            out.flush();
            checkForInputStream = new BufferedInputStream(sock.getInputStream());
            in = new ObjectInputStream(checkForInputStream);
            linkOpen = true;
            System.out.println("CLIENT: contact established");

        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    protected void end()
    {
        closeLink();
    }

    private void closeLink()
    {
        try
        {
            out.writeObject(new CloseStream());    // tell server that client is disconnecting
            out.flush();
            System.out.println("CLIENT: logout sent");
            sock.close();
            linkOpen = false;
        } catch (IOException e)
        {
            System.out.println(e);
        } finally
        {
            if (sock != null)
            {
                try
                {
                    sock.close();
                } catch (IOException ex)
                {
                    System.out.println(ex);
                }
            }
        }
    }

    protected void send(Command command)
    {
        try
        {
            out.writeObject(command);
            out.flush();
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    protected abstract void handleInput(Command command);
}
