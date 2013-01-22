package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author tjen
 */
public class SeverCommandWalk extends ServerCommand
{
    private Position destination;

    public SeverCommandWalk(CommandReceiver cr, int playerID, Position destination)
    {
        super(cr, playerID);
        this.destination = destination;
    }

    @Override
    public void execute()
    {
        cr.walkCommand(playerID, destination);
    }

    @Override
    public String toString()
    {
        return "WalkCommand: player" + playerID + " moves to " + destination;
    }
}
