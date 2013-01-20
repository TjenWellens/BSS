package eu.tjenwellens.bss.client.communication;

import eu.tjenwellens.bss.client.action.Transaction;
import eu.tjenwellens.bss.client.action.Decoration;
import eu.tjenwellens.bss.client.components.items.Item;
import eu.tjenwellens.bss.client.components.items.Tool;
import eu.tjenwellens.bss.client.components.items.Weapon;
import eu.tjenwellens.bss.client.mvc.ServerToModel;
import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.DataForClient;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.SDataItem;
import eu.tjenwellens.bss.data.commands.dataToServer.Bank;
import eu.tjenwellens.bss.data.commands.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.data.commands.dataToServer.Decorate;
import eu.tjenwellens.bss.data.commands.dataToServer.Engage;
import eu.tjenwellens.bss.data.commands.dataToServer.Idle;
import eu.tjenwellens.bss.data.commands.dataToServer.Walk;
import eu.tjenwellens.bss.data.commands.init_exit.CloseStream;
import eu.tjenwellens.bss.data.commands.init_exit.DoLogin;
import eu.tjenwellens.bss.data.commands.init_exit.DoQuickplay;
import eu.tjenwellens.bss.data.commands.init_exit.DoSave;
import eu.tjenwellens.bss.data.commands.init_exit.DoSignup;
import eu.tjenwellens.bss.data.commands.init_exit.Err;
import eu.tjenwellens.bss.data.commands.init_exit.InitStream;
import eu.tjenwellens.bss.data.commands.init_exit.Logout;
import eu.tjenwellens.bss.data.commands.init_exit.OK;

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
        String material = null;
        String tooltype = null;
        String weapontype = null;
        if (item instanceof Weapon)
        {
            Weapon w = (Weapon) item;
            material = w.getMaterial().name();
            weapontype = w.getWeaponType().name();
        } else if (item instanceof Tool)
        {
            Tool t = (Tool) item;
            material = t.getMaterial().name();
            tooltype = t.getToolType().name();
        }
        send(new Bank(transaction.name(), diamonds, new SDataItem(material, tooltype, weapontype)));
    }

    @Override
    public void chooseWeapon(Weapon weapon)
    {
        send(new ChooseWeapon(new SDataItem(weapon.getMaterial().name(), null, weapon.getWeaponType().name())));
    }

    @Override
    public void decorate(Decoration d, int row, int col, Tool t)
    {
        send(new Decorate(d.name(), row, col, new SDataItem(t.getMaterial().name(), t.getToolType().name(), null)));
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
        send(new Walk(x, y));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="init actions">
    @Override
    public void login(String name, String pass, String playerName, String factionName, int x, int y)
    {
        send(new DoLogin(name, pass, playerName, factionName, x, y));
    }

    @Override
    public void logout()
    {
        send(new Logout());
    }

    @Override
    public void quickplay(String playerName, String factionName, int x, int y)
    {
        send(new DoQuickplay(playerName, factionName, x, y));
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
        } else if (command instanceof CloseStream)
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
