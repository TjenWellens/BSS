package eu.tjenwellens.bss.server.components.players.inventory;

import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.actions.bankAction.BankAccount;

/**
 *
 * @author tjen
 */
public interface Inventory extends GetInventory
{
    // player <-> bank
    boolean depositItem(BankAccount bankAccount, Item item);

    boolean retrieveItem(BankAccount bankAccount, Item item);

    boolean depositDiamonds(BankAccount bankAccount, int diamonds);

    boolean retrieveDiamonds(BankAccount bankAccount, int diamonds);

    // player <-> inventory
    boolean chooseWeapon(Weapon weapon);

    boolean useTool(Tool tool);

    boolean hasItem(Item item);

    // win or lose
    void addDiamands(int diamands);

    int removeDiamonds(int diamonds);

    int removeAllDiamands();

    // state <-> inventory
    Weapon getWeapon();

    public boolean emptyWeapon();

    public boolean returnWeapon();
}
