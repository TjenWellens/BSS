package eu.tjenwellens.bss.client.components.items;

/**
 *
 * @author tjen
 */
public enum ToolType implements ItemType
{
    PENSEEL(Material.MATERIALS * 0), TRUWEEL(Material.MATERIALS * 1), HAMER(Material.MATERIALS * 2);
    private int id;

    private ToolType(int id)
    {
        this.id = id;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case PENSEEL:
                return "penseel";
            case TRUWEEL:
                return "truweel";
            case HAMER:
                return "hamer";
            default:
                return super.toString();
        }
    }

    public static ToolType fromName(String name)
    {
        if (PENSEEL.name().equals(name))
        {
            return PENSEEL;
        } else if (TRUWEEL.name().equals(name))
        {
            return TRUWEEL;
        } else if (HAMER.name().equals(name))
        {
            return HAMER;
        } else
        {
            return null;
        }
    }
}
