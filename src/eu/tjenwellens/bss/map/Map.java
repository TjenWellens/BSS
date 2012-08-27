package eu.tjenwellens.bss.map;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tjen
 */
public class Map implements MapI
{
    private int rows;
    private int cols;
    private List<GetTile> tiles;

    public Map(int rows, int cols, List<GetTile> tiles)
    {
        this.rows = rows;
        this.cols = cols;
        this.tiles = tiles;
    }

    public Map(GetTile[][] gt)
    {
        this.rows = gt.length;
        this.cols = gt[0].length;
        tiles = new ArrayList<GetTile>();
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                tiles.add(gt[row][col]);
            }

        }
    }

    @Override
    public List<GetTile> getTiles()
    {
        return new ArrayList<GetTile>(tiles);
    }

    @Override
    public int getRows()
    {
        return rows;
    }

    @Override
    public int getCols()
    {
        return cols;
    }

    @Override
    public boolean positionInMap(int row, int col)
    {
        return 0 <= row && row < rows && 0 <= col && col < cols;
    }

    @Override
    public void setTile(GetTile getTile)
    {
        int row = getTile.getRow();
        int col = getTile.getCol();
        int index;
        GetTile oldTile = getTile(row, col);
        if (oldTile == null)
        {
            System.out.println("ERROR: tile should not be null");
            index = row * cols + col;
        } else
        {
            index = tiles.indexOf(oldTile);
        }
        tiles.set(index, getTile);
    }

    @Override
    public GetTile getTile(int row, int col)
    {
        if (!positionInMap(row, col))
        {
            return null;
        }
        GetTile ret = tiles.get(row * cols + col);
        if (ret.getCol() == col && ret.getRow() == row)
        {
            return ret;
        }
        System.out.println("ClientTile not at assumed position");
        for (GetTile clientTile : tiles)
        {
            if (clientTile.getCol() == col && clientTile.getRow() == row)
            {
                return clientTile;
            }
        }
        return null;
    }

    @Override
    public GetMap getCopy()
    {
        return new Map(rows, cols, getTiles());
    }
}
