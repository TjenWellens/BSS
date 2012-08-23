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
public class EngageCommand extends Command
{

    private String opponentName;

    public EngageCommand(CommandReceiverInterface cr, int playerID, String opponentName)
    {
        super(cr, playerID);
        this.opponentName = opponentName;
    }

    @Override
    public void execute()
    {
        cr.engageCommand(playerID, opponentName);
    }

    @Override
    public String toString()
    {
        return "EngageCommand: player" + playerID + " engages " + opponentName;
    }
}
