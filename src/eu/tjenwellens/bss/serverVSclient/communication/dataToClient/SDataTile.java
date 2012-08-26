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

    SDataTile(GetTile getTile)
    {
        this.factionName = getTile.getFaction().getFactionName();
        this.isWalled = getTile.isWalled();
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
}
