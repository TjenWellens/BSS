package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class SeverCommandLogoutPlayer extends ServerCommand
{
    public SeverCommandLogoutPlayer(CommandReceiverInterface cr, int playerID)
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
