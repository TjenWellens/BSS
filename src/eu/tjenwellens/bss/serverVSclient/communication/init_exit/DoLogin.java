package eu.tjenwellens.bss.serverVSclient.communication.init_exit;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class DoLogin extends Command
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
