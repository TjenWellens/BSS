package eu.tjenwellens.bss.server.actions.walkAction;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.components.factions.FactionPlayer;

/**
 *
 * @author tjen
 */
public interface WalkPlayer extends ActionPlayer, WalkObstaclePlayer, FactionPlayer, Revivable
{
    boolean idle();

    @Override
    boolean isGhost();

    void setPosition(Position position);

    @Override
    Position getPosition();
}
