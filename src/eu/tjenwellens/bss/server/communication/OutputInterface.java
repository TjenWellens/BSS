package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.data.commands.dataToClient.DataForClient;

/**
 *
 * @author tjen
 */
public interface OutputInterface
{
    DataForClient getData(int playerId);
}
