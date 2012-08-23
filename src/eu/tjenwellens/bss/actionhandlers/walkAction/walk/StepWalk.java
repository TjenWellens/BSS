/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction.walk;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;

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
        // TODO: revive
        Position step = step();
        if (step != null)
        {
            player.setPosition(step);
//            System.out.println("Walked to " + step);
            if (destination.equals(step))
            {
                walkHandler.removeWalk(this);
                System.out.println("You have arrived at your destination");
            }
            return true;
        } else
        {
//            System.out.println("Walking failed");
            return false;
        }
    }

    protected abstract Position step();
}
