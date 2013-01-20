package eu.tjenwellens.bss.data.commands.dataToClient;

/**
 *
 * @author Tjen
 */
public class SDataTile implements DataTile
{
    private int row;
    private int col;
    private int factionId;
    private boolean walled;

    public SDataTile(int row, int col, int factionId, boolean walled)
    {
        this.row = row;
        this.col = col;
        this.factionId = factionId;
        this.walled = walled;
    }

    @Override
    public int getFactionId()
    {
        return factionId;
    }

    @Override
    public boolean isWalled()
    {
        return walled;
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
