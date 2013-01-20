package eu.tjenwellens.bss.data.commands.dataToClient.inventory;

/**
 *
 * @author Tjen
 */
public class SDataItem implements DataItem
{
    private String material;
    private String toolType;
    private String weaponType;

    public SDataItem(String material, String toolType, String weaponType)
    {
        this.material = material;
        this.toolType = toolType;
        this.weaponType = weaponType;
    }

    @Override
    public String getMaterial()
    {
        return material;
    }

    @Override
    public String getToolType()
    {
        return toolType;
    }

    @Override
    public String getWeaponType()
    {
        return weaponType;
    }
}
