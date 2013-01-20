package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class SeverCommandIdle extends ServerCommand
{
    public SeverCommandIdle(CommandReceiverInterface cr, int playerID)
    {
        super(cr, playerID);
    }

    @Override
    public void execute()
    {
        cr.idleCommand(playerID);
    }

    @Override
    public String toString()
    {
        return "IdleCommand: player" + playerID + " goes Idle";
    }
}
