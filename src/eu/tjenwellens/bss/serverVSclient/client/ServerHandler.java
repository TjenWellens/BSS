package eu.tjenwellens.bss.serverVSclient.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Tjen
 */
public abstract class ServerHandler
{
    private static final int PORT = 1234;     // server details
    private String HOST = "127.0.0.1";
    private Socket sock;
    // Streams
    private PrintWriter out;
    private BufferedReader in;
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

    private void update()
    {
        if (this.linkOpen)
        {
            try
            {
                String line;
                while ((line = in.readLine()) != null)
                {
                    interpretLine(line);
                }
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
            out = new PrintWriter(sock.getOutputStream(), true);
            out.println("connect");
            out.flush();
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
        out.println("logout");    // tell server that client is disconnecting
        out.flush();
        System.out.println("CLIENT: logout sent");
        try
        {
            sock.close();
            linkOpen = false;
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    protected abstract void interpretLine(String line);

    protected void send(String message)
    {
        if (this.linkOpen)
        {
            out.println(message);
            out.flush();
        }
    }
}
