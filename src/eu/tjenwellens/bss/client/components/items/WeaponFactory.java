package eu.tjenwellens.bss.client.components.items;

/**
 *
 * @author tjen
 */
public abstract class WeaponFactory extends ItemFactory
{
    public static Weapon createWeapon(WeaponType weaponType, Material material)
    {
        Weapon testWeapon = (Weapon) getItem(weaponOffset + weaponType.getId() + material.getId());
        return testWeapon;
    }

    public static Weapon createWeapon(String weaponType, String material)
    {
        return createWeapon(getWeaponType(weaponType), getMaterial(material));
    }

    public static Weapon createWeapon(WeaponType weaponType)
    {
        return createWeapon(weaponType, Material.getDefaultMaterial());
    }

    public static Weapon createWeapon(String weaponType)
    {
        return createWeapon(getWeaponType(weaponType));
    }

    public static Weapon createWeapon()
    {
        return createWeapon(WeaponType.getRandomWeaponType());
    }

    public static int getPrice(Weapon weapon)
    {
        return getPrice(weaponOffset + weapon.getWeaponType().getId() + weapon.getMaterial().getId());
    }

    public static void main(String[] args)
    {
        System.out.println("" + getItem(weaponOffset + Material.DIAMAND.getId() + WeaponType.BLAD.getId()));
    }

    public static WeaponType getWeaponType(String weaponType)
    {
        WeaponType returnWeaponType;
        try
        {
            returnWeaponType = WeaponType.valueOf(weaponType.toUpperCase().trim());
        } catch (IllegalArgumentException e)
        {
            returnWeaponType = WeaponType.getRandomWeaponType();
            System.out.println("Error WeaponFactory.getWeaponType(" + weaponType + "), random weapon returned");
        }
        return returnWeaponType;
    }
}
