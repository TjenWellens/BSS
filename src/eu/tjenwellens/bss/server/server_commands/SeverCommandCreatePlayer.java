package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author tjen
 */
public class SeverCommandCreatePlayer extends ServerCommand
{
    private String playerName;
    private String factionName;
    private Position position;

    public SeverCommandCreatePlayer(CommandReceiver cr, int playerID, String playerName, String factionName, Position position)
    {
        super(cr, playerID);
        this.playerName = playerName;
        this.factionName = factionName;
        this.position = position;
    }

    @Override
    public void execute()
    {
        cr.createPlayerCommand(playerID, playerName, factionName, position);
    }

    @Override
    public String toString()
    {
        return "CreatePlayerCommand: player" + playerID + " named " + playerName + " joined " + factionName;
    }
}
