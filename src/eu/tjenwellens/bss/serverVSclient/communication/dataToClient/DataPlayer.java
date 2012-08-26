package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import java.io.Serializable;


/**
 *
 * @author Tjen
 */
public interface DataPlayer extends Serializable
{
    String getPlayerName();

    int getXPosition();
    int getYPosition();

    String getFactionName();

    String getState();
    // walk

    int getWinns();

    int getLosses();
}
