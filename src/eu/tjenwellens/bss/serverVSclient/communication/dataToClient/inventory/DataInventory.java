package eu.tjenwellens.bss.serverVSclient.communication.dataToClient.inventory;

import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface DataInventory
{
    int getDiamonds();

    Weapon getWeapon();

    List<Item> getItemsCopy();
}
