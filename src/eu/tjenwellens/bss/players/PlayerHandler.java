/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players;

import java.util.ArrayList;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.Position;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.GetBankAccount;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.GetStore;
import eu.tjenwellens.bss.debug.Output;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackResult;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.players.inventory.GetInventory;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.players.playerstate.PlayerStateType;

/**
 *
 * @author tjen
 */
public class PlayerHandler implements PlayerHandlerInterface
{
    private HashMap<Integer, PlayerHandlerPlayer> players = new HashMap<Integer, PlayerHandlerPlayer>();
    private HashMap<String, Integer> playerIds = new HashMap<String, Integer>();
    private Faction nullFaction;
    private AttackHandlerInterface attackHandler;
    private BankHandlerInterface bankHandler;
    private DecorateHanderInterface decorateHander;
    private EngageHandlerInterface engageHandler;
    private WalkHandlerInterface walkHandler;

    @Override
    public void setAttackHandler(AttackHandlerInterface attackHandler)
    {
        this.attackHandler = attackHandler;
    }

    @Override
    public void setBankHandler(BankHandlerInterface bankHandler)
    {
        this.bankHandler = bankHandler;
    }

    @Override
    public void setDecorateHander(DecorateHanderInterface decorateHander)
    {
        this.decorateHander = decorateHander;
    }

    @Override
    public void setEngageHandler(EngageHandlerInterface engageHandler)
    {
        this.engageHandler = engageHandler;
    }

    @Override
    public void setWalkHandler(WalkHandlerInterface walkHandler)
    {
        this.walkHandler = walkHandler;
    }

    public PlayerHandler(Faction nullFaction)
    {
        this.nullFaction = nullFaction;
    }

    @Override
    public boolean playerExists(int id)
    {
        return players.containsKey(id);
    }

    @Override
    public boolean playerExists(String playerName)
    {
        return playerIds.containsKey(playerName);
    }

    protected PlayerHandlerPlayer getLocalPlayer(int id)
    {
        return players.get(id);
    }

    @Override
    public int getPlayerID(String playerName)
    {
        return playerIds.get(playerName);
    }

    protected PlayerHandlerPlayer getPlayerByName(String playerName)
    {
        return players.get(playerIds.get(playerName));
    }

    @Override
    public String toString()
    {
        String returnStr = "PLAYERS: \n";
        for (PlayerHandlerPlayer p : players.values())
        {
            returnStr += "Player:" + p.toString() + "\n";
        }
        return returnStr;
    }

    @Override
    public boolean walk(int id, Position destination)
    {
        if (playerExists(id))
        {
            PlayerHandlerPlayer player = getLocalPlayer(id);
            if (player != null && destination != null)
            {
                player.walk(destination, walkHandler);
                return true;
            } else
            {
                System.out.println("PlayerHandler.walk error");
            }

        }
        return false;
    }

    @Override
    public boolean idle(int id)
    {
        if (playerExists(id))
        {
            PlayerHandlerPlayer player = getLocalPlayer(id);
            if (player != null)
            {
                player.idle();
                return true;
            } else
            {
                System.out.println("PlayerHandler.idle error");
            }
        }
        return false;
    }

    @Override
    public boolean engage(int playerId, String opponentName)
    {
        if (playerExists(playerId) && playerExists(opponentName))
        {
            PlayerHandlerPlayer player = getLocalPlayer(playerId);
            PlayerHandlerPlayer opponent = getPlayerByName(opponentName);
            if (player != null && opponent != null)
            {
                player.engage(opponent, engageHandler);
                return true;
            } else
            {
                Output.add("Model.engagecommand", "player(" + player + ") or opponent(" + opponent + ") is null");
            }
        } else
        {
            Output.add("Model.engagecommand", "player(" + playerId + ") or opponent(" + opponentName + ") doesn't exist");
        }
        return false;
    }

    @Override
    public boolean decorate(int playerId, Decoration decoration, Position location, Tool tool)
    {
        if (playerExists(playerId))
        {
            PlayerHandlerPlayer player = getLocalPlayer(playerId);
            if (player != null && decoration != null && location != null)
            {
                player.decorate(decoration, location, tool, decorateHander);
                return true;
            } else
            {
                System.out.println("PlayerHandler.decorate error");
            }
        }
        return false;
    }

    @Override
    public boolean bank(int playerId, Transaction transaction, int diamands, Item item)
    {
        if (playerExists(playerId))
        {
            PlayerHandlerPlayer player = getLocalPlayer(playerId);
            if (player != null && transaction != null)
            {
                player.bank(transaction, diamands, item, bankHandler);
                return true;
            } else
            {
                System.out.println("PlayerHandler.bank error");
            }
        }
        return false;
    }

