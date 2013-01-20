package eu.tjenwellens.bss.server.actions.bankAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.bankAction.shop.Store;
import eu.tjenwellens.bss.server.components.players.inventory.Inventory;
import eu.tjenwellens.bss.server.components.items.Item;
import java.util.ArrayList;
import java.util.List;

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
        removeBankJob(getBankJobByPlayer(player));
    }

    protected BankJob getBankJobByPlayer(ActionPlayer player)
    {
        for (BankJob bankJob : bankJobs)
        {
            if (bankJob.hasPlayer(player))
            {
                return bankJob;
            }
        }
        return null;
    }

    @Override
    public void removeBankJob(BankJob decorate)
    {
        bankJobs.remove(decorate);
    }
}
