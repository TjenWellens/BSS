package eu.tjenwellens.bss.serverVSclient.communication.dataToClient.inventory;

import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author Tjen
 */
public interface DataStore
{
    int getPrice(Item item);
}
