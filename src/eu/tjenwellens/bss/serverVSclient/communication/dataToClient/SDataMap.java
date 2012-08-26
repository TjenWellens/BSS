package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.map.GetTile;

/**
 *
 * @author Tjen
 */
public class SDataMap implements DataMap
{
    private DataTile[][] tiles;

    public SDataMap(GetTile[][] map)
    {
        if (map != null)
        {
            this.tiles = new DataTile[map.length][map[0].length];
            for (int i = 0; i < map.length; i++)
            {
                for (int j = 0; j < map[i].length; j++)
                {
                    this.tiles[i][j] = new SDataTile(map[i][j]);
                }
            }
        }
    }

    @Override
    public DataTile[][] getTiles()
    {
        return tiles;
    }

    @Override
    public boolean isEmpty()
    {
        return tiles != null;
    }
}
