package eu.tjenwellens.bss.server.mvc.model;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author tjen
 */
public interface CommandReceiverInterface
{
    void walkCommand(int playerId, Position destination);

    void idleCommand(int playerId);

    void engageCommand(int playerId, String opponentName);

    void decorateCommand(int playerId, Decoration decoration, int row, int col, Tool tool);

    void bankCommand(int playerId, Transaction transaction, int diamands, Item item);

    void chooseWeaponCommand(int playerId, Weapon weapon);

    void createPlayerCommand(int id, String playerName, String factionName, Position position);

    void loadPlayerCommand(int id, String playerName, int winns, int losses, String factionName, Position position);
    
    void saveAndLogoutPlayerCommand(int id, PlayerSaver saver);
    
    void logoutPlayerCommand(int id);

    public void updateIDCommand(int id, int newID);
}
