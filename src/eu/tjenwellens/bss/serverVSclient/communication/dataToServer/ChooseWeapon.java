package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.serverVSclient.communication.Command;

/**
 *
 * @author Tjen
 */
public class ChooseWeapon extends Command
{
    private Weapon weapon;

    public ChooseWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }
}
