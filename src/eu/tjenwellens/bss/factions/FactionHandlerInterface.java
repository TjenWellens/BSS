package eu.tjenwellens.bss.factions;

import java.util.List;

/**
 *
 * @author tjen
 */
public interface FactionHandlerInterface
{
    List<Faction> getFactionsCopy();

    public int addFaction(String factionName, Kleur kleur);

    public boolean joinFaction(String factionName, FactionPlayer player);

    Faction getNullFaction();

    public Faction getFactionByName(String factionName);
}
