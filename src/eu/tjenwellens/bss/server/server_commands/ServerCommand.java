package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public abstract class ServerCommand
{
    protected CommandReceiverInterface cr;
    protected int playerID;

    protected ServerCommand(CommandReceiverInterface cr, int playerID)
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
