package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.factions.Faction;

/**
 *
 * @author Tjen
 */
public class SDataFaction implements DataFaction
{
    private String name;

    public SDataFaction(Faction faction)
    {
        this.name = faction.getFactionName();
    }

    @Override
    public String getName()
    {
        return name;
    }
}
