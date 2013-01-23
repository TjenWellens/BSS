package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.actions.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.actions.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.components.Position;
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

    boolean addPlayer(InitPlayer initPlayer);

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

    public boolean logoutPlayer(int id);

    public boolean updatePlayerID(int id, int newID);
}
