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
public abstract class Command
{

    protected CommandReceiverInterface cr;
    protected int playerID;

    protected Command(CommandReceiverInterface cr, int playerID)
    {
        this.cr = cr;
        this.playerID = playerID;
    }

    @Override
    public String toString()
    {
        return "Command: "+super.toString();
    }

    public abstract void execute();
}
