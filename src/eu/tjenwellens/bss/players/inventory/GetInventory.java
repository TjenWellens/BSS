package eu.tjenwellens.bss.players.inventory;

import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
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
