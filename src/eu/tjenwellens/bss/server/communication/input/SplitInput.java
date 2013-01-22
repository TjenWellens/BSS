package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class SplitInput implements Input
{
    private PlayInput play;
    private InitInput init;

    public SplitInput(CommandInvoker commandInvoker, CommandReceiver commandReceiver)
    {
        play = new ConcretePlayInput(commandInvoker, commandReceiver);
//        this.ci = commandInvoker;
//        this.cr = commandReceiver;
//        if (DB.canConnect())
//        {
//            this.ah = new DBAccountHandler(ci, cr);
//        } else
//        {
//            this.ah = new SimpleAccountHandler(ci, cr);
//        }
    }

    @Override
    public void bank(int playerID, Transaction transaction, int diamonds, Item item)
    {
        play.bank(playerID, transaction, diamonds, item);
    }

    @Override
    public void chooseWeapon(int playerID, Weapon weapon)
    {
        play.chooseWeapon(playerID, weapon);
    }

    @Override
    public void decorate(int playerID, Decoration d, int row, int col, Tool t)
    {
        play.decorate(playerID, d, row, col, t);
    }

    @Override
    public void engage(int playerID, String opponentName)
    {
        play.engage(playerID, opponentName);
    }

    @Override
    public void idle(int playerID)
    {
        play.idle(playerID);
    }

    @Override
    public void walk(int playerID, Position destination)
    {
        play.walk(playerID, destination);
    }

    @Override
    public void logout(int playerID)
    {
        play.logout(playerID);
    }

    @Override
    public int fullinit(String name, String pass, String playerName, String factionName, Position position)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int quickplay(String playerName, String factionName, Position position)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean signup(String name, String pass, String playerName)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int save(int playerID, String name, String pass, String playerName)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
