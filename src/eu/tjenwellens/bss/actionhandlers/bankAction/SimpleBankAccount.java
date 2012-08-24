package eu.tjenwellens.bss.actionhandlers.bankAction;

import eu.tjenwellens.bss.players.inventory.items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class SimpleBankAccount implements BankAccount
{
    private int diamonds;
    private ArrayList<Item> items = new ArrayList<Item>();

    public SimpleBankAccount()
    {
    }

    @Override
    public String toString()
    {
        return "SimpleBankAccount{" + "diamonds=" + diamonds + ", items=" + items + '}';
    }

    @Override
    public boolean depositItem(Item item)
    {
        items.add(item);
        return true;
    }

    @Override
    public boolean retrieveItem(Item item)
    {
        return items.remove(item);
    }

    @Override
    public boolean depositDiamonds(int diamonds)
    {
        this.diamonds += diamonds;
        return true;
    }

    @Override
    public int retrieveDiamonds(int diamonds)
    {
        return removeDiamonds(diamonds);
    }

    @Override
    public int removeDiamonds(int diamonds)
    {
        if (this.diamonds < diamonds)
        {
            diamonds = this.diamonds;
        }
        this.diamonds -= diamonds;
        return diamonds;
    }

    @Override
    public void addDiamonds(int diamonds)
    {
        this.diamonds += diamonds;
    }

    @Override
    public boolean addItem(Item item)
    {
        return this.items.add(item);
    }

    @Override
    public boolean hasDiamonds(int diamonds)
    {
        return this.diamonds >= diamonds;
    }

    protected synchronized List<Item> copyItems()
    {
        return new ArrayList(items);
    }

    @Override
    public List<Item> getItemsCopy()
    {
        return copyItems();
    }

    @Override
    public int getDiamonds()
    {
        return diamonds;
    }
}
