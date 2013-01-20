package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class SeverCommandDecorate extends ServerCommand
{
    private Decoration decoration;
    private int row;
    private int col;
    private Tool tool;

    public SeverCommandDecorate(CommandReceiverInterface cr, int playerID, Decoration decoration, int row, int col, Tool tool)
    {
        super(cr, playerID);
        this.decoration = decoration;
        this.row = row;
        this.col = col;
        this.tool = tool;
    }

    @Override
    public void execute()
    {
        cr.decorateCommand(playerID, decoration, row, col, tool);
    }

    @Override
    public String toString()
    {
        return "Command: " + "DecorateCommand: " + decoration;
    }
}
