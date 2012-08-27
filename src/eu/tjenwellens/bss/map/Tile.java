package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.factions.Faction;

/**
 *
 * @author tjen
 */
public class Tile implements GetTile
{
    private final Faction faction;
    private final boolean walled;
    private int row;
    private int col;

    public Tile(Faction faction, boolean walled, int row, int col)
    {
        this.faction = faction;
        this.walled = walled;
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.row != other.row || this.col != other.col)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + this.row;
        hash = 59 * hash + this.col;
        return hash;
    }

    @Override
    public String toString()
    {
        return "Tile{" + "faction=" + faction + ", walled=" + walled + '}';
    }

    @Override
    public Faction getFaction()
    {
        return faction;
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
