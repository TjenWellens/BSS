package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class LoadPlayerCommand extends Command
{
    private String playerName;
    private int winns;
    private int losses;
    private String factionName;
    private Position position;

    public LoadPlayerCommand(CommandReceiverInterface cr, int playerID, String playerName, int winns, int losses, String factionName, Position position)
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
