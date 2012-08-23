/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.engageAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;

/**
 *
 * @author tjen
 */
public class EngageHandler implements EngageHandlerInterface
{

    private AttackHandlerInterface attackHandler;
    private ArrayList<Engage> engages = new ArrayList<Engage>();
    private HashMap<ActionPlayer, Engage> playerLog = new HashMap<ActionPlayer, Engage>();

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
        playerLog.put(challengee, engage);
        return true;
    }

    @Override
    public synchronized void removeEngage(Engage engage)
    {
        engages.remove(engage);
        playerLog.values().remove(engage);
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
        removeEngage(playerLog.get(player));
    }
}
