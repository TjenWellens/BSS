/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.factions.FactionPlayer;

/**
 *
 * @author tjen
 */
public interface WalkPlayer extends ActionPlayer, WalkObstaclePlayer, FactionPlayer, Revivable
{

    boolean isGhost();

    void setPosition(Position position);

    Position getPosition();
}
