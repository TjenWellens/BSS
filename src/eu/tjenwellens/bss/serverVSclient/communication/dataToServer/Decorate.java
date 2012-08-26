package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class Decorate implements Serializable
{
    private Decoration decoration;
    private Position position;
    private Tool tool;

    public Decorate(Decoration decoration, Position position, Tool tool)
    {
        this.decoration = decoration;
        this.position = position;
        this.tool = tool;
    }

    public Decoration getDecoration()
    {
        return decoration;
    }

    public Position getPosition()
    {
        return position;
    }

    public Tool getTool()
    {
        return tool;
    }
}
