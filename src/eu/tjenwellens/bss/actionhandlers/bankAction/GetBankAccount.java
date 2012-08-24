package eu.tjenwellens.bss.actionhandlers.bankAction;

import eu.tjenwellens.bss.players.inventory.items.Item;
import java.util.List;

/**
 *
 * @author tjen
 */
public interface GetBankAccount
{
    int getDiamonds();

    List<Item> getItemsCopy();
}
