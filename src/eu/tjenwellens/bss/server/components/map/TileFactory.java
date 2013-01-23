package eu.tjenwellens.bss.server.components.map;

import eu.tjenwellens.bss.server.components.factions.Faction;

/**
 *
 * @author tjen
 */
public abstract class TileFactory
{

    public static GetTile createTile(Faction faction, boolean walled, int row, int col)
    {
        return new Tile(faction, walled, row, col);
    }

    public static GetTile createTile(Faction faction, int row, int col)
    {
        return createTile(faction, false, row, col);
    }
}
