package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class SeverCommandChooseWeapon extends ServerCommand
{
    private Weapon weapon;

    public SeverCommandChooseWeapon(CommandReceiverInterface cr, int playerID, Weapon weapon)
    {
        super(cr, playerID);
        this.weapon = weapon;
    }

    @Override
    public void execute()
    {
        cr.chooseWeaponCommand(playerID, weapon);
    }

    @Override
    public String toString()
    {
        return "ChooseWeaponCommand: player" + playerID + " attacks with " + weapon;
    }
}
