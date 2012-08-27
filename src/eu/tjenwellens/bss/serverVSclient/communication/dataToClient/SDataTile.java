package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.map.GetTile;

/**
 *
 * @author Tjen
 */
public class SDataTile implements DataTile
{
    private String factionName;
    private boolean isWalled;
    private int row;
    private int col;

    SDataTile(GetTile getTile)
    {
        this.factionName = getTile.getFaction().getFactionName();
        this.isWalled = getTile.isWalled();
        this.row = getTile.getRow();
        this.col = getTile.getCol();
    }

    @Override
    public String getFactionName()
    {
        return factionName;
    }

    @Override
    public boolean isWalled()
    {
        return isWalled;
    }

    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public int getCol()
    {
        return col;
    }
}
