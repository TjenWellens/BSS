package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.factions.Faction;

/**
 *
 * @author tjen
 */
public interface GetTile
{
    Faction getFaction();

    boolean isWalled();

    int getRow();

    int getCol();
}
