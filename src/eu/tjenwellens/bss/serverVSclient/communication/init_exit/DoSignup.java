package eu.tjenwellens.bss.serverVSclient.communication.init_exit;

import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class DoSignup extends Command
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
