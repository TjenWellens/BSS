/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.attackAction;

import eu.tjenwellens.bss.players.inventory.items.Weapon;

/**
 *
 * @author tjen
 */
public class Attack
{

    private AttackHandler duelHandler;
    private AttackPlayer p1;
    private AttackPlayer p2;

    public Attack(AttackHandler duelHandler, AttackPlayer p1, AttackPlayer p2)
    {
        this.duelHandler = duelHandler;
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean update()
    {
        AttackResult attackResult = Attack.doDuel(p1, p2);
        if (attackResult != null)
        {
            if (attackResult.isDraw())
            {
                undecided(attackResult);
            } else
            {
                // decide if losing loses
                if (attackResult.getLosingWeapon().isDisposableMaterial())
                {
                    undecided(attackResult);
                } else
                {
                    decided(attackResult);
                }
            }
            return true;
        }
        return false;
    }

    protected void undecided(AttackResult attackResult)
    {
        System.out.println("Draw: " + attackResult);
        p1.hasDraw(attackResult);
        p2.hasDraw(attackResult);
    }

    protected void decided(AttackResult attackResult)
    {
        System.out.println("Battle decided: " + attackResult);
        AttackPlayer winner = attackResult.getWinner();
        AttackPlayer loser = attackResult.getLoser();
        winner.hasWon(attackResult);
        loser.hasLost(attackResult, winner);
        duelHandler.removeDuel(this);
    }

    public static AttackResult doDuel(AttackPlayer p1, AttackPlayer p2)
    {
        AttackResult result = null;
        Weapon w1 = p1.getWeapon();
        Weapon w2 = p2.getWeapon();
        if (w1 != null && w2 != null)
        {
            int attackResult = w1.compare(w2);
            if (attackResult == 0)
            {
                result = new AttackResult(true, p1, p2, w1, w2);
            } else if (attackResult == 1)
            {
                result = new AttackResult(false, p1, p2, w1, w2);
            } else if (attackResult == -1)
            {
                result = new AttackResult(false, p2, p1, w2, w1);
            }
        }

        return result;
    }
}
