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
import eu.tjenwellens.bss.players.playerstate.PlayerStateType;
import eu.tjenwellens.bss.players.playerstate.StatePlayer;

/**
 *
 * @author tjen
 */
public class PlayerGhostWalkState extends PlayerGeneralState
{
    protected PlayerGhostWalkState()
    {
    }

    @Override
    public String toString()
    {
        return getPlayerStateType().toString();
    }

    @Override
    public boolean isAttackable()
    {
        return false;
    }

    @Override
    public PlayerStateType getPlayerStateType()
    {
        return PlayerStateType.GHOST_WALK;
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        player.resetActions();
        player.setState(PlayerState.GHOSTIDLE);
        return true;
    }

    @Override
    public boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler)
    {
        // for safety
        player.resetActions();
        if (walkHandler.addWalk(player, destination))
        {
            player.addActionHandler(walkHandler);
            player.setState(PlayerState.GHOSTWALK);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(StatePlayer player, AttackPlayer opponent)
    {
        return false;
    }

    @Override
    public boolean bank(StatePlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, BankAccount bankAccount, Store store, BankHandlerInterface bankHandler)
    {
        return false;
    }

    @Override
    public boolean chooseWeapon(StatePlayer player, Weapon weapon, Inventory inventory)
    {
        return false;
    }

    @Override
    public boolean decorate(StatePlayer player, Decoration decoration, Position location, Tool tool, DecorateHanderInterface decorationHander)
    {
        return false;
    }

    @Override
    public boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        return false;
    }

    @Override
    public boolean isGhost()
    {
        return true;
    }
}
