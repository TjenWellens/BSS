package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.factions.Faction;
import java.awt.Point;

/**
 *
 * @author tjen
 */
public interface GetTile
{
    Faction getFaction();

    boolean isWalled();

    Point getPoint();
}
