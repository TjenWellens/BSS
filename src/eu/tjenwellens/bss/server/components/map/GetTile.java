package eu.tjenwellens.bss.server.components.map;

import eu.tjenwellens.bss.server.components.factions.Faction;

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
