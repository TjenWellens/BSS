package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataTile;

/**
 *
 * @author tjen
 */
public class ClientTile
{
    public String factionName;
    public boolean isWalled;
    public int row;
    public int col;

    public ClientTile(DataTile dataTile)
    {
        this.factionName = dataTile.getFactionName();
        this.isWalled = dataTile.isWalled();
        this.row = dataTile.getRow();
        this.col = dataTile.getCol();
    }
}
