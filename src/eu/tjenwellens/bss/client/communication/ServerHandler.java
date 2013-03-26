package eu.tjenwellens.bss.client.communication;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.exit.CloseStream;
import eu.tjenwellens.bss.data.commands.init.InitStream;
import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.update.Updatable;
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
    // server details
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 1234;
    private int port;
    private String host;
    private Socket sock;
    // Streams
    private ObjectInputStream in;     // i/o for the client
    private BufferedInputStream checkForInputStream;        // to chech if object in 'in'
    private ObjectOutputStream out;
//    private PrintWriter out;
//    private BufferedReader in;
    // end
    private volatile boolean linkOpen = false;

    protected ServerHandler(String host, int port)
    {
        if (host == null)
        {
            this.host = DEFAULT_HOST;
        } else
        {
            this.host = host;
        }
        if (port < 0)
        {
            this.port = DEFAULT_PORT;
        } else
        {
            this.port = port;
        }
        makeContact(this.host, this.port);
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
                    if (leesObject == null)
                    {
                        System.out.println("ERROR: Client - Serverhandler - leesobject is null");
                    } else if (leesObject instanceof Command)
                    {
//                        System.out.println("Handle command");
                        handleInput((Command) leesObject);
                    } else if (leesObject instanceof DataForClient)
                    {
//                        System.out.println("Handle data");
                        DataForClient dfc = (DataForClient) leesObject;
                        if (leesObject != null)
                        {
                            handleData(dfc);
                        } else
                        {
                            System.out.println("ERROR: Client - Serverhandler - leesobject is null 2");
                        }
                    } else
                    {
                        System.out.println("Error: not a command: " + leesObject);
                    }
                }
                // handle output?
            } catch (IOException | ClassNotFoundException e)
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
            send(new InitStream());
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
            send(new CloseStream());
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
                } catch (IOException e)
                {
                    System.out.println(e);
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
            System.out.println("Command: " + command);
        }
    }

    protected abstract void handleInput(Command command);

    protected abstract void handleData(DataForClient data);
}
