/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.bankAction;

import java.util.ArrayList;
import java.util.List;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.Store;
import eu.tjenwellens.bss.players.inventory.Inventory;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public class BankHandler implements BankHandlerInterface
{

    private ArrayList<BankJob> bankJobs = new ArrayList<BankJob>();

    public BankHandler()
    {
    }

    @Override
    public boolean addBankJob(BankPlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, Store store, BankAccount bankAccount)
    {
        if (transaction != null && inventory != null && bankAccount != null)
        {
            bankJobs.add(new BankJob(this, player, transaction, diamands, item, inventory, bankAccount, store));
            return true;
        }
        return false;
    }

    protected synchronized List<BankJob> copyDecorates()
    {
        return new ArrayList(bankJobs);
    }

    @Override
    public boolean updateBankJobs()
    {
        boolean result = false;
        for (BankJob decorate : copyDecorates())
        {
            result = decorate.update() | result;
        }
        return result;
    }

    @Override
    public void cancelAction(ActionPlayer player)
    {
    }

    @Override
    public void removeBankJob(BankJob decorate)
    {
        bankJobs.remove(decorate);
    }
}
