package eu.tjenwellens.bss.data.commands.init.to_server;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class DoFullInit extends Command
{
    private String name;
    private String pass;
    private String playerName;
    private String factionName;
    private int xPosition;
    private int yPosition;

    public DoFullInit(String name, String pass, String playerName, String factionName, int xPosition, int yPosition)
    {
        this.name = name;
        this.pass = pass;
        this.playerName = playerName;
        this.factionName = factionName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public String getName()
    {
        return name;
    }

    public String getPass()
    {
        return pass;
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
