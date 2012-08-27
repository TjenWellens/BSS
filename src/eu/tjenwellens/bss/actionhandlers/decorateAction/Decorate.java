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
    private int row;
    private int col;
    private Tool tool;

    public Decorate(DecorateHanderInterface decorateHander, MapHandlerInterface mapHandler, DecoratePlayer player, Decoration decoration, int row, int col, Tool tool)
    {
        this.decorateHander = decorateHander;
        this.mapHandler = mapHandler;
        this.player = player;
        this.decoration = decoration;
        this.row = row;
        this.col = col;
        this.tool = tool;
    }

    public boolean update()
    {
        boolean successfull = false;
        switch (decoration)
        {
            case PAINT:
                if (tool.getToolType() != PAINT_TOOL)
                {
                    System.out.println("wrong tool to paint: " + tool);
                    break;
                }
                if (!player.hasItem(tool))
                {
                    System.out.println("you don't have the correct tool: " + tool);
                    break;
                }
                if (!mapHandler.paint(player, row, col))
                {
                    // maphandler handles this
                    break;
                }
                // all is ok
                successfull = true;
                player.useTool(tool);
                break;
            case BUILD:
                if (tool.getToolType() != BUILD_TOOL)
                {
                    System.out.println("wrong tool to build: " + tool);
                    break;
                }
                if (!player.hasItem(tool))
                {
                    System.out.println("you don't have the correct tool: " + tool);
                    break;
                }
                if (!mapHandler.build(player, row, col))
                {
                    // maphandler handles this
                    break;
                }
                // all is ok
                successfull = true;
                player.useTool(tool);
                break;
            case DESTROY:
                if (tool.getToolType() != DESTROY_TOOL)
                {
                    System.out.println("wrong tool to destroy: " + tool);
                    break;
                }
                if (!player.hasItem(tool))
                {
                    System.out.println("you dont have the correct tool: " + tool);
                    break;
                }
                if (!mapHandler.destroy(player, row, col))
                {
                    // maphandler handles this
                    break;
                }
                // all is ok
                successfull = true;
                player.useTool(tool);
                break;
            default:
                System.out.println("Decorate.update error");
                return false;
        }
        decorateHander.removeDecorate(this);
        player.idle();
        return successfull;
    }

    @Override
    public boolean hasPlayer(ActionPlayer p)
    {
        return p != null && p.equals(player);
    }
}
