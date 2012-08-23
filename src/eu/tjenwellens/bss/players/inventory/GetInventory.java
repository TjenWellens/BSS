/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.inventory;

import java.util.List;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.players.inventory.items.Item;

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
