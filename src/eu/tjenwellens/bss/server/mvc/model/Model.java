package eu.tjenwellens.bss.server.mvc.model;

import eu.tjenwellens.bss.server.communication.init.InitPlayer;
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
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.factions.FactionHandler;
import eu.tjenwellens.bss.server.components.factions.FactionHandlerInterface;
import eu.tjenwellens.bss.server.components.factions.Kleur;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.components.map.GetMap;
import eu.tjenwellens.bss.server.components.map.MapHandler;
import eu.tjenwellens.bss.server.components.map.MapHandlerInterface;
import eu.tjenwellens.bss.server.components.players.GetPlayer;
import eu.tjenwellens.bss.server.components.players.PlayerHandler;
import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.update.Updatable;
import eu.tjenwellens.update.Updater;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class Model implements Updater, Updatable, CommandReceiver, GetModelData
{
    protected static Model model = new Model();
    // handlers
    protected PlayerHandler playerHandler;
    protected AttackHandlerInterface attackHandler;
    protected FactionHandlerInterface factionHandler;
    protected MapHandlerInterface mapHandler;
    protected WalkHandlerInterface walkHandler;
    protected DecorateHanderInterface decorateHander;
    protected EngageHandlerInterface engageHandler;
    protected BankHandlerInterface bankHandler;
    // Observers
    private Collection<Updatable> observers = new LinkedList<>();
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
    public boolean registerUpdatable(Updatable u)
    {
        return observers.add(u);
    }

    @Override
    public boolean removeUpdatable(Updatable u)
    {
        return observers.remove(u);
    }

    protected void notifyObservers()
    {
        for (Updatable u : observers)
        {
            u.update();
        }
    }
    //</editor-fold>

    @Override
    public void update()
    {
        notify = playerHandler.updatePlayers() || notify;
        notify = attackHandler.updateDuels() || notify;


        notify = playerHandler.updatePlayers() || notify;
        notify = walkHandler.updateWalks() || notify;
        notify = decorateHander.updateDecorates() || notify;
        notify = engageHandler.updateEngages() || notify;
        notify = bankHandler.updateBankJobs() || notify;
        if (notify)
        {
            notify = false;
            notifyObservers();
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="init player">
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
            InitPlayer ip = new InitPlayer(id, playerName);
            ip.setPosition(position);
            ip.setFaction(faction);
            notify = playerHandler.createPlayer(ip) || notify;
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
            InitPlayer ip = new InitPlayer(id, playerName);
            ip.setPosition(position);
            ip.setFaction(faction);
            ip.setWinns(winns);
            ip.setLosses(losses);
            notify = playerHandler.loadPlayer(ip) || notify;
        }
    }

    @Override
    public void saveAndLogoutPlayerCommand(int id, PlayerSaver saver)
    {
        System.out.println("Model.saveAndLogoutPlayerCommand");
        notify = playerHandler.saveAndLogoutPlayer(id, saver) || notify;
    }
    //</editor-fold>

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
