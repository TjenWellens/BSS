package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.server.database.SavePlayer;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author tjen
 */
public interface PlayerHandlerPlayer extends GetPlayer, AttackPlayer, WalkObstaclePlayer, SavePlayer
{
    boolean walk(Position destination, WalkHandlerInterface walkHandler);

    boolean idle();

    boolean engage(AttackPlayer opponent, EngageHandlerInterface engageHandler);

    boolean decorate(Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorateHander);

    boolean bank(Transaction transaction, int diamands, Item item, BankHandlerInterface bankHandler);

    boolean chooseWeapon(Weapon weapon);

    void updateID(int newID);
}
