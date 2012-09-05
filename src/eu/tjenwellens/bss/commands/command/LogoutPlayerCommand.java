package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class LogoutPlayerCommand extends Command
{
    public LogoutPlayerCommand(CommandReceiverInterface cr, int playerID)
    {
        super(cr, playerID);
    }

    @Override
    public void execute()
    {
        cr.logoutPlayerCommand(playerID);
    }

    @Override
    public String toString()
    {
        return "IdleCommand: player" + playerID + " is logging out";
    }
}
