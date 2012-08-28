package eu.tjenwellens.bss.serverVSclient.client.mvc;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;

/**
 *
 * @author tjen
 */
public interface ServerToModel
{
    void updateData(DataForClient data);
}
