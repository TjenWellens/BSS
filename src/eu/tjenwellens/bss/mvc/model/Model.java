package eu.tjenwellens.bss.mvc.model;

import java.util.HashMap;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.GetTile;
import eu.tjenwellens.bss.mvc.observe.ModelObservable;
import java.util.ArrayList;
import java.util.List;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.mvc.TickObserver;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandler;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandler;
import eu.tjenwellens.bss.factions.FactionHandler;
import eu.tjenwellens.bss.factions.FactionHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHandler;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandler;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandler;
import eu.tjenwellens.bss.factions.Kleur;
import eu.tjenwellens.bss.map.MapHandler;
import eu.tjenwellens.bss.map.MapHandlerInterface;
import eu.tjenwellens.bss.mvc.observe.FactionObserver;
import eu.tjenwellens.bss.mvc.observe.GlobalObserver;
import eu.tjenwellens.bss.mvc.observe.MapObserver;
import eu.tjenwellens.bss.mvc.observe.PlayerObserver;
import eu.tjenwellens.bss.players.GetPlayer;
import eu.tjenwellens.bss.players.PlayerHandler;
import eu.tjenwellens.bss.players.PlayerHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

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
    protected ArrayList<GlobalObserver> globalObservers = new ArrayList<GlobalObserver>();
    protected ArrayList<PlayerObserver> playerObservers = new ArrayList<PlayerObserver>();
    protected ArrayList<MapObserver> mapObservers = new ArrayList<MapObserver>();
    protected ArrayList<FactionObserver> factionObservers = new ArrayList<FactionObserver>();
    protected volatile boolean notify = false;

    private Model()
    {
        attackHandler = new AttackHandler();
        factionHandler = new FactionHandler();
        factionHandler.addFaction("rood", Kleur.ROOD);
        factionHandler.addFaction("geel", Kleur.GEEL);
        factionHandler.addFaction("blauw", Kleur.BLAUW);
        playerHandler = new PlayerHandler(factionHandler.getNullFaction());
        mapHandler = new MapHandler(factionHandler.getNullFaction());
        walkHandler = new WalkHandler(playerHandler, mapHandler);
        decorateHander = new DecorateHandler(mapHandler);
        bankHandler = new BankHandler();
        engageHandler = new EngageHandler(attackHandler);

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removePlayersObserver(PlayerObserver o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerMapObserver(MapObserver o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeMapObserver(MapObserver o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerFactionObserver(FactionObserver o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeFactionObserver(FactionObserver o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public void decorateCommand(int playerId, Decoration decoration, Position location, Tool tool)
    {
        notify = playerHandler.decorate(playerId, decoration, location, tool) || notify;
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
        Faction faction = factionHandler.getFactionByName(factionName);
        if (faction == null || faction.equals(factionHandler.getNullFaction()))
        {
            System.out.println("No such faction");
        } else
        {
            notify = playerHandler.createPlayer(id, playerName, faction, position) || notify;
        }
    }
    //</editor-fold>

    @Override
    public GetTile[][] getMap()
    {
        return mapHandler.getMapCopy();
    }

    @Override
    public HashMap<String, GetPlayer> getPlayers()
    {
        return playerHandler.getPlayersCopy();
    }

    @Override
    public List<Faction> getFactions()
    {
        return factionHandler.getFactionsCopy();
    }
}
