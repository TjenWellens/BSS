package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataMap;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataTile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tjen
 */
public class ClientMap
{
    public List<ClientTile> tiles = new ArrayList<ClientTile>();
    public int rows;
    public int cols;

    public ClientMap(DataMap m)
    {
        List<DataTile> dts = m.getTiles();
        for (DataTile dataTile : dts)
        {
            tiles.add(new ClientTile(dataTile));
        }
        rows = m.getRows();
        cols = m.getCols();
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }

    public ClientTile getTile(int row, int col)
    {
        if (!isPositionInMap(row, col))
        {
            System.out.println("ERROR ClientMap.getTile: tile not in map: row:" + row + " - col:" + col+" - rows:" + rows + " - cols:" + cols);
            return null;
        }
        ClientTile ret = tiles.get(row * cols + col);
        if (ret.col == col && ret.row == row)
        {
            return ret;
        }
        System.out.println("ClientMap.getTile: ClientTile not at assumed position: row:" + row + " - col:" + col);
        for (ClientTile clientTile : tiles)
        {
            if (clientTile.col == col && clientTile.row == row)
            {
                return clientTile;
            }
        }
        System.out.println("ERROR ClientMap.getTile: ClientTile not found, returning null: row:" + row + " - col:" + col);
        return null;
    }

    public boolean isPositionInMap(int row, int col)
    {
        return 0 <= row && row < rows && 0 <= col && col < cols;
    }
}
