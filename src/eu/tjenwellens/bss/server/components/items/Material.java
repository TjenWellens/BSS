package eu.tjenwellens.bss.server.components.items;

/**
 *
 * @author tjen
 */
public enum Material
{
// laatste waarde groter dan 2*de grootste
    HOUT(0), STEEN(1), IJZER(2), DIAMAND(3);
    public static final int MATERIALS = 4;
    private int id;

    private Material(int id)
    {
        this.id = id;
    }

    public int compare(Material material)
    {
        return this.compareTo(material);
    }

    public static Material getDefaultMaterial()
    {
        return Material.IJZER;
    }

    public boolean isDisposableMaterial()
    {
        return this != IJZER && this != DIAMAND;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case HOUT:
                return "hout";
            case STEEN:
                return "steen";
            case IJZER:
                return "ijzer";
            case DIAMAND:
                return "diamand";
            default:
                return super.toString();
        }
    }

    public boolean isDefaultMaterial()
    {
        if (this == getDefaultMaterial())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static Material fromName(String name)
    {
        if (HOUT.name().equals(name))
        {
            return HOUT;
        } else if (STEEN.name().equals(name))
        {
            return STEEN;
        } else if (IJZER.name().equals(name))
        {
            return IJZER;
        } else if (DIAMAND.name().equals(name))
        {
            return DIAMAND;
        } else
        {
            return null;
        }
    }
}
