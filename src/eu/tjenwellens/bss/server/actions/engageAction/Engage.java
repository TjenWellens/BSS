package eu.tjenwellens.bss.server.actions.engageAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.HasPlayer;
import eu.tjenwellens.bss.server.actions.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;

/**
 *
 * @author tjen
 */
public class Engage implements HasPlayer
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

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && (p.equals(challenger) || p.equals(challengee));
    }
}
