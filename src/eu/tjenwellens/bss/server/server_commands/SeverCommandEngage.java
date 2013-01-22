package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author tjen
 */
public class SeverCommandEngage extends ServerCommand
{
    private String opponentName;

    public SeverCommandEngage(CommandReceiver cr, int playerID, String opponentName)
    {
        super(cr, playerID);
        this.opponentName = opponentName;
    }

    @Override
    public void execute()
    {
        cr.engageCommand(playerID, opponentName);
    }

    @Override
    public String toString()
    {
        return "EngageCommand: player" + playerID + " engages " + opponentName;
    }
}
