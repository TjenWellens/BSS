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
        // check p1 attackable
        if (!p1.isAttackable())
        {
            System.out.println("Attack cancelled: p1 is busy");
            return false;
        }
        // check p2 attackable
        if (!p2.isAttackable())
        {
            System.out.println("Attack cancelled: p2 is busy");
            return false;
        }
        // check pos of p1
        Position p1pos = p1.getPosition();
        if (p1pos == null)
        {
            System.out.println("ERROR: AttackHandler.addDuel(...) p1 pos null: " + p1);
            return false;
        }
        // check pos of p2
        Position p2pos = p2.getPosition();
        if (p2pos == null)
        {
            System.out.println("ERROR: AttackHandler.addDuel(...) p2 pos null: " + p2);
            return false;
        }
        // check distance
        double distance = p1pos.distance(p2pos);
        if (distance > GameConstants.ATTACK_RANGE)
        {
            System.out.println("Attack cancelled: out of range: " + distance);
            return false;
        }
        // all is well, continue attack
        System.out.println("Attack success");
        Attack attack = new Attack(this, p1, p2);
        p1.attack(p2);
        p2.attack(p1);
        duels.add(attack);
        return true;
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
        removeDuel(getDuelByPlayer(player));
    }

    protected Attack getDuelByPlayer(ActionPlayer player)
    {
        for (Attack attack : duels)
        {
            if (attack.hasPlayer(player))
            {
                return attack;
            }
        }
        return null;
    }
}
