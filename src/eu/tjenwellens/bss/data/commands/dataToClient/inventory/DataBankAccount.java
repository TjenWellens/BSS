package eu.tjenwellens.bss.data.commands.dataToClient.inventory;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface DataBankAccount extends Serializable
{
    int getDiamonds();

    List<DataItem> getItemsCopy();
}
