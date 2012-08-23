/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class CreatePlayerCommand extends Command
{

    private String playerName;
    private String factionName;
    private Position position;

    public CreatePlayerCommand(CommandReceiverInterface cr, int playerID, String playerName, String factionName, Position position)
    {
        super(cr, playerID);
        this.playerName = playerName;
        this.factionName = factionName;
        this.position = position;
    }

    @Override
    public void execute()
    {
        cr.createPlayerCommand(playerID, playerName, factionName, position);
    }

    @Override
    public String toString()
    {
        return "CreatePlayerCommand: player" + playerID + " named " + playerName + " joined " + factionName;
    }
}
