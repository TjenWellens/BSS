/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.inventory;

import java.util.List;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.players.inventory.items.Item;
import java.util.ArrayList;
import eu.tjenwellens.bss.actionhandlers.bankAction.BankAccount;

/**
 *
 * @author tjen
 */
public class SimpleInventory implements Inventory
{

    private int maxInventorySlots = 5;
    private int diamonds = 1;
    private Weapon currentWeapon;
    private ArrayList<Item> items = new ArrayList<Item>();

    public SimpleInventory()
    {
    }

    public SimpleInventory(ArrayList<Item> items, Weapon currentWeapon, int diamands)
    {
        this.items = items;
        this.currentWeapon = currentWeapon;
        this.diamonds = diamands;
    }

    @Override
    public String toString()
    {
        return "SimpleInventory{" + "diamonds=" + diamonds + ", currentWeapon=" + currentWeapon + ", items=" + items + '}';
    }

    @Override
    public boolean chooseWeapon(Weapon weapon)
    {
        if (weapon == null)
        {
            currentWeapon = null;
            return true;
        }
        if (weapon.isMadeOfDefaultMaterial() || removeItem(weapon))
        {
            currentWeapon = weapon;
            return true;
        }
        return false;
    }

    @Override
    public Weapon getWeapon()
    {
        return currentWeapon;
    }

    @Override
    public void addDiamands(int diamands)
    {
        this.diamonds += diamands;
    }

    @Override
    public int removeAllDiamands()
    {
        int returnDiamands = this.diamonds;
        this.diamonds = 0;
        return returnDiamands;
    }

    @Override
    public boolean depositItem(BankAccount bankAccount, Item item)
    {
        if (removeItem(item))
        {
            return bankAccount.depositItem(item);
        } else
        {
            return false;
        }
    }

    @Override
    public boolean retrieveItem(BankAccount bankAccount, Item item)
    {
        if (addItem(item))
        {
            if (bankAccount.retrieveItem(item))
            {
                return true;
            } else
            {
                removeItem(item);
                return false;
            }
        } else
        {
            return false;
        }
    }

    @Override
    public boolean depositDiamonds(BankAccount bankAccount, int diamonds)
    {
        return bankAccount.depositDiamonds(removeDiamonds(diamonds));
    }

    @Override
    public boolean retrieveDiamonds(BankAccount bankAccount, int diamonds)
    {
        this.addDiamands(bankAccount.retrieveDiamonds(diamonds));
        return true;
    }

    @Override
    public boolean useTool(Tool tool)
    {
        if (removeItem(tool))
        {
            return true;
        } else
        {
            System.out.println("ERROR SimpleInventory.useTool: " + tool);
            return false;
        }
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

    @Override
    public boolean emptyWeapon()
    {
        currentWeapon = null;
        return true;
    }

    @Override
    public boolean returnWeapon()
    {
        if (currentWeapon != null && !currentWeapon.isMadeOfDefaultMaterial())
        {
            addItem(currentWeapon);
        }
        currentWeapon = null;
        return true;
    }

    protected boolean addItem(Item item)
    {
        if (items.size() < maxInventorySlots)
        {
            return items.add(item);
        } else
        {
            return false;
        }
    }

    protected boolean removeItem(Item item)
    {
        return items.remove(item);
    }

    @Override
    public boolean hasItem(Item item)
    {
        return items.contains(item);
    }
}
