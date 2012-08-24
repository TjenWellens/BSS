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

    public static GetTile createTile(Faction faction, boolean walled, int xPos, int yPos)
    {
        return new Tile(faction, walled, new Point(xPos, yPos));
    }

    public static GetTile createTile(Faction faction, int xPos, int yPos)
    {
        return createTile(faction, false, xPos, yPos);
    }
}
