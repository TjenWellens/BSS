package eu.tjenwellens.bss.serverVSclient.communication.dataToClient.inventory;

import eu.tjenwellens.bss.players.inventory.items.Item;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface DataBankAccount
{
    int getDiamonds();

    List<Item> getItemsCopy();
}
