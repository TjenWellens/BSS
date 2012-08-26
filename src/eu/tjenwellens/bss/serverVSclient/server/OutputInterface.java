package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;

/**
 *
 * @author tjen
 */
public interface OutputInterface
{
    DataForClient getData(int playerId);
}
