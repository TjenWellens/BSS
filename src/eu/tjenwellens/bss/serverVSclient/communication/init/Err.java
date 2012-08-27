package eu.tjenwellens.bss.serverVSclient.communication.init;

import eu.tjenwellens.bss.serverVSclient.communication.Command;


/**
 *
 * @author Tjen
 */
public class Err extends Command
{
    private String errorMessage;

    public Err(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
