package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.commands.command.*;
import eu.tjenwellens.bss.database.DB;
import eu.tjenwellens.bss.mvc.controller.CommandInvokerInterface;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import eu.tjenwellens.bss.players.inventory.items.*;

/**
 *
 * @author tjen
 */
public class Input implements InputInterface
{
    private CommandInvokerInterface ci;
    private CommandReceiverInterface cr;
    private AccountHandler ah;

    public Input(CommandInvokerInterface commandInvoker, CommandReceiverInterface commandReceiver)
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

    @Override
    public void bank(int playerID, Transaction transaction, int diamonds, Item item)
    {
        ci.addCommand(new BankCommand(cr, playerID, transaction, diamonds, item));
    }

    @Override
    public void chooseWeapon(int playerID, Weapon weapon)
    {
        ci.addCommand(new ChooseWeaponCommand(cr, playerID, weapon));
    }

    @Override
    public void walk(int playerID, Position destination)
    {
        ci.addCommand(new WalkCommand(cr, playerID, destination));
    }

    @Override
    public void engage(int playerID, String opponentName)
    {
        ci.addCommand(new EngageCommand(cr, playerID, opponentName));
    }

    @Override
    public void decorate(int playerID, Decoration d, int row, int col, Tool t)
    {
        ci.addCommand(new DecorateCommand(cr, playerID, d, row, col, t));
    }

    @Override
    public void idle(int playerID)
    {
        ci.addCommand(new IdleCommand(cr, playerID));
    }

    @Override
    public int login(String name, String pass, String playerName, String factionName, Position position)
    {
        return ah.login(name, pass, playerName, factionName, position);
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
