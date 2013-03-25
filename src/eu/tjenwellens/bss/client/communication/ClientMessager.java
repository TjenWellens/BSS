package eu.tjenwellens.bss.client.communication;

import eu.tjenwellens.bss.client.action.Decoration;
import eu.tjenwellens.bss.client.action.Transaction;
import eu.tjenwellens.bss.client.components.items.Item;
import eu.tjenwellens.bss.client.components.items.Tool;
import eu.tjenwellens.bss.client.components.items.Weapon;
import eu.tjenwellens.bss.client.mvc.ServerToModel;
import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.SDataItem;
import eu.tjenwellens.bss.data.commands.exit.CloseStream;
import eu.tjenwellens.bss.data.commands.exit.Logout;
import eu.tjenwellens.bss.data.commands.init.InitStream;
import eu.tjenwellens.bss.data.commands.init.to_client.Err;
import eu.tjenwellens.bss.data.commands.init.to_client.OK;
import eu.tjenwellens.bss.data.commands.init.to_client.OKFullInit;
import eu.tjenwellens.bss.data.commands.init.to_client.OkPlay;
import eu.tjenwellens.bss.data.commands.init.to_server.DoLogin;
import eu.tjenwellens.bss.data.commands.init.to_server.DoPlay;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSelectFaction;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSelectPosition;
import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Bank;
import eu.tjenwellens.bss.data.commands.play.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Decorate;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Engage;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Idle;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Walk;

/**
 *
 * @author tjen
 */
public class ClientMessager extends ServerHandler implements Communication
{
    private ServerToModel model;

    public ClientMessager(ServerToModel model, String HOST, int port)
    {
        super(HOST, port);
        this.model = model;
    }

    //<editor-fold defaultstate="collapsed" desc="play">
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

    //<editor-fold defaultstate="collapsed" desc="init">
    @Override
    public void login(String name, String password)
    {
        send(new DoLogin(name, password));
    }

    @Override
    public void selectFaction(String factionName)
    {
        send(new DoSelectFaction(factionName));
    }

    @Override
    public void selectPosition(int x, int y)
    {
        send(new DoSelectPosition(x, y));
    }

    @Override
    public void play()
    {
        send(new DoPlay());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="exit">
    @Override
    public void logout()
    {
        send(new Logout());
    }
    //</editor-fold>

    @Override
    protected void handleInput(Command command)
    {
        if (command instanceof InitStream)
        {
            // init done
            System.out.println("StreamInit done");
        } else if (command instanceof CloseStream)
        {
            end();
        } else if (command instanceof Err)
        {
            System.out.println("Err: " + command);
        } else if (command instanceof OK)
        {
            System.out.println("Ok: " + command);
            if (command instanceof OKFullInit)
            {
                model.fullInitDone();
            } else if (command instanceof OkPlay)
            {
                model.fullInitDone();
            } else
            {
                // ignore
            }
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
