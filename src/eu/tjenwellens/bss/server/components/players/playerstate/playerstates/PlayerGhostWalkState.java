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
        return PlayerStateType.GHOSTWALK;
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        player.resetActions();
        player.setState(PlayerStateType.GHOSTIDLE);
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
            player.setState(PlayerStateType.GHOSTWALK);
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
    public boolean decorate(StatePlayer player, Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorationHander)
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
