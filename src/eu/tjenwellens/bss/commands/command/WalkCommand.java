package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class WalkCommand extends Command
{
    private Position destination;

    public WalkCommand(CommandReceiverInterface cr, int playerID, Position destination)
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
