package eu.tjenwellens.bss.data.commands.dataToClient;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Tjen
 */
public interface DataMap extends Serializable
{
    List<DataTile> getTiles();
    boolean isEmpty();
    int getRows();
    int getCols();
}
