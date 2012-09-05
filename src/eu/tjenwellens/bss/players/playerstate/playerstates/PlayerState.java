package eu.tjenwellens.bss.players.playerstate.playerstates;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankAccount;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.Store;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.players.inventory.Inventory;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.players.playerstate.StatePlayer;

/**
 *
 * @author Tjen
 */
public interface PlayerState
{
    boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler);

    boolean idle(StatePlayer player);

    boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler);

    boolean decorate(StatePlayer player, Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorationHander);

    boolean bank(StatePlayer player, Transaction transaction, int diamands, Item item,
            Inventory inventory, BankAccount bankAccount, Store store, BankHandlerInterface bankHandler);

    boolean attack(StatePlayer player, AttackPlayer opponent);

    boolean chooseWeapon(StatePlayer player, Weapon weapon, Inventory inventory);

    boolean isAttackable();

    PlayerStateType getPlayerStateType();

    boolean isGhost();
}
