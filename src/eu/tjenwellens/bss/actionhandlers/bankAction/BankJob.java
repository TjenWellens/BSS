package eu.tjenwellens.bss.actionhandlers.bankAction;

import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.actionhandlers.HasPlayer;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.Store;
import eu.tjenwellens.bss.players.inventory.Inventory;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public class BankJob implements HasPlayer
{
    private BankHandlerInterface bankHandler;
    private BankPlayer player;
    private Transaction transaction;
    private int diamands;
    private Item item;
    private Inventory inventory;
    private BankAccount bankAccount;
    private Store store;

    public BankJob(BankHandlerInterface bankHandler, BankPlayer player, Transaction transaction, int diamands, Item item, Inventory inventory, BankAccount bankAccount, Store store)
    {
        this.bankHandler = bankHandler;
        this.player = player;
        this.transaction = transaction;
        this.diamands = diamands;
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
                result = false;
                if (diamands > 0)
                {
                    if (inventory.depositDiamonds(bankAccount, diamands))
                    {
                        System.out.println("Diamonds deposited: " + diamands);
                        result = true;
                    } else
                    {
                        System.out.println("Deposit diamands failed: " + diamands);
                    }
                }
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
                result = false;
                if (diamands > 0)
                {
                    if (inventory.retrieveDiamonds(bankAccount, diamands))
                    {
                        System.out.println("Diamonds retrieved: " + diamands);
                        result = true;
                    } else
                    {
                        System.out.println("Retrieve diamonds failed: " + diamands);
                    }
                }
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
                if (diamands <= 0)
                {
                    result = false;
                    break;
                }
                if (item == null)
                {
                    result = false;
                    break;
                }
                // TODO: remove
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
            default:
                result = false;
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
