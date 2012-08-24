/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.decorateAction;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.actionhandlers.HasPlayer;
import eu.tjenwellens.bss.map.MapHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.ToolType;

/**
 *
 * @author tjen
 */
public class Decorate implements HasPlayer
{
    private static final ToolType PAINT_TOOL = ToolType.PENSEEL;
    private static final ToolType BUILD_TOOL = ToolType.TRUWEEL;
    private static final ToolType DESTROY_TOOL = ToolType.HAMER;
    private DecorateHanderInterface decorateHander;
    private MapHandlerInterface mapHandler;
    private DecoratePlayer player;
    private Decoration decoration;
    private Position location;
    private Tool tool;

    public Decorate(DecorateHanderInterface decorateHander, MapHandlerInterface mapHandler, DecoratePlayer player, Decoration decoration, Position location, Tool tool)
    {
        this.decorateHander = decorateHander;
        this.mapHandler = mapHandler;
        this.player = player;
        this.decoration = decoration;
        this.location = location;
        this.tool = tool;
    }

    public boolean update()
    {
        switch (decoration)
        {
            case PAINT:
                if (tool.getToolType() == PAINT_TOOL && player.useTool(tool))
                {
                    mapHandler.paint(player, location);
                } else
                {
                    System.out.println("wrong tool to paint: " + tool);
                }
                break;
            case BUILD:
                if (tool.getToolType() == BUILD_TOOL && player.useTool(tool))
                {
                    mapHandler.build(player, location);
                } else
                {
                    System.out.println("wrong tool to build: " + tool);
                }
                break;
            case DESTROY:
                if (tool.getToolType() == DESTROY_TOOL && player.useTool(tool))
                {
                    mapHandler.destroy(player, location);
                } else
                {
                    System.out.println("wrong tool to destroy: " + tool);
                }
                break;
            default:
                System.out.println("Decorate.update error");
                return false;
        }
        decorateHander.removeDecorate(this);
        return true;
    }

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && p.equals(player);
    }
}
