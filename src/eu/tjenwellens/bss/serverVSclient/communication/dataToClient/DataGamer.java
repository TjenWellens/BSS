package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.Position;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public interface DataGamer extends Serializable
{
    String getPlayerName();

    Position getPosition();

    String getFactionName();

    String getState();

    // walk
    Position getDestination();

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
