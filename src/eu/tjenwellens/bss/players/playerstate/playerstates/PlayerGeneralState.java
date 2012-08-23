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
 * @author tjen
 */
public abstract class PlayerGeneralState implements PlayerState
{

    @Override
    public boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler)
    {
        player.resetActions();
        if (walkHandler.addWalk(player, destination)) {
            player.addActionHandler(walkHandler);
            player.setState(PlayerState.WALK);
            return true;
        }
        return false;
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        player.resetActions();
        player.setState(PlayerState.IDLE);
        return true;
    }

    @Override
    public boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        player.resetActions();
        if (engageHandler.addEngage(player, opponent)) {
            player.addActionHandler(engageHandler);
            player.setState(PlayerState.ENGAGE);
            return true;
        }
        return false;
    }

    @Override
    public boolean decorate(StatePlayer player, Decoration decoration, Position location, Tool tool, DecorateHanderInterface decorationHander)
    {
        player.resetActions();
        if (decorationHander.addDecorate(player, decoration, location, tool)) {
            player.addActionHandler(decorationHander);
            player.setState(PlayerState.DECORATE);
            return true;
        }
        return false;
    }

    @Override
    public boolean bank(StatePlayer player, Transaction transaction, int diamands, Item item,
            Inventory inventory, BankAccount bankAccount, Store store, BankHandlerInterface bankHandler)
    {
        player.resetActions();
        if (bankHandler.addBankJob(player, transaction, diamands, item, inventory, store, bankAccount)) {
            player.addActionHandler(bankHandler);
            player.setState(PlayerState.BANK);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(StatePlayer player, AttackPlayer opponent)
    {
        player.resetActions();
        player.setState(PlayerState.ATTACK);
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
