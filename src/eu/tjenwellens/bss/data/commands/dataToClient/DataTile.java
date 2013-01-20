package eu.tjenwellens.bss.data.commands.dataToClient;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public interface DataTile extends Serializable
{
    int getRow();

    int getCol();

    int getFactionId();

    boolean isWalled();
}
