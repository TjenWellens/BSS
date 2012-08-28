package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.factions.Faction;
import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author tjen
 */
public abstract class TileFactory
{
    private static HashMap<Point, GetTile[]> tiles = new HashMap<Point, GetTile[]>();

    public static GetTile createTile(Faction faction, boolean walled, int row, int col)
    {
        return new Tile(faction, walled, row, col);
    }

    public static GetTile createTile(Faction faction, int row, int col)
    {
        return createTile(faction, false, row, col);
    }
}
