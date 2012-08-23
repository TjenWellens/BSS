/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.map;

import java.awt.Point;
import eu.tjenwellens.bss.factions.Faction;

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
