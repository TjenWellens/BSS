package eu.tjenwellens.bss.serverVSclient.communication.init;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class DoSignup implements Serializable
{
    private String name;
    private String pass;
    private String playerName;

    public DoSignup(String name, String pass, String playerName)
    {
        this.name = name;
        this.pass = pass;
        this.playerName = playerName;
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
