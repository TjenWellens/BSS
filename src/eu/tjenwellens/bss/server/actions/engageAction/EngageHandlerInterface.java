package eu.tjenwellens.bss.server.actions.engageAction;

import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;

/**
 *
 * @author tjen
 */
public interface EngageHandlerInterface extends ActionHandlerInterface
{
    public boolean addEngage(AttackPlayer challenger, AttackPlayer challengee);

    public void removeEngage(Engage duel);

    public boolean updateEngages();
}
