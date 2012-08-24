package eu.tjenwellens.bss.actionhandlers.walkAction;

import eu.tjenwellens.bss.actionhandlers.walkAction.walk.WalkInterface;
import java.util.ArrayList;
import java.util.List;
import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.actionhandlers.walkAction.walk.CollisionJumpWalk;
import eu.tjenwellens.bss.actionhandlers.walkAction.walk.GhostJumpWalk;
import eu.tjenwellens.bss.map.MapHandlerInterface;
import eu.tjenwellens.bss.players.PlayerHandlerInterface;

/**
 *
 * @author tjen
 */
public class WalkHandler implements WalkHandlerInterface
{
    private ArrayList<WalkInterface> walks = new ArrayList<WalkInterface>();
    private MapHandlerInterface mapHandler;
    private PlayerHandlerInterface playerHandler;

    public WalkHandler(PlayerHandlerInterface playerHandler, MapHandlerInterface mapHandler)
    {
        this.playerHandler = playerHandler;
        this.mapHandler = mapHandler;
    }

    protected synchronized List<WalkInterface> copyWalks()
    {
        return new ArrayList(walks);
    }

    @Override
    public boolean addWalk(WalkPlayer player, Position destination)
    {
        if (destination == null || destination.getX() < 0 || destination.getX() > GameConstants.MAP_WIDTH || destination.getY() < 0 || destination.getY() > GameConstants.MAP_HEIGHT)
        {
            System.out.println("ERROR: WalkHandler.addWalk: invalid destination");
            return false;
        }
        WalkInterface walk;
        if (player.isGhost())
        {
            System.out.println("ghost");
            walk = new GhostJumpWalk(this, player, destination, mapHandler);
        } else
        {
            walk = new CollisionJumpWalk(this, player, destination, mapHandler, playerHandler);
        }
        walks.add(walk);
        return true;
    }

    @Override
    public synchronized void removeWalk(WalkInterface walk)
    {
        this.walks.remove(walk);
    }

    @Override
    public boolean updateWalks()
    {
        boolean result = false;
        for (WalkInterface walk : copyWalks())
        {
            result = walk.update() || result;
        }
        return result;
    }

    @Override
    public void cancelAction(ActionPlayer player)
    {
        removeWalk(getWalkByPlayer(player));
    }

    protected WalkInterface getWalkByPlayer(ActionPlayer player)
    {
        for (WalkInterface walk : walks)
        {
            if (walk.hasPlayer(player))
            {
                return walk;
            }
        }
        return null;
    }
}
