package eu.tjenwellens.bss.data.commands.dataToServer;

import eu.tjenwellens.bss.data.commands.Command;

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
