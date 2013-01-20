package eu.tjenwellens.bss.data.commands.dataToServer;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.DataItem;

/**
 *
 * @author Tjen
 */
public class Decorate extends Command
{
    private String decoration;
    private int row;
    private int col;
    private DataItem tool;

    public Decorate(String decoration, int row, int col, DataItem tool)
    {
        this.decoration = decoration;
        this.row = row;
        this.col = col;
        this.tool = tool;
    }

    public String getDecoration()
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

    public DataItem getTool()
    {
        return tool;
    }
}
