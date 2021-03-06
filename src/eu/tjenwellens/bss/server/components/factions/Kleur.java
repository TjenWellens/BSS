package eu.tjenwellens.bss.server.components.factions;

import java.awt.Color;

/**
 *
 * @author tjen
 */
public enum Kleur
{
    WIT(Color.white), ROOD(Color.red), GEEL(Color.yellow), BLAUW(Color.BLUE);
    private Color c;

    private Kleur(Color c)
    {
        this.c = c;
    }

    public Color getColor()
    {
        return c;
    }
}
