package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author tjen
 */
public interface PlayerActions extends PlayerHandlerPlayer
{
    @Override
    boolean idle();

    @Override
    boolean walk(Position destination, WalkHandlerInterface walkHandler);

    @Override
    boolean engage(AttackPlayer opponent, EngageHandlerInterface engageHandler);

    @Override
    boolean attack(AttackPlayer opponent);

    @Override
    boolean chooseWeapon(Weapon weapon);

//    @Override
//    boolean die();
    @Override
    boolean decorate(Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorateHander);

    @Override
    boolean bank(Transaction transaction, int diamands, Item item, BankHandlerInterface bankHandler);

    @Override
    public abstract String toString();
}
