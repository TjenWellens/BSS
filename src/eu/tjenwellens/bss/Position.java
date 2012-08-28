package eu.tjenwellens.bss;

import java.io.Serializable;

/**
 *
 * @author tjen
 */
public class Position implements Serializable
{
    private int x;
    private int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    public Position(Position p)
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public double distance(Position p)
    {
        int x2 = p.getX();
        int y2 = p.getY();

        double dist = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));

        return dist;
    }
}
