package eu.tjenwellens.bss.client.mvc;

import eu.tjenwellens.bss.data.commands.play.DataForClient;

/**
 *
 * @author tjen
 */
public interface ServerToModel
{
    void updateData(DataForClient data);

    void fullInitDone();
}
