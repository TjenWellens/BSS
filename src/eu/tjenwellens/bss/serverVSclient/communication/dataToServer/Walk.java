package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class Walk extends Command
{
    private Position position;

    public Walk(Position position)
    {
        this.position = position;
    }

    public Position getPosition()
    {
        return position;
    }
}
