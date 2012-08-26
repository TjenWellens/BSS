package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.Position;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class Walk implements Serializable
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
