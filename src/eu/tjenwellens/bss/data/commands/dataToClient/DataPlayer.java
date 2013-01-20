package eu.tjenwellens.bss.data.commands.dataToClient;

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

    int getFactionId();

    String getState();
    // walk

    int getWinns();

    int getLosses();
}
