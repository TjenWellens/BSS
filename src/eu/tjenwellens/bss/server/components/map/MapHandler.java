package eu.tjenwellens.bss.server.components.map;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.server.actions.decorateAction.DecoratePlayer;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;

/**
 *
 * @author tjen
 */
public class MapHandler implements MapHandlerInterface, GameConstants
{
    private MapI map;
    private int tileWidth;
    private int tileHeight;

    public boolean isPositionInMap(Position p)
    {
        if (p == null)
        {
            return false;
        }
        if (p.getX() < 0)
        {
            return false;
        }
        if (p.getX() > MAP_WIDTH)
        {
            return false;
        }
        if (p.getY() < 0)
        {
            return false;
        }
        if (p.getY() > MAP_HEIGHT)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "MapHandler{" + "rows=" + map.getRows() + ", cols=" + map.getCols() + ", tileWidth=" + tileWidth + ", tileHeight=" + tileHeight + ", tiles=" + map.toString() + '}';
    }

    public MapHandler(Faction noFaction)
    {
        // setup map
        int rows = MAP_ROWS;
        int cols = MAP_COLUMNS;
        tileWidth = (int) (MAP_WIDTH / rows);
        tileHeight = (int) (MAP_HEIGHT / cols);
        GetTile[][] tiles = new GetTile[rows][cols];
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                tiles[row][col] = TileFactory.createTile(noFaction, row, col);
            }
        }
        tiles[3][3] = TileFactory.createTile(noFaction, true, 3, 3);
        tiles[3][4] = TileFactory.createTile(noFaction, true, 3, 4);
        tiles[3][5] = TileFactory.createTile(noFaction, true, 3, 5);
        tiles[5][3] = TileFactory.createTile(noFaction, true, 5, 3);
        tiles[4][5] = TileFactory.createTile(noFaction, true, 4, 5);
        tiles[5][4] = TileFactory.createTile(noFaction, true, 5, 4);
        tiles[5][5] = TileFactory.createTile(noFaction, true, 5, 5);
        // init map
        map = new Map(tiles);
    }

    @Override
    public boolean build(DecoratePlayer player, int row, int col)
    {
        if (!map.isPositionInMap(row, col))
        {
            System.out.println("ERROR: Wall not built, position not in map: ");
            return false;
        }
        Faction playerFaction = player.getFaction();
        if (playerFaction == null)
        {
            System.out.println("ERROR: Wall not built, playerFaction null: " + player);
            return false;
        }
        GetTile tile = getTile(row, col);
        Faction tileFaction = tile.getFaction();

        if (!playerFaction.equals(tileFaction))
        {
            System.out.println("ERROR: Wall not built, wrong faction: " + playerFaction);
            return false;
        }
        if (tile.isWalled())
        {
            System.out.println("ERROR: Wall not built, allready walled: ");
            return false;
        }
        // all good
        map.setTile(TileFactory.createTile(tileFaction, true, col, row));
        System.out.println("Wall built: ");
        return true;
    }

    @Override
    public boolean paint(DecoratePlayer player, int row, int col)
    {
        Faction playerFaction = player.getFaction();
        if (playerFaction == null)
        {
            return false;
        }
        if (!map.isPositionInMap(row, col))
        {
            System.out.println("ERROR: Tile not painted, position not in map: ");
            return false;
        }
        GetTile tile = getTile(row, col);
        if (tile.isWalled())
        {
            System.out.println("ERROR: Tile not painted, allready walled: ");
            return false;
        }
        map.setTile(TileFactory.createTile(playerFaction, false, col, row));
        System.out.println("Tile painted: ");
        return true;
    }

    @Override
    public boolean destroy(DecoratePlayer player, int row, int col)
    {
        Faction playerFaction = player.getFaction();
        if (playerFaction == null)
        {
            return false;
        }
        if (!map.isPositionInMap(row, col))
        {
            System.out.println("ERROR: Wall not destroyed, position not in map: ");
            return false;
        }
        GetTile tile = getTile(row, col);
        if (!tile.isWalled())
        {
            System.out.println("ERROR: Wall not destroyed, no wall to destroy:");
            return false;
        }
        map.setTile(TileFactory.createTile(tile.getFaction(), false, col, row));
        System.out.println("Wall destroyed");
        return true;
    }

    @Override
    public GetMap getMapCopy()
    {
        return map.getCopy();
    }

    @Override
    public GetTile getTile(int row, int col)
    {
        return map.getTile(row, col);
    }
}
