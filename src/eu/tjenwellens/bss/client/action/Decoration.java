package eu.tjenwellens.bss.client.action;

import eu.tjenwellens.bss.client.components.items.ToolType;

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
    
    public static Decoration fromName(String name)
    {
        if (PAINT.name().equals(name))
        {
            return PAINT;
        } else if (BUILD.name().equals(name))
        {
            return BUILD;
        } else if (DESTROY.name().equals(name))
        {
            return DESTROY;
        } else
        {
            return null;
        }
    }
}
