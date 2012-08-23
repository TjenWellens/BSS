/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.map;

import java.util.Arrays;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecoratePlayer;
import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.factions.Faction;

/**
 *
 * @author tjen
 */
public class MapHandler implements MapHandlerInterface, GameConstants
{

    private int rows = MAP_ROWS;
    private int cols = MAP_COLUMNS;
    private int tileWidth = (int) (MAP_WIDTH / rows);
    private int tileHeight = (int) (MAP_HEIGHT / cols);
    private GetTile[][] tiles = new GetTile[rows][cols];

    @Override
    public String toString()
    {
        return "MapHandler{" + "rows=" + rows + ", cols=" + cols + ", tileWidth=" + tileWidth + ", tileHeight=" + tileHeight + ", tiles=" + Arrays.toString(tiles) + '}';
    }

    public MapHandler(Faction noFaction)
    {
        for (int row = 0; row < tiles.length; row++)
        {
            for (int col = 0; col < tiles[row].length; col++)
            {
                tiles[row][col] = TileFactory.createTile(noFaction, col, row);
            }
        }
        tiles[3][3] = TileFactory.createTile(noFaction, true, 3, 3);
        tiles[3][4] = TileFactory.createTile(noFaction, true, 4, 3);
        tiles[3][5] = TileFactory.createTile(noFaction, true, 3, 5);
        tiles[5][3] = TileFactory.createTile(noFaction, true, 3, 5);
        tiles[4][5] = TileFactory.createTile(noFaction, true, 5, 4);
        tiles[5][4] = TileFactory.createTile(noFaction, true, 4, 5);
        tiles[5][5] = TileFactory.createTile(noFaction, true, 5, 5);
    }

    @Override
    public boolean build(DecoratePlayer player, Position tilePosition)
    {
        int row = (int) Math.floor(tilePosition.getY() / tileWidth);
        int col = (int) Math.floor(tilePosition.getX() / tileHeight);

        if (0 < row && row < rows && 0 < col && col < cols)
        {
            GetTile tile = tiles[row][col];
            Faction playerFaction = player.getFaction();
            Faction tileFaction = tile.getFaction();
            if (playerFaction != null && playerFaction.equals(tileFaction))
            {
                if (!tile.isWalled())
                {
                    tiles[row][col] = TileFactory.createTile(tileFaction, true, col, row);
                    System.out.println("Wall built: " + tilePosition);
                    return true;
                } else
                {
                    System.out.println("ERROR: Wall not built, allready walled: " + tilePosition);
                }
            } else
            {
                System.out.println("ERROR: Wall not built, tile of wrong faction: " + playerFaction);
            }
        } else
        {
            System.out.println("ERROR: Wall not built, wrong position: " + tilePosition);
        }

        return false;
    }

    @Override
    public boolean paint(DecoratePlayer player, Position tilePosition)
    {
        Faction playerFaction = player.getFaction();
        if (playerFaction == null)
        {
            return false;
        }

        int row = (int) Math.floor(tilePosition.getY() / tileWidth);
        int col = (int) Math.floor(tilePosition.getX() / tileHeight);

        if (0 < row && row < rows && 0 < col && col < cols)
        {
            GetTile tile = tiles[row][col];
            if (!tile.isWalled())
            {
                tiles[row][col] = TileFactory.createTile(playerFaction, false, col, row);
                System.out.println("Tile painted: " + tilePosition);
                return true;
            } else
            {
                System.out.println("ERROR: Tile not painted, tile is walled: " + tilePosition);
            }
        } else
        {
            System.out.println("ERROR: Tile not painted, wrong position: " + tilePosition);
        }

        return false;
    }

    @Override
    public boolean destroy(DecoratePlayer player, Position tilePosition)
    {

        Faction playerFaction = player.getFaction();
        if (playerFaction == null)
        {
            return false;
        }

        int row = (int) Math.floor(tilePosition.getY() / tileWidth);
        int col = (int) Math.floor(tilePosition.getX() / tileHeight);

        if (0 < row && row < rows && 0 < col && col < cols)
        {
            GetTile tile = tiles[row][col];
            if (tile.isWalled())
            {
                tiles[row][col] = TileFactory.createTile(tile.getFaction(), false, col, row);
                System.out.println("Wall destroyed" + tilePosition);
                return true;
            } else
            {
                System.out.println("ERROR: Wall not destroyed, no wall to destroy" + tilePosition);
            }
        } else
        {
            System.out.println("ERROR: Wall not destroyed, wrong position: " + tilePosition);
        }

        return false;
    }

    @Override
    public GetTile[][] getMapCopy()
    {
        GetTile[][] mapCopy = new GetTile[rows][rows];

        for (int row = 0; row < tiles.length; row++)
        {
            for (int col = 0; col < tiles[row].length; col++)
            {
                mapCopy[row][col] = tiles[row][col];
            }
        }
        return mapCopy;
    }

    @Override
    public GetTile getTile(int row, int col)
    {
        if (row < 0 || col < 0 || row > rows - 1 || col > cols - 1)
        {
            return null;
        }

        return tiles[row][col];
    }
}
