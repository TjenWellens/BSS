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
public enum PlayerStateType implements PlayerState
{
    DECORATE(new PlayerDecorateState()),
    ENGAGE(new PlayerEngageState()),
    WALK(new PlayerWalkState()),
    IDLE(new PlayerIdleState()),
    GHOSTIDLE(new PlayerGhostIdleState()),
    GHOSTWALK(new PlayerGhostWalkState()),
    BANK(new PlayerBankState()),
    ATTACK(new PlayerAttackState());
    private PlayerState ps;

    private PlayerStateType(PlayerState ps)
    {
        this.ps = ps;
    }

    @Override
    public boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler)
    {
        return ps.walk(player, destination, walkHandler);
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        return ps.idle(player);
    }

    @Override
    public boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        return ps.engage(player, opponent, engageHandler);
    }

    @Override
    public boolean decorate(StatePlayer player, Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorationHander)
    {
        return ps.decorate(player, decoration, row, col, tool, decorationHander);
    }

    @Override
    public boolean bank(StatePlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, BankAccount bankAccount, Store store, BankHandlerInterface bankHandler)
    {
        return ps.bank(player, transaction, diamands, item, inventory, bankAccount, store, bankHandler);
    }

    @Override
    public boolean attack(StatePlayer player, AttackPlayer opponent)
    {
        return ps.attack(player, opponent);
    }

    @Override
    public boolean chooseWeapon(StatePlayer player, Weapon weapon, Inventory inventory)
    {
        return ps.chooseWeapon(player, weapon, inventory);
    }

    @Override
    public boolean isAttackable()
    {
        return ps.isAttackable();
    }

    @Override
    public PlayerStateType getPlayerStateType()
    {
        return ps.getPlayerStateType();
    }

    @Override
    public boolean isGhost()
    {
        return ps.isGhost();
    }
}
