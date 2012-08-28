package eu.tjenwellens.bss.serverVSclient.client;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.serverVSclient.client.mvc.ServerToModel;
import eu.tjenwellens.bss.serverVSclient.communication.Command;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Bank;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Decorate;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Engage;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Idle;
import eu.tjenwellens.bss.serverVSclient.communication.dataToServer.Walk;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.CloseStream;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.DoLogin;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.DoQuickplay;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.DoSave;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.DoSignup;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.Err;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.InitStream;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.Logout;
import eu.tjenwellens.bss.serverVSclient.communication.init_exit.OK;

/**
 *
 * @author tjen
 */
public class ClientMessager extends ServerHandler implements Communication
{
    private ServerToModel model;

    public ClientMessager(ServerToModel model, String HOST)
    {
        super(HOST);
        this.model = model;
    }

    //<editor-fold defaultstate="collapsed" desc="actions">
    @Override
    public void bank(Transaction transaction, int diamonds, Item item)
    {
        send(new Bank(transaction, diamonds, item));
    }

    @Override
    public void chooseWeapon(Weapon weapon)
    {
        send(new ChooseWeapon(weapon));
    }

    @Override
    public void decorate(Decoration d, int row, int col, Tool t)
    {
        send(new Decorate(d, row, col, t));
    }

    @Override
    public void engage(String opponentName)
    {
        send(new Engage(opponentName));
    }

    @Override
    public void idle()
    {
        send(new Idle());
    }

    @Override
    public void walk(int x, int y)
    {
        send(new Walk(new Position(x, y)));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="init actions">
    @Override
    public void login(String name, String pass, String playerName, String factionName, int x, int y)
    {
        send(new DoLogin(name, pass, playerName, factionName, new Position(x, y)));
    }

    @Override
    public void logout()
    {
        send(new Logout());
    }

    @Override
    public void quickplay(String playerName, String factionName, int x, int y)
    {
        send(new DoQuickplay(playerName, factionName, new Position(x, y)));
    }

    @Override
    public void signup(String name, String pass, String playerName)
    {
        send(new DoSignup(name, pass, playerName));
    }

    @Override
    public void save(String name, String pass, String playerName)
    {
        send(new DoSave(name, pass, playerName));
    }
    //</editor-fold>

    @Override
    protected void handleInput(Command command)
    {
        if (command instanceof InitStream)
        {
            // init done
        }
        if (command instanceof CloseStream)
        {
            end();
        } else if (command instanceof Err)
        {
            System.out.println("Err: " + command);
        } else if (command instanceof OK)
        {
            System.out.println("Ok: " + command);
        } else
        {
            System.out.println("ERROR: command not recognized: " + command);
        }
    }

    @Override
    protected void handleData(DataForClient data)
    {
        model.updateData(data);
    }
}
