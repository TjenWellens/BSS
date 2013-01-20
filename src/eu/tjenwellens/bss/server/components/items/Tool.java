package eu.tjenwellens.bss.server.components.items;

/**
 *
 * @author tjen
 */
public class Tool implements Item
{
    private Material material;
    private ToolType decorationType;

    public Tool(ToolType decorationType, Material material)
    {
        this.material = material;
        this.decorationType = decorationType;
    }

    public ToolType getToolType()
    {
        return decorationType;
    }

    public void setDecorationType(ToolType decorationType)
    {
        this.decorationType = decorationType;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

//    public boolean isBrush()
//    {
//        return this.decorationType==ToolType.PENSEEL;
//    }
//    
//    public boolean isTrowel()
//    {
//        return this.decorationType==ToolType.TRUWEEL;
//    }
//    
//    public boolean isHammer()
//    {
//        return this.decorationType==ToolType.HAMER;
//    }
    @Override
    public int getId()
    {
        return decorationType.getId() + material.getId();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Tool))
        {
            return false;
        }
        Tool decoration = (Tool) obj;
        if (decorationType.equals(decoration.decorationType) && material.equals(decoration.material))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return decorationType.getId() * 10 + material.getId();
    }

    @Override
    public String toString()
    {
        return "Decoration[" + material + "-" + decorationType + "]";
    }
}
