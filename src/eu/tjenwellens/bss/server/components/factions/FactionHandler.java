package eu.tjenwellens.bss.server.components.factions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tjen
 */
public class FactionHandler implements FactionHandlerInterface
{
    private Faction nullFaction = new Faction(0, "None", Kleur.WIT);
    private HashMap<Integer, Faction> factions = new HashMap<>();
    private HashMap<String, Integer> factionIds = new HashMap<>();
    private int factionCounter = 1;

    public FactionHandler()
    {
        factions.put(0, nullFaction);
        factionIds.put(nullFaction.getFactionName(), nullFaction.getFactionId());
    }

    @Override
    public int addFaction(String factionName, Kleur kleur)
    {
        if (factionIds.containsKey(factionName.toLowerCase()))
        {
            return -1;
        } else if (factionName.equalsIgnoreCase(nullFaction.getFactionName()))
        {
            return 0;
        } else
        {
            factions.put(factionCounter, new Faction(factionCounter, factionName.toLowerCase(), kleur));
            factionIds.put(factionName.toLowerCase(), factionCounter);
            factionCounter++;
            return factionCounter;
        }
    }

    @Override
    public boolean joinFaction(String factionName, FactionPlayer player)
    {
        if (nullFaction.getFactionName().equalsIgnoreCase(factionName))
        {
            return false;
        }
        if (factionIds.containsKey(factionName))
        {
            Faction faction = factions.get(factionIds.get(factionName));
            return faction.addMember(player);
        }
        return false;
    }

    @Override
    public Faction getNullFaction()
    {
        return nullFaction;
    }

    @Override
    public synchronized List<Faction> getFactionsCopy()
    {
        return new ArrayList<>(factions.values());
    }

    @Override
    public Faction getFactionByName(String factionName)
    {
        return factions.get(factionIds.get(factionName));
    }
}
