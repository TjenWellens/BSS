package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class Decorate extends Command
{
    private Decoration decoration;
    private int row;
    private int col;
    private Tool tool;

    public Decorate(Decoration decoration, int row, int col, Tool tool)
    {
        this.decoration = decoration;
        this.row = row;
        this.col = col;
        this.tool = tool;
    }

    public Decoration getDecoration()
    {
        return decoration;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Tool getTool()
    {
        return tool;
    }
}
