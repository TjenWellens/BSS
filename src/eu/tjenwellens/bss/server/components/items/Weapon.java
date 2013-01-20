package eu.tjenwellens.bss.server.components.items;

/**
 *
 * @author tjen
 */
public class Weapon implements Item
{
    private WeaponType weaponType;
    private Material material;

    public Weapon(WeaponType weaponType, Material material)
    {
        this.weaponType = weaponType;
        this.material = material;
    }

    public WeaponType getWeaponType()
    {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType)
    {
        this.weaponType = weaponType;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public int compare(Weapon w)
    {
        int returnInt = weaponType.compare(w.weaponType);
        if (returnInt == 0)
        {
            returnInt = material.compare(w.material);
        }

        return returnInt;
    }

    @Override
    public int getId()
    {
        return weaponType.getId() + material.getId();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Weapon))
        {
            return false;
        }
        Weapon compWeapon = (Weapon) obj;
        if (weaponType.equals(compWeapon.weaponType) && material.equals(compWeapon.material))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return weaponType.getId() * 10 + material.getId();
    }

    @Override
    public String toString()
    {
        return "Weapon[" + material + "-" + weaponType + "]";
    }

    public boolean isMadeOfDefaultMaterial()
    {
        return material.isDefaultMaterial();
    }

    public boolean isDisposableMaterial()
    {
        return material.isDisposableMaterial();
    }
}
