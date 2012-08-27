package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class Engage extends Command
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
