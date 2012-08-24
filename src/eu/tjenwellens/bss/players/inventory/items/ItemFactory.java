package eu.tjenwellens.bss.players.inventory.items;

/**
 *
 * @author tjen
 */
public abstract class ItemFactory
{
    public static final int weaponOffset = 0;
    public static final int decorationOffset = 12;
    private static int[] prices =
    {
        // Weapons:
        // blad 4
        1, 4, -1, -1,
        // steen 8
        1, 4, -1, -1,
        // schaar 12
        1, 4, -1, -1,
        // Tools:
        // penseel 16
        1, 10, -1, 100,
        // truweel 20
        1, 10, -1, 100,
        // hamer 24
        1, 10, -1, 100,
    };
    private static Item[] items =
    {
        // Weapons:
        // blad 4
        new Weapon(WeaponType.BLAD, Material.HOUT),
        new Weapon(WeaponType.BLAD, Material.STEEN),
        new Weapon(WeaponType.BLAD, Material.IJZER),
        new Weapon(WeaponType.BLAD, Material.DIAMAND),
        // steen 8
        new Weapon(WeaponType.STEEN, Material.HOUT),
        new Weapon(WeaponType.STEEN, Material.STEEN),
        new Weapon(WeaponType.STEEN, Material.IJZER),
        new Weapon(WeaponType.STEEN, Material.DIAMAND),
        // schaar 12
        new Weapon(WeaponType.SCHAAR, Material.HOUT),
        new Weapon(WeaponType.SCHAAR, Material.STEEN),
        new Weapon(WeaponType.SCHAAR, Material.IJZER),
        new Weapon(WeaponType.SCHAAR, Material.DIAMAND),
        // Tools:
        // penseel 16
        new Tool(ToolType.PENSEEL, Material.HOUT),
        new Tool(ToolType.PENSEEL, Material.STEEN),
        new Tool(ToolType.PENSEEL, Material.IJZER),
        new Tool(ToolType.PENSEEL, Material.DIAMAND),
        // truweel 20
        new Tool(ToolType.TRUWEEL, Material.HOUT),
        new Tool(ToolType.TRUWEEL, Material.STEEN),
        new Tool(ToolType.TRUWEEL, Material.IJZER),
        new Tool(ToolType.TRUWEEL, Material.DIAMAND),
        // hamer 24
        new Tool(ToolType.HAMER, Material.HOUT),
        new Tool(ToolType.HAMER, Material.STEEN),
        new Tool(ToolType.HAMER, Material.IJZER),
        new Tool(ToolType.HAMER, Material.DIAMAND),
        // 
        null
    };

    public static Item getItem(int id)
    {
        return items[id];
    }

    public static int getPrice(int id)
    {
        return prices[id];
    }

    public static Material getMaterial(String material)
    {
        Material returnMaterial = null;
        try
        {
            returnMaterial = Material.valueOf(material.toUpperCase().trim());
        } catch (IllegalArgumentException e)
        {
            returnMaterial = Material.getDefaultMaterial();
            System.out.println("Error WeaponFactory.getMaterial, default material returned");
        }
        return returnMaterial;
    }
}
