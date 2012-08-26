package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import java.io.Serializable;


/**
 *
 * @author Tjen
 */
public interface DataMap extends Serializable
{
    DataTile[][] getTiles();
    boolean isEmpty();
}
