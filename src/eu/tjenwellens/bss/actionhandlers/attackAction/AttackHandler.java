/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.attackAction;

import java.util.ArrayList;
import java.util.List;
import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;

/**
 *
 * @author tjen
 */
public class AttackHandler implements AttackHandlerInterface
{

    private ArrayList<Attack> duels = new ArrayList<Attack>();

    public AttackHandler()
    {
    }

    @Override
    public boolean addDuel(AttackPlayer p1, AttackPlayer p2)
    {
        if (p1.isAttackable() && p2.isAttackable())
        {
            Position p1pos = p1.getPosition();
            Position p2pos = p2.getPosition();
            if (p1pos != null && p2pos != null && (p1pos.distance(p2pos) <= GameConstants.ATTACK_RANGE))
            {
                System.out.println("Attack success");
                Attack attack = new Attack(this, p1, p2);
                p1.attack(p2);
                p2.attack(p1);
                duels.add(attack);
                return true;
            } else
            {
                System.out.println("ERROR: AttackHandler.addDuel null, null or out of distance"
                        + (p1pos != null) + "" + (p2pos != null) + ""
                        + (p1pos.distance(p2pos) <= GameConstants.ATTACK_RANGE));
            }
        } else
        {
            System.out.println("ERROR: AttackHandler.addDuel not attackable ");
        }
        return false;
    }

    protected synchronized List<Attack> copyDuels()
    {
        return new ArrayList(duels);
    }

    @Override
    public synchronized boolean updateDuels()
    {
        boolean result = false;
        for (Attack duel : copyDuels())
        {
            result = duel.update() || result;
        }
        return result;
    }

    @Override
    public synchronized void removeDuel(Attack duel)
    {
        duels.remove(duel);
    }

    @Override
    public void cancelAction(ActionPlayer player)
    {
    }
}
