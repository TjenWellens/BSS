package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class ServerCommandUpdateID extends ServerCommand
{
    private int newID;

    public ServerCommandUpdateID(CommandReceiverInterface cr, int oldID, int newID)
    {
        super(cr, oldID);
        this.newID = newID;
    }

    @Override
    public void execute()
    {
        cr.updateIDCommand(playerID, newID);
    }
}
