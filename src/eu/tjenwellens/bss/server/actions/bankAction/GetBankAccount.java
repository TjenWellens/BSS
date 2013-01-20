package eu.tjenwellens.bss.server.actions.bankAction;

import eu.tjenwellens.bss.server.components.items.Item;
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
