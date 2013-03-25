package eu.tjenwellens.bss.server.communication.client;

import eu.tjenwellens.bss.server.communication.input.Input;
import eu.tjenwellens.bss.server.communication.output.Output;
import eu.tjenwellens.update.Updatable;
import eu.tjenwellens.update.Updater;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Tjen
 */
public class ClientListener extends Thread
{
    private static final int DEFAULT_PORT = 1234;
    private int port;
    private Updater updater;
    private Input input;
    private Output output;

    public ClientListener(int port, Updater updater, Input input, Output output)
    {
        if (port < 0)
        {
            this.port = DEFAULT_PORT;
        } else
        {
            this.port = port;
        }
        this.updater = updater;
        this.input = input;
        this.output = output;
    }

    public void disconnectClientHandler(Updatable updatableClientHandler)
    {
        updater.removeUpdatable(updatableClientHandler);
    }

    @Override
    public void run()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket;
            String clientAddress;
            while (true)
            {
                System.out.println("SERVER: Waiting for a client...");
                clientSocket = serverSocket.accept();
                clientAddress = clientSocket.getInetAddress().getHostAddress();
                ClientHandler ch = new ClientHandlerMessenger(clientSocket, clientAddress, this, input, output);
                updater.registerUpdatable(ch);
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
