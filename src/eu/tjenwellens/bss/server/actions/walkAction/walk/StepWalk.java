package eu.tjenwellens.bss.server.actions.walkAction.walk;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkPlayer;
import eu.tjenwellens.bss.server.components.Position;

/**
 *
 * @author tjen
 */
public abstract class StepWalk implements WalkInterface
{
    protected WalkHandlerInterface walkHandler;
    protected WalkPlayer player;
    protected Position destination;

    public StepWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination)
    {
        this.walkHandler = walkHandler;
        this.player = player;
        this.destination = destination;
    }

    @Override
    public boolean update()
    {
        Position step = step();
        if (step == null)
        {
            walkHandler.removeWalk(this);
            player.idle();
            System.out.println("Walking failed");
//            return false;
        }
        // all is good
        player.setPosition(step);
//            System.out.println("Walked to " + step);
        if (destination.equals(step))
        {
            walkHandler.removeWalk(this);
            player.idle();
            System.out.println("You have arrived at your destination");
        }
        return true;
    }

    protected abstract Position step();

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && p.equals(player);
    }
}
