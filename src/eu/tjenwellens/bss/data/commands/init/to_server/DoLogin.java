package eu.tjenwellens.bss.data.commands.init.to_server;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class DoLogin extends DoInit
{
    private String name;
    private String password;

    public DoLogin(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }
}
