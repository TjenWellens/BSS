package eu.tjenwellens.bss.client.communication;

import eu.tjenwellens.bss.client.action.Decoration;
import eu.tjenwellens.bss.client.action.Transaction;
import eu.tjenwellens.bss.client.components.items.Item;
import eu.tjenwellens.bss.client.components.items.Tool;
import eu.tjenwellens.bss.client.components.items.Weapon;

/**
 *
 * @author Tjen
 */
interface PlayCommunication {
    // -bank
    public void bank(Transaction transaction, int diamonds, Item item);

    // -chooseweapon
    public void chooseWeapon(Weapon weapon);

    // -decorate
    public void decorate(Decoration d, int row, int col, Tool t);

    // -engage
    public void engage(String opponentName);

    // -idle
    public void idle();

    // -walk
    public void walk(int x, int y);
}
