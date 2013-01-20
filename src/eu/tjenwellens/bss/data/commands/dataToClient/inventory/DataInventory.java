package eu.tjenwellens.bss.data.commands.dataToClient.inventory;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface DataInventory extends  Serializable
{
    int getDiamonds();

    DataItem getWeapon();

    List<DataItem> getItemsCopy();
}
