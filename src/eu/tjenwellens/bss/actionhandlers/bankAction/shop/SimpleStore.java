package eu.tjenwellens.bss.actionhandlers.bankAction.shop;

import eu.tjenwellens.bss.actionhandlers.bankAction.BankAccount;
import eu.tjenwellens.bss.debug.Output;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.ItemFactory;

/**
 *
 * @author tjen
 */
public class SimpleStore implements Store
{
    private static SimpleStore store = new SimpleStore();

    private SimpleStore()
    {
    }

    public static SimpleStore getInstance()
    {
        return store;
    }

    @Override
    public boolean buyItem(BankAccount bankAccount, Item item)
    {
        if (item == null)
        {
            System.out.println("No item");
            return false;
        }
        int price = ItemFactory.getPrice(item.getId());
        if (price < 0)
        {
            return false;
        }
        if (!bankAccount.hasDiamonds(price))
        {
            System.out.println("Costs too much" + price + bankAccount);
            return false;
        }
        if (!bankAccount.addItem(item))
        {
            return false;
        }
        // all good
        bankAccount.removeDiamonds(price);
        Output.add("SimpleStore", "item gekocht: " + item);
        return true;
    }

    @Override
    public int getPrice(Item item)
    {
        if (item == null)
        {
            System.out.println("No item");
            return -1;
        } else
        {
            return ItemFactory.getPrice(item.getId());
        }
    }

    @Override
    public String toString()
    {
        return "SimpleStore";
    }
}
