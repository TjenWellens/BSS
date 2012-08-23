/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

/**
 *
 * @author tjen
 */
public class ChooseWeaponCommand extends Command
{

    private Weapon weapon;

    public ChooseWeaponCommand(CommandReceiverInterface cr, int playerID, Weapon weapon)
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
