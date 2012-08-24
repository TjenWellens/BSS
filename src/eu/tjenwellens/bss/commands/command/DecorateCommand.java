package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Tool;

/**
 *
 * @author tjen
 */
public class DecorateCommand extends Command
{
    private Decoration decoration;
    private Position location;
    private Tool tool;

    public DecorateCommand(CommandReceiverInterface cr, int playerID, Decoration decoration, Position location, Tool tool)
    {
        super(cr, playerID);
        this.decoration = decoration;
        this.location = location;
        this.tool = tool;
    }

    @Override
    public void execute()
    {
        cr.decorateCommand(playerID, decoration, location, tool);
    }

    @Override
    public String toString()
    {
        return "Command: " + "DecorateCommand: " + decoration;
    }
}
