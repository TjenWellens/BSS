package eu.tjenwellens.bss.server.actions.walkAction;

import eu.tjenwellens.bss.server.actions.walkAction.walk.WalkInterface;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;

/**
 *
 * @author tjen
 */
public interface WalkHandlerInterface extends ActionHandlerInterface
{
    public boolean addWalk(WalkPlayer player, Position destination);

    public void removeWalk(WalkInterface walk);

    boolean updateWalks();
}
