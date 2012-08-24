package eu.tjenwellens.bss.factions;

import eu.tjenwellens.bss.actionhandlers.ActionPlayer;

/**
 *
 * @author tjen
 */
public interface FactionPlayer extends ActionPlayer
{
    Faction getFaction();

    void setFaction(Faction faction);
}
