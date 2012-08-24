package eu.tjenwellens.bss.actionhandlers.walkAction.walk;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;
import eu.tjenwellens.bss.map.MapHandlerInterface;

/**
 *
 * @author tjen
 */
public class GhostJumpWalk extends JumpWalk
{
    private MapHandlerInterface mapHandler;

    public GhostJumpWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination, MapHandlerInterface mapHandler)
    {
        super(walkHandler, player, destination);
        this.mapHandler = mapHandler;
    }

    @Override
    protected Position step()
    {// todo solve problem
        Position p = super.step();
        if (p != null && mapHandler.getTile((int) (p.getX() / (MAP_WIDTH / MAP_ROWS)), (int) (p.getY() / (MAP_HEIGHT / MAP_COLUMNS))).getFaction().containsPlayer(player))
        {
            player.revive();
        }
        return p;
    }
}
