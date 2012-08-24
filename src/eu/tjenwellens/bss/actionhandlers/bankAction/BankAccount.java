package eu.tjenwellens.bss.actionhandlers.bankAction;

import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public interface BankAccount extends GetBankAccount
{
    // player <-> bank
    boolean depositItem(Item item);

    boolean retrieveItem(Item item);

    boolean depositDiamonds(int diamonds);

    int retrieveDiamonds(int diamonds);

    // bank <-> shop
    void addDiamonds(int diamonds);

    int removeDiamonds(int diamonds);

    boolean addItem(Item item);

    boolean hasDiamonds(int diamonds);
}
