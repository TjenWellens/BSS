package eu.tjenwellens.bss.server.actions.bankAction.shop;

import eu.tjenwellens.bss.server.components.items.Item;

/**
 *
 * @author tjen
 */
public interface StorePlayer
{
    boolean buyItem(StorePlayer player, Item item);
}
