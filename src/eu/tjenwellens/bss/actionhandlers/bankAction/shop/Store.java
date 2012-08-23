/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.bankAction.shop;

import eu.tjenwellens.bss.actionhandlers.bankAction.BankAccount;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public interface Store extends GetStore
{
    //player <-bankaccount-> shop
    boolean buyItem(BankAccount bankAccount, Item item);
}
