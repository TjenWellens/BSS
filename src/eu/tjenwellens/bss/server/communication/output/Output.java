package eu.tjenwellens.bss.server.communication.output;

import eu.tjenwellens.bss.data.commands.play.DataForClient;

/**
 *
 * @author tjen
 */
public interface Output
{
    DataForClient getData(int playerId);

    DataForClient getMiniMap();

    DataForClient getFactions();
}
