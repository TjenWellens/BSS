package eu.tjenwellens.bss.data.commands.init_exit;

import eu.tjenwellens.bss.data.commands.Command;

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
