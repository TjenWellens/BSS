package eu.tjenwellens.bss.server.communication.client;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.exit.CloseStream;
import eu.tjenwellens.bss.data.commands.init.InitStream;
import eu.tjenwellens.update.Updatable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Tjen
 */
public abstract class ClientHandler implements Updatable
{
    // serverside communication
    private ClientListener clientListener;
    // socket
    private Socket clientSocket;
    private String clientAddress;
    // Streams
    private ObjectOutputStream out;
    private BufferedInputStream checkForInputStream;
    private ObjectInputStream in;
    // end
    private volatile boolean linkOpen = false;
    private int socketErrorCounter = 0;
    private int maxSockErrors = 5;
    // state: 
    // not inited   = no init received
    // inited       = init received && playerid == 0
    // logged in    = init received && playerid != 0
    private boolean streamInitDone = false;

    ClientHandler(Socket clientSocket, String clientAddress, ClientListener clientListener)
    {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.clientListener = clientListener;
        initSocket();
    }

    @Override
    public void update()
    {
        if (!this.linkOpen)
        {
            System.out.println("Link closed for a client");
        }

        // input
        manageInput();

        // output
        manageOutput();

    }

    private void manageInput()
    {
        try
        {
            // loop over all incomming messages, if none in que, continue
            while (linkOpen && this.checkForInputStream.available() > 0)
            {
                Object leesObject = read();
                if (leesObject instanceof CloseStream)
                {
                    // other side will be closed
                    System.out.println("Close request received");
                    end();
                } else if (leesObject instanceof InitStream)
                {
                    // connection is made
                    System.out.println("Init request received");
                    streamInitDone = true;
                } else if (!streamInitDone)
                {
                    // need to init first
                    System.out.println("ERROR: need to init stream" + leesObject);
                } else if (leesObject instanceof Command)
                {
                    manageInput((Command) leesObject);
                } else
                {
                    System.out.println("ERROR: Object not expected" + leesObject);
                }
            }
        } catch (IOException e)
        {
            handleIOException(e);
        }
    }

    private void initSocket()
    {
        try
        {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            send(new InitStream());
            checkForInputStream = new BufferedInputStream(clientSocket.getInputStream());   // to check if object in 'in'
            in = new ObjectInputStream(checkForInputStream);
            this.linkOpen = true;
        } catch (IOException e)
        {
            handleIOException(e);
        }
    }

    protected void end()
    {
        clientListener.disconnectClientHandler(this);
        closeSocket();
    }

    private void closeSocket()
    {
        exit();
        try
        {
            out.writeObject(new CloseStream());
            out.flush();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            clientSocket.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
        System.out.println("SERVER_CLIENT: Client (" + clientAddress + ") connection closed\n");
        this.linkOpen = false;
        clientListener.disconnectClientHandler(this);
    }

    protected void send(Serializable command)
    {
        try
        {
            out.writeObject(command);
            out.flush();
        } catch (IOException e)
        {
            handleIOException(e);
        }
    }

    private Object read()
    {
        try
        {
            Object o = this.in.readObject();
            return o;
        } catch (IOException | ClassNotFoundException e)
        {
            handleIOException(e);
        }
        return null;
    }

    private void handleIOException(Exception e)
    {
        if (e instanceof SocketException)
        {
            System.out.println("Errors: " + socketErrorCounter + " out of " + maxSockErrors);
            socketErrorCounter++;
        }
        if (socketErrorCounter > maxSockErrors)
        {
            end();
        } else
        {
            System.out.println("continuing times: " + (maxSockErrors - socketErrorCounter));
        }
        System.out.println(e);
    }

    protected abstract void manageInput(Command command);

    protected abstract void manageOutput();

    protected void exit()
    {
    }
}
