package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;

/**
 *
 * @author Tjen
 */
public class ServerCommandSelectFaction extends ServerCommand
{
    private String factionName;

    public ServerCommandSelectFaction(CommandReceiver cr, int playerID, String factionName)
    {
        super(cr, playerID);
        this.factionName = factionName;
    }

    @Override
    public void execute()
    {
        cr.selectFactionCommand(playerID, factionName);
    }
}
