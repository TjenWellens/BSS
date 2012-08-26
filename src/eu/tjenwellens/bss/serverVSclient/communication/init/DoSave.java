package eu.tjenwellens.bss.serverVSclient.communication.init;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class DoSave implements Serializable
{
    private int playerID;
    private String name;
    private String pass;
    private String playerName;

    public DoSave(int playerID, String name, String pass, String playerName)
    {
        this.playerID = playerID;
        this.name = name;
        this.pass = pass;
        this.playerName = playerName;
    }

    public int getPlayerID()
    {
        return playerID;
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
}
