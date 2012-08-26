package eu.tjenwellens.bss.serverVSclient.communication.init;

import eu.tjenwellens.bss.Position;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class DoLogin implements Serializable
{
    private String name;
    private String pass;
    private String playerName;
    private String factionName;
    private Position position;

    public DoLogin(String name, String pass, String playerName, String factionName, Position position)
    {
        this.name = name;
        this.pass = pass;
        this.playerName = playerName;
        this.factionName = factionName;
        this.position = position;
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

    public Position getPosition()
    {
        return position;
    }
}
