package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import java.io.Serializable;


/**
 *
 * @author Tjen
 */
public interface DataTile extends Serializable
{
    String getFactionName();

    boolean isWalled();
}
