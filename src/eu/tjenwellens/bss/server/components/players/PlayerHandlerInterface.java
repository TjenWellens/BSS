package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import java.util.HashMap;

/**
 *
 * @author tjen
 */
public interface PlayerHandlerInterface
{
    boolean walk(int playerId, Position destination);

    boolean idle(int playerId);

    boolean engage(int playerId, String opponentName);

    boolean decorate(int playerId, Decoration decoration, int row, int col, Tool tool);

    boolean bank(int playerId, Transaction transaction, int diamands, Item item);

    boolean chooseWeapon(int playerId, Weapon weapon);

    boolean createPlayer(int id, String playerName, Faction faction, Position position);

    boolean loadPlayer(int id, String playerName, int winns, int losses, Faction faction, Position position);

    HashMap<Integer, GetPlayer> getPlayersCopy();

    HashMap<Integer, WalkObstaclePlayer> getWalkObstaclesCopy();

    public boolean playerExists(int playerId);

    public boolean playerExists(String playerName);

    public int getPlayerID(String playerName);

    public boolean updatePlayers();

    public void setAttackHandler(AttackHandlerInterface attackHandler);

    public void setBankHandler(BankHandlerInterface bankHandler);

    public void setDecorateHander(DecorateHanderInterface decorateHander);

    public void setEngageHandler(EngageHandlerInterface engageHandler);

    public void setWalkHandler(WalkHandlerInterface walkHandler);

    public boolean saveAndLogoutPlayer(int id, PlayerSaver saver);

    public boolean logoutPlayer(int id);

    public boolean updatePlayerID(int id, int newID);
}
