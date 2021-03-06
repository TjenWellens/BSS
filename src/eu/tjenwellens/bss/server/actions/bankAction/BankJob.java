package eu.tjenwellens.bss.server.actions.bankAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.actions.HasPlayer;
import eu.tjenwellens.bss.server.actions.bankAction.shop.Store;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.players.inventory.Inventory;

/**
 *
 * @author tjen
 */
public class BankJob implements HasPlayer
{
    private BankHandlerInterface bankHandler;
    private BankPlayer player;
    private Transaction transaction;
    private int diamonds;
    private Item item;
    private Inventory inventory;
    private BankAccount bankAccount;
    private Store store;

    public BankJob(BankHandlerInterface bankHandler, BankPlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, BankAccount bankAccount, Store store)
    {
        this.bankHandler = bankHandler;
        this.player = player;
        this.transaction = transaction;
        this.diamonds = diamands;
        this.item = item;
        this.inventory = inventory;
        this.bankAccount = bankAccount;
        this.store = store;
    }

    boolean update()
    {
        boolean result = false;
        switch (transaction)
        {
            case DEPOSIT:
                // diamonds to deposit?
                if (diamonds > 0)
                {
                    if (inventory.depositDiamonds(bankAccount, diamonds))
                    {
                        System.out.println("Diamonds deposited: " + diamonds);
                        result = true;
                    } else
                    {
                        System.out.println("Deposit diamands failed: " + diamonds);
                    }
                }
                // item to deposit?
                if (item != null)
                {
                    if (inventory.depositItem(bankAccount, item))
                    {
                        System.out.println("Item deposited: " + item);
                        result = true;
                    } else
                    {
                        System.out.println("Deposit item failed: " + item);
                    }
                }
                break;
            case RETRIEVE:
                // diamonds to retrieve?
                if (diamonds > 0)
                {
                    if (inventory.retrieveDiamonds(bankAccount, diamonds))
                    {
                        System.out.println("Diamonds retrieved: " + diamonds);
                        result = true;
                    } else
                    {
                        System.out.println("Retrieve diamonds failed: " + diamonds);
                    }
                }
                // item to retrieve?
                if (item != null)
                {
                    if (inventory.retrieveItem(bankAccount, item))
                    {
                        System.out.println("Item retrieved: " + item);
                        result = true;
                    } else
                    {
                        System.out.println("Retrieve item failed: " + item);
                    }
                }
                break;
            case SHOP:
                if (diamonds <= 0)
                {
                    result = false;
                    break;
                }
                if (item == null)
                {
                    result = false;
                    break;
                }
                // TODO: remove free diamonds
                bankAccount.addDiamonds(10000);
                if (store.buyItem(bankAccount, item))
                {
                    System.out.println("Item bought: " + item);
                    result = true;
                } else
                {
                    System.out.println("Shopping error");
                }
                break;
        }
        bankHandler.removeBankJob(this);
        return result;
    }

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && p.equals(player);
    }
}
