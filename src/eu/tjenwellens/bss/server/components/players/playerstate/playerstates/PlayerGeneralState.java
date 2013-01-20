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
 * @author tjen
 */
public abstract class PlayerGeneralState implements PlayerState
{
    @Override
    public boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler)
    {
        player.resetActions();
        if (walkHandler.addWalk(player, destination))
        {
            player.addActionHandler(walkHandler);
            player.setState(PlayerStateType.WALK);
            return true;
        }
        return false;
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        player.resetActions();
        player.setState(PlayerStateType.IDLE);
        return true;
    }

    @Override
    public boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        player.resetActions();
        if (engageHandler.addEngage(player, opponent))
        {
            player.addActionHandler(engageHandler);
            player.setState(PlayerStateType.ENGAGE);
            return true;
        }
        return false;
    }

    @Override
    public boolean decorate(StatePlayer player, Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorationHander)
    {
        player.resetActions();
        if (decorationHander.addDecorate(player, decoration, row, col, tool))
        {
            player.addActionHandler(decorationHander);
            player.setState(PlayerStateType.DECORATE);
            return true;
        }
        return false;
    }

    @Override
    public boolean bank(StatePlayer player, Transaction transaction, int diamands, Item item,
            Inventory inventory, BankAccount bankAccount, Store store, BankHandlerInterface bankHandler)
    {
        player.resetActions();
        if (bankHandler.addBankJob(player, transaction, diamands, item, inventory, store, bankAccount))
        {
            player.addActionHandler(bankHandler);
            player.setState(PlayerStateType.BANK);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(StatePlayer player, AttackPlayer opponent)
    {
        player.resetActions();
        player.setState(PlayerStateType.ATTACK);
        return true;
    }

    @Override
    public boolean chooseWeapon(StatePlayer player, Weapon weapon, Inventory inventory)
    {
        return false;
    }

    @Override
    public abstract String toString();

//    @Override
//    public abstract boolean isAttackable();
//    @Override
//    public abstract PlayerStateType getPlayerStateType();
    @Override
    public boolean isGhost()
    {
        return false;
    }
}
