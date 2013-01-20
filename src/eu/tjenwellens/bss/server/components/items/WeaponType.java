package eu.tjenwellens.bss.server.components.items;

/**
 *
 * @author tjen
 */
public enum WeaponType implements ItemType
{
// laatste waarde groter dan 2*de grootste
    BLAD(0, Material.MATERIALS * 0), STEEN(1, Material.MATERIALS * 1), SCHAAR(10, Material.MATERIALS * 2);
    private int value;
    private int id;
    private static final int max = 2;

    private WeaponType(int i, int id)
    {
        this.value = i;
        this.id = id;
    }

    public int compare(WeaponType weaponType)
    {
        int result;
        if (this.value == weaponType.value)
        {
            result = 0;
        } else if (this.value > weaponType.value)
        {
            if (this.value + weaponType.value == 10)
            {
                result = 1;
            } else
            {
                result = -1;
            }
        } else
        {
            if (this.value + weaponType.value == 10)
            {
                result = -1;
            } else
            {
                result = 1;
            }
        }
        return result;
    }

    public static WeaponType getRandomWeaponType()
    {
        Math.random();
        int i = (int) Math.floor(Math.random() * 3);
        if (i == 0)
        {
            return WeaponType.BLAD;
        } else if (i == 1)
        {
            return WeaponType.STEEN;
        } else
        {
            return WeaponType.SCHAAR;
        }
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
            case BLAD:
                return "blad";
            case STEEN:
                return "steen";
            case SCHAAR:
                return "schaar";
            default:
                return super.toString();
        }
    }

    public static WeaponType fromName(String name)
    {
        if (BLAD.name().equals(name))
        {
            return BLAD;
        } else if (STEEN.name().equals(name))
        {
            return STEEN;
        } else if (SCHAAR.name().equals(name))
        {
            return SCHAAR;
        } else
        {
            return null;
        }
    }
}
