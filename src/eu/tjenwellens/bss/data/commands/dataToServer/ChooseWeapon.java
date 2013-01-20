package eu.tjenwellens.bss.data.commands.dataToServer;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.DataItem;

/**
 *
 * @author Tjen
 */
public class ChooseWeapon extends Command
{
    private DataItem weapon;

    public ChooseWeapon(DataItem weapon)
    {
        this.weapon = weapon;
    }

    public DataItem getWeapon()
    {
        return weapon;
    }
}
