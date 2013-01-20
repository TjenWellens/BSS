package eu.tjenwellens.bss.server.actions.attackAction;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.components.players.OpponentPlayer;
import eu.tjenwellens.bss.server.components.items.Weapon;

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

    @Override
    String getPlayerName();

    Weapon getWeapon();

    boolean attack(AttackPlayer opponent);

    // to give the winner the diamands of the loser
    void addDiamands(int removeAllDiamands);
}
