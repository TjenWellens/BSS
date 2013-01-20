package eu.tjenwellens.bss.data.commands.init_exit;

import eu.tjenwellens.bss.data.commands.Command;


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
