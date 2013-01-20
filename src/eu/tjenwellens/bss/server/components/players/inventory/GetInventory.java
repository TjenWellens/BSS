package eu.tjenwellens.bss.server.components.players.inventory;

import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Weapon;
import java.util.List;

/**
 *
 * @author tjen
 */
public interface GetInventory
{
    List<Item> getItemsCopy();

    Weapon getWeapon();

    int getDiamonds();
}
