/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.decorateAction;

import java.util.ArrayList;
import java.util.List;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.map.MapHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Tool;

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
    public boolean addDecorate(DecoratePlayer player, Decoration decoration, Position location, Tool tool)
    {
        if (decoration != null && location != null && tool != null)
        {
            decorates.add(new Decorate(this, mapHandler, player, decoration, location, tool));
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
