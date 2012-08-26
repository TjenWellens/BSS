package eu.tjenwellens.bss.serverVSclient.communication.init;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class Err implements Serializable
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
