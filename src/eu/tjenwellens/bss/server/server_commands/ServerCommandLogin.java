package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class ServerCommandLogin extends ServerCommand
{
    private InitPlayer initPlayer;

    public ServerCommandLogin(CommandReceiver cr, int playerID, InitPlayer initPlayer)
    {
        super(cr, playerID);
        this.initPlayer = initPlayer;
    }

    @Override
    public void execute()
    {
        cr.loginPlayerCommand(initPlayer);
    }
}
