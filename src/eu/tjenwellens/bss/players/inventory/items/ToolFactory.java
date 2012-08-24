package eu.tjenwellens.bss.players.inventory.items;

/**
 *
 * @author tjen
 */
public abstract class ToolFactory extends ItemFactory
{
    public static Tool createTool(ToolType toolType, Material material)
    {
        // 12 + 8 + 2
        Tool testTool = (Tool) getItem(decorationOffset + toolType.getId() + material.getId());
        return testTool;
    }

    public static Tool createTool(String toolType, String material)
    {
        return createTool(getToolType(toolType), getMaterial(material));
    }

    public static Tool createTool(ToolType toolType)
    {
        return createTool(toolType, Material.getDefaultMaterial());
    }

    public static Tool createTool(String toolType)
    {
        return createTool(getToolType(toolType));
    }

    public static int getPrice(Tool tool)
    {
        return getPrice(decorationOffset + tool.getToolType().getId() + tool.getMaterial().getId());
    }

    public static void main(String[] args)
    {
        System.out.println("" + getItem(decorationOffset + Material.HOUT.getId() + ToolType.TRUWEEL.getId()));
    }

    public static ToolType getToolType(String toolType)
    {
        ToolType returnToolType = null;
        try
        {
            returnToolType = ToolType.valueOf(toolType.toUpperCase().trim());
        } catch (IllegalArgumentException e)
        {
            returnToolType = null;
            System.out.println("Error WeaponFactory.getWeaponType");
        }
        return returnToolType;
    }
}
