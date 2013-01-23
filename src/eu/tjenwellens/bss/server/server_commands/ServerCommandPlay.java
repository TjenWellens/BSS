package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class ServerCommandPlay extends ServerCommand
{
    public ServerCommandPlay(CommandReceiver cr, int playerID)
    {
        super(cr, playerID);
    }

    @Override
    public void execute()
    {
        cr.playCommand(playerID);
    }
}
