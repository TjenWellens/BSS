package eu.tjenwellens.bss.server.mvc.model;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackHandler;
import eu.tjenwellens.bss.server.actions.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandler;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHandler;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandler;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandler;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.factions.FactionHandler;
import eu.tjenwellens.bss.server.components.factions.FactionHandlerInterface;
import eu.tjenwellens.bss.server.components.factions.Kleur;
import eu.tjenwellens.bss.server.components.map.GetMap;
import eu.tjenwellens.bss.server.components.map.MapHandler;
import eu.tjenwellens.bss.server.components.map.MapHandlerInterface;
import eu.tjenwellens.bss.server.mvc.TickObserver;
import eu.tjenwellens.bss.server.mvc.model.observers.FactionObserver;
import eu.tjenwellens.bss.server.mvc.model.observers.GlobalObserver;
import eu.tjenwellens.bss.server.mvc.model.observers.MapObserver;
import eu.tjenwellens.bss.server.mvc.model.observers.ModelObservable;
import eu.tjenwellens.bss.server.mvc.model.observers.PlayerObserver;
import eu.tjenwellens.bss.server.components.players.GetPlayer;
import eu.tjenwellens.bss.server.components.players.PlayerHandler;
import eu.tjenwellens.bss.server.components.players.PlayerHandlerInterface;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tjen
 */
public class Model implements ModelObservable, TickObserver, CommandReceiverInterface, GetModelData
{
    protected static Model model = new Model();
    // handlers
    protected PlayerHandlerInterface playerHandler;
    protected AttackHandlerInterface attackHandler;
    protected FactionHandlerInterface factionHandler;
    protected MapHandlerInterface mapHandler;
    protected WalkHandlerInterface walkHandler;
    protected DecorateHanderInterface decorateHander;
    protected EngageHandlerInterface engageHandler;
    protected BankHandlerInterface bankHandler;
    // Observers
    protected ArrayList<GlobalObserver> globalObservers = new ArrayList<>();
    protected ArrayList<PlayerObserver> playerObservers = new ArrayList<>();
    /**
     *
     */
    protected ArrayList<MapObserver> mapObservers = new ArrayList<>();
    protected ArrayList<FactionObserver> factionObservers = new ArrayList<>();
    protected volatile boolean notify = false;

    private Model()
    {
        // init handlers
        attackHandler = new AttackHandler();
        factionHandler = new FactionHandler();
        playerHandler = new PlayerHandler(factionHandler.getNullFaction());
        mapHandler = new MapHandler(factionHandler.getNullFaction());
        walkHandler = new WalkHandler(playerHandler, mapHandler);
        decorateHander = new DecorateHandler(mapHandler);
        bankHandler = new BankHandler();
        engageHandler = new EngageHandler(attackHandler);
        // init test factions
        factionHandler.addFaction("rood", Kleur.ROOD);
        factionHandler.addFaction("geel", Kleur.GEEL);
        factionHandler.addFaction("blauw", Kleur.BLAUW);
        // init playerhandler's - pass-on to player -
        playerHandler.setAttackHandler(attackHandler);
        playerHandler.setBankHandler(bankHandler);
        playerHandler.setDecorateHander(decorateHander);
        playerHandler.setEngageHandler(engageHandler);
        playerHandler.setWalkHandler(walkHandler);
    }

    public static Model getInstance()
    {
        return model;
    }

    @Override
    public String toString()
    {
        return playerHandler.toString();
    }

    //<editor-fold defaultstate="collapsed" desc="observer functions">
    @Override
    public void registerPlayersObserver(PlayerObserver o)
    {
        playerObservers.add(o);
    }

    @Override
    public void removePlayersObserver(PlayerObserver o)
    {
        playerObservers.remove(o);
    }

    @Override
    public void registerMapObserver(MapObserver o)
    {
        mapObservers.add(o);
    }

    @Override
    public void removeMapObserver(MapObserver o)
    {
        mapObservers.remove(o);
    }

    @Override
    public void registerFactionObserver(FactionObserver o)
    {
        factionObservers.add(o);
    }

    @Override
    public void removeFactionObserver(FactionObserver o)
    {
        factionObservers.remove(o);
    }

