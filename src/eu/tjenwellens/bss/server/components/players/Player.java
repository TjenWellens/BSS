package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import eu.tjenwellens.bss.server.actions.attackAction.AttackResult;
import eu.tjenwellens.bss.server.actions.bankAction.BankAccount;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.GetBankAccount;
import eu.tjenwellens.bss.server.actions.bankAction.SimpleBankAccount;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.bankAction.shop.GetStore;
import eu.tjenwellens.bss.server.actions.bankAction.shop.SimpleStore;
import eu.tjenwellens.bss.server.actions.bankAction.shop.Store;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.players.inventory.GetInventory;
import eu.tjenwellens.bss.server.components.players.inventory.Inventory;
import eu.tjenwellens.bss.server.components.players.inventory.SimpleInventory;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.components.players.playerstate.playerstates.PlayerStateType;
import eu.tjenwellens.bss.server.components.players.playerstate.StatePlayer;
import eu.tjenwellens.bss.server.components.players.playerstate.playerstates.PlayerState;
import java.util.ArrayList;

/**
 *
 * @author tjen
 */
public class Player implements PlayerActions, StatePlayer, PlayerHandlerPlayer
{
    private int playerID;
    private String playerName;
    private Faction faction;
    private volatile Position position;
    private PlayerState state;
    // walk
    private volatile Position destination = null;
    // battleresult
    private int winns = 0;
    private int losses = 0;
    private AttackResult previousDuelResult = null;
    // bank
    private Inventory inventory;
    private BankAccount bankAccount;
    private Store store;
    // battle
    private AttackPlayer opponent = null;
    // actions
    private ArrayList<ActionHandlerInterface> actionHandlers = new ArrayList<>();

    public Player(int playerID, String playerName, Faction faction, Position position)
    {
        this.playerID = playerID;
        this.playerName = playerName;
        this.faction = faction;
        this.position = position;
        // init
        this.state = PlayerStateType.IDLE;
        this.inventory = new SimpleInventory();
        this.bankAccount = new SimpleBankAccount();
        this.store = SimpleStore.getInstance();
    }

    public Player(int playerID, String playerName, int winns, int losses, Faction faction, Position position, Inventory inventory, BankAccount bankAccount, Store store)
    {
        this.playerID = playerID;
        this.playerName = playerName;
        this.winns = winns;
        this.losses = losses;
        this.faction = faction;
        this.position = position;
        // init
        this.state = PlayerStateType.IDLE;
        this.inventory = inventory;
        this.bankAccount = bankAccount;
        this.store = store;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Player))
        {
            return false;
        }
        final Player other = (Player) obj;
        if (this.playerID != other.playerID)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        return this.playerID;
    }

    @Override
    public String toString()
    {
        return "Player{"
                + "playerID=" + playerID
                + ", playerName=" + playerName
                + ", faction=" + faction
                + ", position=" + position
                + ", state=" + state
                + ", destination=" + destination
                + ", winns=" + winns
                + ", losses=" + losses
                + ", previousDuelResult=" + previousDuelResult
                + ", inventory=" + inventory
                + ", bankAccount=" + bankAccount
                + ", store=" + store
                + ", opponent=" + opponent
                + '}';
    }

    @Override
    public boolean walk(Position destination, WalkHandlerInterface walkHandler)
    {
        return state.walk(this, destination, walkHandler);
    }

    @Override
    public boolean idle()
    {
        return state.idle(this);
    }

    @Override
    public boolean engage(AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        return state.engage(this, opponent, engageHandler);
    }

    @Override
    public boolean attack(AttackPlayer opponent)
    {
        return state.attack(this, opponent);
    }

    @Override
    public boolean decorate(Decoration decoration, int row, int col, Tool tool, DecorateHanderInterface decorationHander)
    {
        return state.decorate(this, decoration, row, col, tool, decorationHander);
    }

    @Override
    public boolean bank(Transaction transaction, int diamands, Item item, BankHandlerInterface bankHandler)
    {
        return state.bank(this, transaction, diamands, item, inventory, bankAccount, store, bankHandler);
    }

    @Override
    public boolean chooseWeapon(Weapon weapon)
    {
        return state.chooseWeapon(this, weapon, inventory);
    }

    @Override
    public Faction getFaction()
    {
        return this.faction;
    }

    @Override
    public void setFaction(Faction faction)
    {
        this.faction = faction;
    }

    @Override
    public boolean isAttackable()
    {
        return state.isAttackable();
    }

    @Override
    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public void hasWon(AttackResult duelResult)
    {
        this.previousDuelResult = duelResult;
        winns++;
        state = PlayerStateType.IDLE;
        resetActions();
        inventory.emptyWeapon();
    }

    @Override
    public void hasLost(AttackResult duelResult, AttackPlayer opponent)
    {
        this.previousDuelResult = duelResult;
        losses++;
        opponent.addDiamands(inventory.removeAllDiamands());
        state = PlayerStateType.GHOSTIDLE;
        resetActions();
        inventory.emptyWeapon();
    }

    @Override
    public void hasDraw(AttackResult duelResult)
    {
        this.previousDuelResult = duelResult;
        inventory.emptyWeapon();
    }

    @Override
    public String getPlayerName()
    {
        return this.playerName;
    }

    @Override
    public Weapon getWeapon()
    {
        return inventory.getWeapon();
    }

    @Override
    public void addDiamands(int diamands)
    {
        inventory.addDiamands(diamands);
    }

    @Override
    public boolean useTool(Tool tool)
    {
        return inventory.useTool(tool);
    }

    @Override
    public int getPlayerID()
    {
        return playerID;
    }

    @Override
    public PlayerStateType getState()
    {
        return state.getPlayerStateType();
    }

    @Override
    public Position getDestination()
    {
        return destination;
    }

    @Override
    public int getWinns()
    {
        return winns;
    }

    @Override
    public int getLosses()
    {
        return losses;
    }

    @Override
    public AttackResult getPreviousDuelResult()
    {
        return previousDuelResult;
    }

    @Override
    public GetInventory getInventory()
    {
        return inventory;
    }

    @Override
    public GetBankAccount getBankAccount()
    {
        return bankAccount;
    }

    @Override
    public GetStore getStore()
    {
        return store;
    }

    @Override
    public OpponentPlayer getOpponent()
    {
        return opponent;
    }

    @Override
    public void setState(PlayerState state)
    {
        this.state = state;
    }

    @Override
    public synchronized void resetActions()
    {
        for (ActionHandlerInterface ah : actionHandlers)
        {
            ah.cancelAction(this);
        }
        actionHandlers.clear();
        this.destination = null;
        inventory.returnWeapon();
        this.opponent = null;
    }

    @Override
    public synchronized void addActionHandler(ActionHandlerInterface actionHandler)
    {
        actionHandlers.add(actionHandler);
    }

    @Override
    public void setPosition(Position position)
    {
        this.position = position;
    }

    @Override
    public boolean isGhost()
    {
        if (state.isGhost())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean revive()
    {
        System.out.println("TRY REVIVE");
        if (state.isGhost())
        {
            System.out.println("xxxRIVIVEDxxx");
            state = PlayerStateType.IDLE;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasItem(Item item)
    {
        return inventory.hasItem(item);
    }

    @Override
    public void updateID(int newID)
    {
        this.playerID = newID;
    }
}
