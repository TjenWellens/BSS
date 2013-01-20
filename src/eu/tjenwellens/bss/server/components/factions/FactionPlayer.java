package eu.tjenwellens.bss.server.components.factions;

import eu.tjenwellens.bss.server.actions.ActionPlayer;

/**
 *
 * @author tjen
 */
public interface FactionPlayer extends ActionPlayer
{
    Faction getFaction();

    void setFaction(Faction faction);
}
