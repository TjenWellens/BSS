package eu.tjenwellens.bss.serverVSclient.communication.init;

import eu.tjenwellens.bss.Position;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class DoQuickplay implements Serializable
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
