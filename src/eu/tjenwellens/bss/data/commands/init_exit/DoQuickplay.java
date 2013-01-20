package eu.tjenwellens.bss.data.commands.init_exit;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class DoQuickplay extends Command
{
    private String playerName;
    private String factionName;
    private int xPosition;
    private int yPosition;

    public DoQuickplay(String playerName, String factionName, int xPosition, int yPosition)
    {
        this.playerName = playerName;
        this.factionName = factionName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public String getFactionName()
    {
        return factionName;
    }

    public int getXPosition()
    {
        return xPosition;
    }

    public int getYPosition()
    {
        return yPosition;
    }
}
