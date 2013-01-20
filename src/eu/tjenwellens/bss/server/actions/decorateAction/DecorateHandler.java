package eu.tjenwellens.bss.server.actions.decorateAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.components.map.MapHandlerInterface;
import eu.tjenwellens.bss.server.components.items.Tool;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class DecorateHandler implements DecorateHanderInterface
{
    private MapHandlerInterface mapHandler;
    private ArrayList<Decorate> decorates = new ArrayList<Decorate>();

    public DecorateHandler(MapHandlerInterface mapHandler)
    {
        this.mapHandler = mapHandler;
    }

    @Override
    public boolean addDecorate(DecoratePlayer player, Decoration decoration, int row, int col, Tool tool)
    {
        if (decoration != null && row >= 0 && col >= 0 && tool != null)
        {
            decorates.add(new Decorate(this, mapHandler, player, decoration, row, col, tool));
            return true;
        }
        return false;
    }

    protected synchronized List<Decorate> copyDecorates()
    {
        return new ArrayList(decorates);
    }

    @Override
    public boolean updateDecorates()
    {
        boolean result = false;
        for (Decorate decorate : copyDecorates())
        {
            result = decorate.update() | result;
        }
        return result;
    }

    @Override
    public void cancelAction(ActionPlayer player)
    {
        removeDecorate(getDecorateByPlayer(player));
    }

    protected Decorate getDecorateByPlayer(ActionPlayer player)
    {
        for (Decorate decorate : decorates)
        {
            if (decorate.hasPlayer(player))
            {
                return decorate;
            }
        }
        return null;
    }

    @Override
    public void removeDecorate(Decorate decorate)
    {
        decorates.remove(decorate);
    }
}
