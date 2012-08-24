package eu.tjenwellens.bss.actionhandlers.bankAction;

import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.Store;
import eu.tjenwellens.bss.players.inventory.Inventory;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public interface BankHandlerInterface extends ActionHandlerInterface
{
    public boolean addBankJob(BankPlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, Store store, BankAccount bankAccount);

    public void removeBankJob(BankJob bankJob);

    boolean updateBankJobs();
}
