package eu.tjenwellens.bss.mvc.model;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.GetPlayer;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

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
}
