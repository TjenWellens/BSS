/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.engageAction;

import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;

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
