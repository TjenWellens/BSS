package eu.tjenwellens.bss.server.actions.bankAction.shop;

import eu.tjenwellens.bss.server.actions.bankAction.BankAccount;
import eu.tjenwellens.bss.server.components.items.Item;

/**
 *
 * @author tjen
 */
public interface Store extends GetStore
{
    /** player <-bankaccount-> shop */
    boolean buyItem(BankAccount bankAccount, Item item);
}
