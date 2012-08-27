package eu.tjenwellens.bss.players;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
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
}
