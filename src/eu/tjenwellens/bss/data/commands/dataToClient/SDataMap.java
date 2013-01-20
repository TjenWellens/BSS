package eu.tjenwellens.bss.data.commands.dataToClient;

import java.util.List;

/**
 *
 * @author Tjen
 */
public class SDataMap implements DataMap
{
    private List<DataTile> tiles;
    private int rows;
    private int cols;

    public SDataMap(List<DataTile> tiles, int rows, int cols)
    {
        this.tiles = tiles;
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public List<DataTile> getTiles()
    {
        return tiles;
    }

    @Override
    public boolean isEmpty()
    {
        return tiles.isEmpty();
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
}
