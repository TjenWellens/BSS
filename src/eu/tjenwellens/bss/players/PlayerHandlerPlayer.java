package eu.tjenwellens.bss.players;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

/**
 *
 * @author tjen
 */
public interface PlayerHandlerPlayer extends GetPlayer, AttackPlayer, WalkObstaclePlayer
{
    boolean walk(Position destination, WalkHandlerInterface walkHandler);

    boolean idle();

    boolean engage(AttackPlayer opponent, EngageHandlerInterface engageHandler);

    boolean decorate(Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorateHander);

    boolean bank(Transaction transaction, int diamands, Item item, BankHandlerInterface bankHandler);

    boolean chooseWeapon(Weapon weapon);
}
