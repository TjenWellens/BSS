package eu.tjenwellens.bss.server.actions.bankAction;

import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.shop.Store;
import eu.tjenwellens.bss.server.components.players.inventory.Inventory;
import eu.tjenwellens.bss.server.components.items.Item;

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
