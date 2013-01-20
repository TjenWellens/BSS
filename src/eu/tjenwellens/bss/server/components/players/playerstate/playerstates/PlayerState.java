package eu.tjenwellens.bss.server.components.players.playerstate.playerstates;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import eu.tjenwellens.bss.server.actions.bankAction.BankAccount;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.bankAction.shop.Store;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.components.players.inventory.Inventory;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.components.players.playerstate.StatePlayer;

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
