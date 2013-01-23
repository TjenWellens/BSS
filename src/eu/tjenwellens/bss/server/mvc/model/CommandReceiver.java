package eu.tjenwellens.bss.server.mvc.model;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author tjen
 */
public interface CommandReceiver
{
    void walkCommand(int playerId, Position destination);

    void idleCommand(int playerId);

    void engageCommand(int playerId, String opponentName);

    void decorateCommand(int playerId, Decoration decoration, int row, int col, Tool tool);

    void bankCommand(int playerId, Transaction transaction, int diamands, Item item);

    void chooseWeaponCommand(int playerId, Weapon weapon);

    void logoutPlayerCommand(int id);

    public void updateIDCommand(int id, int newID);

    // init
    void loginPlayerCommand(InitPlayer initPlayer);

    void selectFactionCommand(int id, String factionName);

    void selectPositionCommand(int id, int x, int y);

    void playCommand(int id);
}
