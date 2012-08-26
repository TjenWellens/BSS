package eu.tjenwellens.bss.serverVSclient.communication.init;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class Logout implements Serializable
{
    private int playerID;

    public Logout(int playerID)
    {
        this.playerID = playerID;
    }

    public int getPlayerID()
    {
        return playerID;
    }
}
