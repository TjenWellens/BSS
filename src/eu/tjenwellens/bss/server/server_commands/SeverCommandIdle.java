package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author tjen
 */
public class SeverCommandIdle extends ServerCommand
{
    public SeverCommandIdle(CommandReceiver cr, int playerID)
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
