/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.attackAction;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.players.OpponentPlayer;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

/**
 *
 * @author tjen
 */
public interface AttackPlayer extends OpponentPlayer, ActionPlayer
{

    boolean isAttackable();

    Position getPosition();

    void hasWon(AttackResult duelResult);

    void hasLost(AttackResult duelResult, AttackPlayer opponent);

    void hasDraw(AttackResult duelResult);

    String getPlayerName();

    Weapon getWeapon();

    boolean attack(AttackPlayer opponent);

    // to give the winner the diamands of the loser
    void addDiamands(int removeAllDiamands);
}
