package eu.tjenwellens.bss.server.actions.bankAction.shop;

import eu.tjenwellens.bss.server.components.items.Item;

/**
 *
 * @author tjen
 */
public interface GetStore
{
    /** player <-> shop */
    int getPrice(Item item);
}
