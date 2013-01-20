package eu.tjenwellens.bss.server.actions.walkAction.walk;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkPlayer;

/**
 *
 * @author tjen
 */
public class DirectWalk implements WalkInterface
{
    private WalkHandlerInterface walkHandler;
    private WalkPlayer player;
    private Position destination;

    public DirectWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination)
    {
        this.walkHandler = walkHandler;
        this.player = player;
        this.destination = destination;
    }

    @Override
    public boolean update()
    {
        player.setPosition(destination);
        System.out.println("Walked to " + destination);
        walkHandler.removeWalk(this);
        player.idle();
        return true;
    }

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && p.equals(player);
    }
}
