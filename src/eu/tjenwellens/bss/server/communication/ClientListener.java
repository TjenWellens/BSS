package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.observer.Updatable;
import eu.tjenwellens.bss.server.observer.Updater;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Tjen
 */
public class ClientListener extends Thread
{
    private static final int PORT = 1234;
    private Updater updater;
    private InputInterface input;
    private OutputInterface output;

    public ClientListener(Updater updater, InputInterface input, OutputInterface output)
    {
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
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket;
            String clientAddress;
            while (true)
            {
                System.out.println("SERVER: Waiting for a client...");
                clientSocket = serverSocket.accept();
                clientAddress = clientSocket.getInetAddress().getHostAddress();
                ClientHandler ch = new ClientHandler(clientSocket, clientAddress, this, input, output);
                updater.addUpdatable(ch);
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
