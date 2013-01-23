package eu.tjenwellens.bss.data.commands.init.to_server;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class DoSelectPosition extends DoInit
{
    private int xPosition;
    private int yPosition;

    public DoSelectPosition(int xPosition, int yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition()
    {
        return xPosition;
    }

    public int getYPosition()
    {
        return yPosition;
    }
}
