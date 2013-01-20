package eu.tjenwellens.bss.data.commands.dataToServer;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.DataItem;

/**
 *
 * @author Tjen
 */
public class Bank extends Command
{
    private String transaction;
    private int diamonds;
    private DataItem item;

    public Bank(String transaction, int diamonds, DataItem item)
    {
        this.transaction = transaction;
        this.diamonds = diamonds;
        this.item = item;
    }

    public String getTransaction()
    {
        return transaction;
    }

    public int getDiamonds()
    {
        return diamonds;
    }

    public DataItem getItem()
    {
        return item;
    }
}
