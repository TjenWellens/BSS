/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class IdleCommand extends Command
{

    public IdleCommand(CommandReceiverInterface cr, int playerID)
    {
        super(cr, playerID);
    }

    @Override
    public void execute()
    {
        cr.idleCommand(playerID);
    }

    @Override
    public String toString()
    {
        return "IdleCommand: player" + playerID + " goes Idle";
    }
}
