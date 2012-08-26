package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class Engage implements Serializable
{
    private String opponentName;

    public Engage(String opponentName)
    {
        this.opponentName = opponentName;
    }

    public String getOpponentName()
    {
        return opponentName;
    }
}
