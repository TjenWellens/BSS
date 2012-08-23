/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.inventory;

import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankAccount;

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

    // win or lose
    void addDiamands(int diamands);

    int removeDiamonds(int diamonds);

    int removeAllDiamands();

    // state <-> inventory
    Weapon getWeapon();

    public boolean emptyWeapon();

    public boolean returnWeapon();
}
