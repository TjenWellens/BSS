package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.communication.AccountHandler;
import eu.tjenwellens.bss.server.communication.DBAccountHandler;
import eu.tjenwellens.bss.server.communication.SimpleAccountHandler;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.database.DB;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;
import eu.tjenwellens.bss.server.server_commands.SeverCommandBank;
import eu.tjenwellens.bss.server.server_commands.SeverCommandChooseWeapon;
import eu.tjenwellens.bss.server.server_commands.SeverCommandDecorate;
import eu.tjenwellens.bss.server.server_commands.SeverCommandEngage;
import eu.tjenwellens.bss.server.server_commands.SeverCommandIdle;
import eu.tjenwellens.bss.server.server_commands.SeverCommandWalk;

/**
 *
 * @author tjen
 */
public class ConcreteInput implements Input
{
    private CommandInvoker ci;
    private CommandReceiver cr;
    private AccountHandler ah;

    public ConcreteInput(CommandInvoker commandInvoker, CommandReceiver commandReceiver)
    {
        this.ci = commandInvoker;
        this.cr = commandReceiver;
        if (DB.canConnect())
        {
            this.ah = new DBAccountHandler(ci, cr);
        } else
        {
            this.ah = new SimpleAccountHandler(ci, cr);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="...">
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="play commands">
    @Override
    public void bank(int playerID, Transaction transaction, int diamonds, Item item)
    {
        ci.addCommand(new SeverCommandBank(cr, playerID, transaction, diamonds, item));
    }

    @Override
    public void chooseWeapon(int playerID, Weapon weapon)
    {
        ci.addCommand(new SeverCommandChooseWeapon(cr, playerID, weapon));
    }

    @Override
    public void walk(int playerID, Position destination)
    {
        ci.addCommand(new SeverCommandWalk(cr, playerID, destination));
    }

    @Override
    public void engage(int playerID, String opponentName)
    {
        ci.addCommand(new SeverCommandEngage(cr, playerID, opponentName));
    }

    @Override
    public void decorate(int playerID, Decoration d, int row, int col, Tool t)
    {
        ci.addCommand(new SeverCommandDecorate(cr, playerID, d, row, col, t));
    }

    @Override
    public void idle(int playerID)
    {
        ci.addCommand(new SeverCommandIdle(cr, playerID));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="init commands">
    @Override
    public int fullinit(String name, String pass, String playerName, String factionName, Position position)
    {
        return ah.fullinit(name, pass, playerName, factionName, position);
    }

    @Override
    public int quickplay(String playerName, String factionName, Position position)
    {
        return ah.quickPlay(playerName, factionName, position);
    }

    @Override
    public boolean signup(String name, String pass, String playerName)
    {
        return ah.signup(name, pass, playerName);
    }

    @Override
    public int save(int playerID, String name, String pass, String playerName)
    {
        return ah.save(playerID, name, pass, playerName);
    }

    @Override
    public void logout(int playerID)
    {
        ah.logout(playerID);
    }
}