    @Override
    public boolean chooseWeapon(int playerId, Weapon weapon)
    {
        if (playerExists(playerId))
        {
            PlayerHandlerPlayer player = getLocalPlayer(playerId);
            if (player != null && weapon != null)
            {
                player.chooseWeapon(weapon);
                return true;
            } else
            {
                System.out.println("PlayerHandler.chooseWeapon error");
            }
        }
        return false;
    }

    @Override
    public boolean createPlayer(int id, String playerName, Faction faction, Position position)
    {
        PlayerHandlerPlayer player = players.get(id);

        if (player == null)
        {
            //Todo give faction with creation
            player = new Player(id, playerName, faction, position);
            players.put(id, player);
            playerIds.put(playerName, id);
            System.out.println("created");
            return true;
        }
        System.out.println("failed to create");
        return false;
    }

    protected synchronized List<PlayerHandlerPlayer> copyPlayers()
    {
        return new ArrayList(players.values());
    }

    @Override
    public boolean updatePlayers()
    {
        boolean result = false;
        // TODO: is PH.update ooit nodig?
        return result;
    }

    @Override
    public HashMap<Integer, WalkObstaclePlayer> getWalkObstaclesCopy()
    {
        HashMap<Integer, WalkObstaclePlayer> playersCopy = new HashMap<Integer, WalkObstaclePlayer>(players.size(), 1f);
        for (Iterator<PlayerHandlerPlayer> playerIterator = copyPlayers().iterator(); playerIterator.hasNext();)
        {
            PlayerHandlerPlayer player = playerIterator.next();
            playersCopy.put(player.getPlayerID(), player);
        }
        return playersCopy;
    }

    @Override
    public HashMap<String, GetPlayer> getPlayersCopy()
    {
        HashMap<String, GetPlayer> playersCopy = new HashMap<String, GetPlayer>(players.size(), 1f);
        for (Iterator<PlayerHandlerPlayer> playerIterator = copyPlayers().iterator(); playerIterator.hasNext();)
        {
            PlayerHandlerPlayer player = playerIterator.next();
            playersCopy.put(player.getPlayerName(), new ImmutableGetPlayer(player));
        }
        return playersCopy;
    }

    private class ImmutableGetPlayer implements GetPlayer
    {
        private int playerID;
        private String playerName;
        private Position position;
        private Faction faction;
        private PlayerStateType state;
        // walk
        private Position destination;
        // battleresult
        private int winns = 0;
        private int losses = 0;
        private AttackResult previousDuelResult;
        // bank
        private GetInventory inventory;
        private GetBankAccount bankAccount;
        private GetStore store;
        // battle
        private OpponentPlayer opponent;

        public ImmutableGetPlayer(GetPlayer player)
        {
            this.playerID = player.getPlayerID();
            this.playerName = player.getPlayerName();
            this.position = player.getPosition();
            this.faction = player.getFaction();
            this.state = player.getState();
            this.destination = player.getDestination();
            this.winns = player.getWinns();
            this.losses = player.getLosses();
            this.previousDuelResult = player.getPreviousDuelResult();
            this.inventory = player.getInventory();
            this.bankAccount = player.getBankAccount();
            this.store = player.getStore();
            this.opponent = player.getOpponent();
        }

        @Override
        public int hashCode()
        {
            return playerID;
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
            final GetPlayer other = (GetPlayer) obj;
            if (this.playerID != other.getPlayerID())
            {
                return false;
            }
            return true;
        }

        @Override
        public String toString()
        {
            return "ImmutablePlayer{" + "playerID=" + playerID + ", playerName=" + playerName + ", position=" + position + ", faction=" + faction + ", state=" + state + ", destination=" + destination + ", winns=" + winns + ", losses=" + losses + ", previousDuelResult=" + previousDuelResult + ", inventory=" + inventory + ", bankAccount=" + bankAccount + ", store=" + store + ", opponent=" + opponent + '}';
        }

        @Override
        public GetBankAccount getBankAccount()
        {
            return bankAccount;
        }

        @Override
        public Position getDestination()
        {
            return destination;
        }

        @Override
        public Faction getFaction()
        {
            return faction;
        }

        @Override
        public GetInventory getInventory()
        {
            return inventory;
        }

        @Override
        public int getLosses()
        {
            return losses;
        }

        @Override
        public OpponentPlayer getOpponent()
        {
            return opponent;
        }

        @Override
        public int getPlayerID()
        {
            return playerID;
        }

        @Override
        public String getPlayerName()
        {
            return playerName;
        }

        @Override
        public Position getPosition()
        {
            return position;
        }

        @Override
        public AttackResult getPreviousDuelResult()
        {
            return previousDuelResult;
        }

        @Override
        public PlayerStateType getState()
        {
            return state;
        }

        @Override
        public GetStore getStore()
        {
            return store;
        }

        @Override
        public int getWinns()
        {
            return winns;
        }
    }
}
