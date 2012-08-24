package eu.tjenwellens.bss.players;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

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
    boolean decorate(Decoration decoration, Position location, Tool tool, DecorateHanderInterface decorateHander);

    @Override
    boolean bank(Transaction transaction, int diamands, Item item, BankHandlerInterface bankHandler);

    @Override
    public abstract String toString();
}
