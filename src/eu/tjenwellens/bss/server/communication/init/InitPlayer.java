package eu.tjenwellens.bss.server.communication.init;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;

/**
 *
 * @author Tjen
 */
public class InitPlayer
{
    private int id;
    private String playerName;
    private Faction faction;
    private Position position;
    private int winns = -1;
    private int losses = -1;

    public InitPlayer(int id, String playerName)
    {
        this.id = id;
        this.playerName = playerName;
    }

    public int getId()
    {
        return id;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public Faction getFaction()
    {
        return faction;
    }

    public Position getPosition()
    {
        return position;
    }

    public int getWinns()
    {
        return winns;
    }

    public int getLosses()
    {
        return losses;
    }

    public void setWinns(int winns)
    {
        this.winns = winns;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public void setFaction(Faction faction)
    {
        this.faction = faction;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public boolean isWinnsSet()
    {
        return winns >= 0;
    }

    public boolean isLossesSet()
    {
        return losses >= 0;
    }

    public boolean isFactionSet()
    {
        return faction != null;
    }

    public boolean isPositionSet()
    {
        return position != null;
    }
}
