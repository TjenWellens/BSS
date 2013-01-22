package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;
import eu.tjenwellens.bss.server.server_commands.SeverCommandBank;
import eu.tjenwellens.bss.server.server_commands.SeverCommandChooseWeapon;
import eu.tjenwellens.bss.server.server_commands.SeverCommandDecorate;
import eu.tjenwellens.bss.server.server_commands.SeverCommandEngage;
import eu.tjenwellens.bss.server.server_commands.SeverCommandIdle;
import eu.tjenwellens.bss.server.server_commands.SeverCommandLogoutPlayer;
import eu.tjenwellens.bss.server.server_commands.SeverCommandWalk;

/**
 *
 * @author Tjen
 */
public class ConcretePlayInput implements PlayInput
{
    private CommandInvoker ci;
    private CommandReceiver cr;

    public ConcretePlayInput(CommandInvoker ci, CommandReceiver cr)
    {
        this.ci = ci;
        this.cr = cr;
    }
    //<editor-fold defaultstate="collapsed" desc="...">
    //</editor-fold>

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

    @Override
    public void logout(int playerID)
    {
        ci.addCommand(new SeverCommandLogoutPlayer(cr, playerID));
    }

}