    @Override
    public void registerGlobalObserver(GlobalObserver o)
    {
        globalObservers.add(o);
    }

    @Override
    public void removeGlobalObserver(GlobalObserver o)
    {
        globalObservers.remove(o);
    }

    protected void notifyGlobalObservers()
    {
        for (GlobalObserver observer : globalObservers)
        {
            observer.notifyGlobalObserver();
        }
    }

    protected void notifyPlayerObservers()
    {
        for (PlayerObserver observer : playerObservers)
        {
            observer.notifyPlayerObserver();
        }
    }

    protected void notifyMapObservers()
    {
        for (MapObserver observer : mapObservers)
        {
            observer.notifyMapObserver();
        }
    }

    protected void notifyFactionObservers()
    {
        for (FactionObserver observer : factionObservers)
        {
            observer.notifyFactionObserver();
        }
    }
    //</editor-fold>

    @Override
    public void tick()
    {
        notify = playerHandler.updatePlayers() || notify;
        notify = attackHandler.updateDuels() || notify;


        notify = playerHandler.updatePlayers() || notify;
        notify = walkHandler.updateWalks() || notify;
        notify = decorateHander.updateDecorates() || notify;
        notify = engageHandler.updateEngages() || notify;
        notify = bankHandler.updateBankJobs() || notify;
//        if (notify)
        {
            notify = false;
            notifyGlobalObservers();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="player commands">
    @Override
    public void walkCommand(int id, Position destination)
    {
        notify = playerHandler.walk(id, destination) || notify;
    }

    @Override
    public void idleCommand(int id)
    {
        notify = playerHandler.idle(id) || notify;
    }

    @Override
    public void engageCommand(int id, String opponentName)
    {
        notify = playerHandler.engage(id, opponentName) || notify;
    }

    @Override
    public void decorateCommand(int playerId, Decoration decoration, int row, int col, Tool tool)
    {
        notify = playerHandler.decorate(playerId, decoration, row, col, tool) || notify;
    }

    @Override
    public void bankCommand(int playerId, Transaction transaction, int diamands, Item item)
    {
        notify = playerHandler.bank(playerId, transaction, diamands, item) || notify;
    }

    @Override
    public void chooseWeaponCommand(int id, Weapon weapon)
    {
        notify = playerHandler.chooseWeapon(id, weapon) || notify;
    }

    @Override
    public void createPlayerCommand(int id, String playerName, String factionName, Position position)
    {
        System.out.println("Model.createPlayer");
        Faction faction = factionHandler.getFactionByName(factionName);
        if (faction == null || faction.equals(factionHandler.getNullFaction()))
        {
            System.out.println("No such faction, player not created");
        } else
        {
            notify = playerHandler.createPlayer(id, playerName, faction, position) || notify;
        }
    }

    @Override
    public void loadPlayerCommand(int id, String playerName, int winns, int losses, String factionName, Position position)
    {
        System.out.println("Model.loadPlayer");
        Faction faction = factionHandler.getFactionByName(factionName);
        if (faction == null || faction.equals(factionHandler.getNullFaction()))
        {
            System.out.println("No such faction, player not loaded");
        } else
        {
            notify = playerHandler.loadPlayer(id, playerName, winns, losses, faction, position) || notify;
        }
    }

    @Override
    public void saveAndLogoutPlayerCommand(int id, PlayerSaver saver)
    {
        System.out.println("Model.saveAndLogoutPlayerCommand");
        notify = playerHandler.saveAndLogoutPlayer(id, saver) || notify;
    }

    @Override
    public void logoutPlayerCommand(int id)
    {
        System.out.println("Model.logoutPlayerCommand");
        notify = playerHandler.logoutPlayer(id) || notify;
    }

    @Override
    public void updateIDCommand(int id, int newID)
    {
        System.out.println("Model.updateID");
        notify = playerHandler.updatePlayerID(id, newID) || notify;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters for view">
    @Override
    public GetMap getMap()
    {
        return mapHandler.getMapCopy();
    }

    @Override
    public HashMap<Integer, GetPlayer> getPlayers()
    {
        return playerHandler.getPlayersCopy();
    }

    @Override
    public List<Faction> getFactions()
    {
        return factionHandler.getFactionsCopy();
    }
    //</editor-fold>
}
