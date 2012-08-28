package eu.tjenwellens.bss.serverVSclient.communication.init_exit;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class DoQuickplay extends Command
{
    private String playerName;
    private String factionName;
    private Position position;

    public DoQuickplay(String playerName, String factionName, Position position)
    {
        this.playerName = playerName;
        this.factionName = factionName;
        this.position = position;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public String getFactionName()
    {
        return factionName;
    }

    public Position getPosition()
    {
        return position;
    }
}
