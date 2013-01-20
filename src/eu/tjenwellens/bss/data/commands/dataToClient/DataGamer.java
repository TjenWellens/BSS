package eu.tjenwellens.bss.data.commands.dataToClient;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public interface DataGamer extends Serializable, DataPlayer
{
    int getXDestination();

    int getYDestination();

    int getDuelResult();

    String getOpponentName();
}
