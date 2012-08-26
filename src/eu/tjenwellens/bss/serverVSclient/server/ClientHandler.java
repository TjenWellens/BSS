package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.serverVSclient.Updatable;
import eu.tjenwellens.bss.serverVSclient.communication.CloseStream;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Bank;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Decorate;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Engage;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Idle;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Walk;
import eu.tjenwellens.bss.serverVSclient.communication.init.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Tjen
 */
public class ClientHandler implements Updatable
{
    // serverside communication
    private ClientListener clientListener;
    private InputInterface input;
    private OutputInterface output;
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
    private int maxSockErrors = 100;
    // player information
    private int playerId = -1;
    // state: 
    // not inited   = no init received
    // inited       = init received && playerid < 0
    // logged in    = init received && playerid > 0
    private boolean initReceived = false;

    ClientHandler(Socket clientSocket, String clientAddress, ClientListener clientListener, InputInterface input, OutputInterface output)
    {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.clientListener = clientListener;
        this.input = input;
        this.output = output;
        initSocket();
    }

    @Override
    public void update()
    {
        if (!this.linkOpen)
        {
            System.out.println("Link closed for a client");
        }
        try
        {
            // input
            manageInput();

            // output
            manageOutput();

        } catch (IOException e)
        {
            if (e instanceof SocketException)
            {
                socketErrorCounter++;
            }
            System.out.println("ClientHandler" + e);
            if (socketErrorCounter > maxSockErrors)
            {
                closeSocket();
            }
        } catch (ClassNotFoundException e)
        {
            System.out.println("ClientHandler" + e);
        }
    }

    private void manageInput() throws IOException, ClassNotFoundException
    {
        // loop over all incomming messages, if none in que, continue
        while (linkOpen && this.checkForInputStream.available() > 0)
        {
            Object leesObject = this.in.readObject();
            if (leesObject instanceof CloseStream)
            {
                // other side will be closed
                System.out.println("Close request received");
                this.closeSocket();
            } else if (leesObject instanceof InitStream)
            {
                // connection is made
                System.out.println("Init request received");
                initReceived = true;
            } else if (!initReceived)
            {
                // need to init first
                System.out.println("ERROR: need to init stream" + leesObject);
            } else if (playerId < 0)    // player needs to log in
            {
                if (leesObject instanceof DoLogin)
                {
                    DoLogin login = (DoLogin) leesObject;
                    playerId = input.login(login.getName(), login.getPass(), login.getPlayerName(), login.getFactionName(), login.getPosition());
                    if (playerId >= 0)
                    {
                        out.writeObject(new OKLogin());
                    } else
                    {
                        out.writeObject(new ErrLogin("err"));
                    }
                } else if (leesObject instanceof DoSignup)
                {
                    DoSignup signup = (DoSignup) leesObject;
                    boolean success = input.signup(signup.getName(), signup.getPass(), signup.getPlayerName());
                    if (success)
                    {
                        out.writeObject(new OkSignUp());
                    } else
                    {
                        out.writeObject(new ErrSignUp("err"));
                    }
                } else if (leesObject instanceof DoQuickplay)
                {
                    DoQuickplay quickplay = (DoQuickplay) leesObject;
                    playerId = input.quickplay(quickplay.getPlayerName(), quickplay.getFactionName(), quickplay.getPosition());
                    if (playerId >= 0)
                    {
                        out.writeObject(new OkQuickplay());
                    } else
                    {
                        out.writeObject(new ErrQuickplay("err"));
                    }
                } else
                {
                    System.out.println("ERROR: need to login" + leesObject);
                }
            } else if (playerId >= 0)    // player is logged in
            {
                if (leesObject instanceof Bank)
                {
                    Bank bank = (Bank) leesObject;
                    input.bank(playerId, bank.getTransaction(), bank.getDiamonds(), bank.getItem());
                } else if (leesObject instanceof ChooseWeapon)
                {
                    ChooseWeapon chooseWeapon = (ChooseWeapon) leesObject;
                    input.chooseWeapon(playerId, chooseWeapon.getWeapon());
                } else if (leesObject instanceof Decorate)
                {
                    Decorate decorate = (Decorate) leesObject;
                    input.decorate(playerId, decorate.getDecoration(), decorate.getPosition(), decorate.getTool());
                } else if (leesObject instanceof Engage)
                {
                    Engage engage = (Engage) leesObject;
                    input.engage(playerId, engage.getOpponentName());
                } else if (leesObject instanceof Idle)
                {
                    input.idle(playerId);
                } else if (leesObject instanceof Walk)
                {
                    Walk walk = (Walk) leesObject;
                    input.walk(playerId, walk.getPosition());
                } else if (leesObject instanceof DoSave)
                {
                    // quickplay -> save progress, cf signup, continue with a saveable account
                    DoSave save = (DoSave) leesObject;
                    int success = input.save(playerId, save.getName(), save.getPass(), save.getPlayerName());
                    if (success >= 0)
                    {
                        out.writeObject(new OkSignUp());
                        playerId = success;
                    } else
                    {
                        out.writeObject(new ErrSignUp("err"));
                    }
                } else if (leesObject instanceof Logout)
                {
                    input.logout(playerId);
                    closeSocket();
                } else
                {
                    System.out.println("ERROR: need to choose an action" + leesObject);
                }
            } else
            {
                System.out.println("ERROR: Object not expected" + leesObject);
            }
        }
    }

    private void manageOutput() throws IOException, ClassNotFoundException
    {
        if (playerId < 0)
        {
            return;
        }
        DataForClient dfc = output.getData(playerId);
        if (dfc == null)
        {
            return;
        }
        // all is ok
        out.writeObject(dfc);
        out.flush();
    }

    private void initSocket()
    {
        try
        {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(new InitStream());
            out.flush();
            checkForInputStream = new BufferedInputStream(clientSocket.getInputStream());   // to check if object in 'in'
            in = new ObjectInputStream(checkForInputStream);
            this.linkOpen = true;
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    protected void end()
    {
        clientListener.disconnectClientHandler(this);
        closeSocket();
    }

    private void closeSocket()
    {
        try
        {
            out.writeObject(new CloseStream());
            out.flush();
            clientSocket.close();
            System.out.println("SERVER_CLIENT: Client (" + clientAddress + ") connection closed\n");
            this.linkOpen = false;
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
