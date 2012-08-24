package eu.tjenwellens.bss.map;

import java.awt.Point;
import eu.tjenwellens.bss.factions.Faction;

/**
 *
 * @author tjen
 */
public class Tile implements GetTile
{
    private final Faction faction;
    private final boolean walled;
    private Point point;

    public Tile(Faction faction, boolean walled, Point point)
    {
        this.faction = faction;
        this.walled = walled;
        this.point = point;
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
        if (this.point != other.point && (this.point == null || !this.point.equals(other.point)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.point != null ? this.point.hashCode() : 0);
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
    public Point getPoint()
    {
        return point;
    }
}
