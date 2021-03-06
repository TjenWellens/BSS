package eu.tjenwellens.bss.server.actions.engageAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class EngageHandler implements EngageHandlerInterface
{
    private AttackHandlerInterface attackHandler;
    private ArrayList<Engage> engages = new ArrayList<Engage>();

    public EngageHandler(AttackHandlerInterface attackHandler)
    {
        this.attackHandler = attackHandler;
    }

    protected synchronized List<Engage> copyDuels()
    {
        return new ArrayList(engages);
    }

    @Override
    public boolean addEngage(AttackPlayer challenger, AttackPlayer challengee)
    {
        Engage engage = new Engage(this, challenger, challengee, attackHandler);
        engages.add(engage);
        return true;
    }

    @Override
    public synchronized void removeEngage(Engage engage)
    {
        engages.remove(engage);
    }

    @Override
    public boolean updateEngages()
    {
        boolean result = false;
        for (Engage engage : copyDuels())
        {
            result = engage.update() || result;
        }
        return result;
    }

    @Override
    public void cancelAction(ActionPlayer player)
    {
        removeEngage(getDuelByPlayer(player));
    }

    protected Engage getDuelByPlayer(ActionPlayer player)
    {
        for (Engage engage : engages)
        {
            if (engage.hasPlayer(player))
            {
                return engage;
            }
        }
        return null;
    }
}
