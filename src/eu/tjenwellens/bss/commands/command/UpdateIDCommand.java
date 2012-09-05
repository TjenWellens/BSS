package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class UpdateIDCommand extends Command
{
    private int newID;

    public UpdateIDCommand(CommandReceiverInterface cr, int oldID, int newID)
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
