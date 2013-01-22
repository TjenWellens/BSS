package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class SeverCommandSaveAndLogoutPlayer extends ServerCommand
{
private PlayerSaver playerSaver;

    public SeverCommandSaveAndLogoutPlayer(CommandReceiver cr, int playerID, PlayerSaver playerSaver)
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
