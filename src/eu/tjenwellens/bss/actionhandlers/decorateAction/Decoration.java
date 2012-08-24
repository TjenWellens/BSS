package eu.tjenwellens.bss.actionhandlers.decorateAction;

import eu.tjenwellens.bss.players.inventory.items.ToolType;

/**
 *
 * @author tjen
 */
public enum Decoration
{
    PAINT, BUILD, DESTROY;

    public ToolType getNecessaryTool()
    {
        switch (this)
        {
            case PAINT:
                return ToolType.PENSEEL;

            case BUILD:
                return ToolType.TRUWEEL;

            case DESTROY:
                return ToolType.HAMER;

            default:
                return null;
        }
    }
}
