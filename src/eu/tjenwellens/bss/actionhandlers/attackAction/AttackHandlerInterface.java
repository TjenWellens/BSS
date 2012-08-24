package eu.tjenwellens.bss.actionhandlers.attackAction;

import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;

/**
 *
 * @author tjen
 */
public interface AttackHandlerInterface extends ActionHandlerInterface
{
    public boolean addDuel(AttackPlayer p1, AttackPlayer p2);

    public void removeDuel(Attack duel);

    public boolean updateDuels();
}
