package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.database.PlayerSaver;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;

/**
 *
 * @author Tjen
 */
public class SaveAndLogoutPlayerCommand extends Command
{
private PlayerSaver playerSaver;

    public SaveAndLogoutPlayerCommand(CommandReceiverInterface cr, int playerID, PlayerSaver playerSaver)
    {
        super(cr, playerID);
        this.playerSaver = playerSaver;
    }

    @Override
    public void execute()
    {
        cr.saveAndLogoutPlayerCommand(playerID, playerSaver);
    }

    @Override
    public String toString()
    {
        return "SavePlayerCommand: player" + playerID + " is saving and logging out.";
    }
}
