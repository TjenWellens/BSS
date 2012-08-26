package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public interface DataGamer extends Serializable
{
    String getPlayerName();

    int getXPosition();

    int getYPosition();

    String getFactionName();

    String getState();

    // walk
    int getXDestination();

    int getYDestination();

    // battleresult
    int getWinns();

    int getLosses();

    int getDuelResult();

//    DataInventory getInventory();
//
//    DataBankAccount getBankAccount();
//
//    DataStore getStore();
    // battle
    String getOpponentName();
}
