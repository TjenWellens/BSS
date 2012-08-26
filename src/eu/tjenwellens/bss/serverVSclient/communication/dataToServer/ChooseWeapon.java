package eu.tjenwellens.bss.serverVSclient.communication.dataToServer;

import eu.tjenwellens.bss.players.inventory.items.Weapon;
import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public class ChooseWeapon implements Serializable
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
