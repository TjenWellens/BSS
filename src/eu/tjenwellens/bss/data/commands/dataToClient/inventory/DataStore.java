package eu.tjenwellens.bss.data.commands.dataToClient.inventory;

import java.io.Serializable;


/**
 *
 * @author Tjen
 */
public interface DataStore extends Serializable
{
    int getPrice(DataItem item);
}
