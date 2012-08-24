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
    {// TODO: solve problem - 23/08/2012 what problem?
        Position p = getStepDestination();
        // overbodig:
//        if (p == null)
//        {
//            return null;
//        }
        return p;
    }

    protected Position getStepDestination()
    {
        Position position = player.getPosition();
        //sition destination
        double maxDist = WALK_RANGE;
        double distance = position.distance(destination);
        if (distance <= maxDist)
        {// arrived at destination
            return destination;
        } else
        {
            //transponeer naar O
            int a = destination.getX() - position.getX();
            int b = destination.getY() - position.getY();
            // bereken hoeveel % x en y moeten verplaatst worden indien 45graden
            double k = maxDist / distance;
            // bereken de verplaatsingsgrootte (hoek maakt niet uit)    KLOPT WEL!!!!!!
            int x = (int) (k * a);
            int y = (int) (k * b);
            // transponeer terug naar de plaats waar alles gebeurt
            x += position.getX();
            y += position.getY();
            return new Position(x, y);
        }
    }
}
