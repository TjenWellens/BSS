package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class Bank extends Command
{
    private Transaction transaction;
    private int diamonds;
    private Item item;

    public Bank(Transaction transaction, int diamonds, Item item)
    {
        this.transaction = transaction;
        this.diamonds = diamonds;
        this.item = item;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public int getDiamonds()
    {
        return diamonds;
    }

    public Item getItem()
    {
        return item;
    }
}
