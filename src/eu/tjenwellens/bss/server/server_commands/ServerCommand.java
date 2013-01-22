package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author tjen
 */
public abstract class ServerCommand
{
    protected CommandReceiver cr;
    protected int playerID;

    protected ServerCommand(CommandReceiver cr, int playerID)
    {
        this.cr = cr;
        this.playerID = playerID;
    }

    @Override
    public String toString()
    {
        return "Command: " + super.toString();
    }

    public abstract void execute();
}
