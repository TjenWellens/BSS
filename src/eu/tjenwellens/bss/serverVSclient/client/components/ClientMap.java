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
}
