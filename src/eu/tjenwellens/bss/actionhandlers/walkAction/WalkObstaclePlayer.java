/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction;

import eu.tjenwellens.bss.Position;

/**
 *
 * @author tjen
 */
public interface WalkObstaclePlayer
{

    boolean isGhost();

    public int getPlayerID();

    public Position getPosition();
}
