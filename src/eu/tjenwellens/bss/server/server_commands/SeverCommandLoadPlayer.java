package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class SeverCommandLoadPlayer extends ServerCommand
{
    private String playerName;
    private int winns;
    private int losses;
    private String factionName;
    private Position position;

    public SeverCommandLoadPlayer(CommandReceiverInterface cr, int playerID, String playerName, int winns, int losses, String factionName, Position position)
    {
        super(cr, playerID);
        this.playerName = playerName;
        this.winns = winns;
        this.losses = losses;
        this.factionName = factionName;
        this.position = position;
    }

    @Override
    public void execute()
    {
        cr.loadPlayerCommand(playerID, playerName, winns, losses, factionName, position);
    }

    @Override
    public String toString()
    {
        return "LoadPlayerCommand: player" + playerID + " is loading";
    }
}
