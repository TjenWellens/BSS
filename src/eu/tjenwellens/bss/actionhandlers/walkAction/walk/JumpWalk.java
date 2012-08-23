/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction.walk;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;

/**
 *
 * @author tjen
 */
public class JumpWalk extends StepWalk implements GameConstants
{

    public JumpWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination)
    {
        super(walkHandler, player, destination);
    }

    @Override
    protected Position step()
    {// todo solve problem
        Position p = getStepDestination();
        if (p == null)
        {
            return null;
        } else
        {
            return p;
        }
    }

    protected Position getStepDestination()
    {
        Position position = player.getPosition();
        //sition destination
        double maxDist = WALK_RANGE;
        double distance = position.distance(destination);
        if (distance > maxDist)
        {
            int x = 0;
            int y = 0;
            //transponeer naar O
            int a = destination.getX() - position.getX();
            int b = destination.getY() - position.getY();
            // bereken hoeveel % x en y moeten verplaatst worden indien 45graden
            double k = maxDist / distance;
            // bereken de verplaatsingsgrootte (hoek maakt niet uit)    KLOPT WEL!!!!!!
            x = (int) (k * a);
            y = (int) (k * b);
            // transponeer terug naar de plaats waar alles gebeurt
            x += position.getX();
            y += position.getY();
            return new Position(x, y);
        } else
        {
            return destination;
        }
    }
}
