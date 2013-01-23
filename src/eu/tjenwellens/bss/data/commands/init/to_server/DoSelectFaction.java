package eu.tjenwellens.bss.data.commands.init.to_server;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class DoSelectFaction extends DoInit
{
    private String factionName;

    public DoSelectFaction(String factionName)
    {
        this.factionName = factionName;
    }

    public String getFactionName()
    {
        return factionName;
    }
}
