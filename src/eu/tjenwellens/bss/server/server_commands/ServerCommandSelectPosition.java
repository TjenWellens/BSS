package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class ServerCommandSelectPosition extends ServerCommand
{
    private int x;
    private int y;

    public ServerCommandSelectPosition(CommandReceiver cr, int playerID, int x, int y)
    {
        super(cr, playerID);
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute()
    {
        cr.selectPositionCommand(playerID, x, y);
    }
}