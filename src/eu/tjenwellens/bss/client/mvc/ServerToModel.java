package eu.tjenwellens.bss.client.mvc;

import eu.tjenwellens.bss.data.commands.dataToClient.DataForClient;

/**
 *
 * @author tjen
 */
public interface ServerToModel
{
    void updateData(DataForClient data);
}
