package eu.tjenwellens.bss.data.commands.play.dataToServer;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public class Walk extends Command
{
    private int xPosition;
    private int yPosition;

    public Walk(int xPosition, int yPosition)
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
