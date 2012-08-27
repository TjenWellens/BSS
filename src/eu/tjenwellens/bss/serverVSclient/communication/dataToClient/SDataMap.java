package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.map.GetMap;
import eu.tjenwellens.bss.map.GetTile;
import java.util.ArrayList;
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

    public SDataMap(GetMap map)
    {
        tiles = new ArrayList<DataTile>();
        for (GetTile getTile : map.getTiles())
        {
            tiles.add(new SDataTile(getTile));
        }
        this.rows = map.getRows();
        this.cols = map.getCols();
    }

    @Override
    public List<DataTile> getTiles()
    {
        return tiles;
    }

    @Override
    public boolean isEmpty()
    {
        return tiles != null;
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
