package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author Tjen
 */
public interface PlayInput
{
    // -bank
    public void bank(int playerID, Transaction transaction, int diamonds, Item item);

    // -chooseweapon
    public void chooseWeapon(int playerID, Weapon weapon);

    // -decorate
    public void decorate(int playerID, Decoration d, int row, int col, Tool t);

    // -engage
    public void engage(int playerID, String opponentName);

    // -idle
    public void idle(int playerID);

    // -walk
    public void walk(int playerID, Position destination);
    
    // end
    public void logout(int playerID);
}
