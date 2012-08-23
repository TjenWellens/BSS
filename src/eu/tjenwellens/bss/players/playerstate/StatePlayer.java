/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.playerstate;

import eu.tjenwellens.bss.players.playerstate.playerstates.PlayerState;
import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankPlayer;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;
import eu.tjenwellens.bss.factions.FactionPlayer;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecoratePlayer;

/**
 *
 * @author tjen
 */
public interface StatePlayer extends WalkPlayer, AttackPlayer, DecoratePlayer, FactionPlayer, BankPlayer
{

    void setState(PlayerState state);

    void resetActions();

    void addActionHandler(ActionHandlerInterface actionHandler);
}
