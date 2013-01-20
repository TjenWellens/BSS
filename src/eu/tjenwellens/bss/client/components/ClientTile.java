package eu.tjenwellens.bss.client.components;

import eu.tjenwellens.bss.data.commands.dataToClient.DataTile;

/**
 *
 * @author tjen
 */
public class ClientTile
{
    public int factionId;
    public boolean isWalled;
    public int row;
    public int col;

    public ClientTile(DataTile dataTile)
    {
        this.factionId = dataTile.getFactionId();
        this.isWalled = dataTile.isWalled();
        this.row = dataTile.getRow();
        this.col = dataTile.getCol();
    }
}
