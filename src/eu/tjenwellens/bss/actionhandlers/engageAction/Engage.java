/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.engageAction;

import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;

/**
 *
 * @author tjen
 */
public class Engage
{

    private EngageHandlerInterface engageHandler;
    private AttackPlayer challenger;
    private AttackPlayer challengee;
    private AttackHandlerInterface attackHandler;

    public Engage(EngageHandlerInterface engageHandler, AttackPlayer challenger, AttackPlayer challengee, AttackHandlerInterface attackHandler)
    {
        this.engageHandler = engageHandler;
        this.challenger = challenger;
        this.challengee = challengee;
        this.attackHandler = attackHandler;
    }

    boolean update()
    {
        if (attackHandler.addDuel(challenger, challengee))
        {
            engageHandler.removeEngage(this);
            System.out.println("Attack started");
            return true;
        }
        System.out.println("Duel not added");
        return false;
    }
}
