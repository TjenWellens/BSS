package eu.tjenwellens.bss.server.actions.walkAction;

import eu.tjenwellens.bss.server.components.Position;

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
