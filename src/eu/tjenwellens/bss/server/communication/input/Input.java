package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.components.Position;

/**
 *
 * @author tjen
 */
public interface Input extends PlayInput, InitInput
{
    // fullinit
    public int fullinit(String name, String pass, String playerName, String factionName, Position position);

    // quickplay
    public int quickplay(String playerName, String factionName, Position position);

    // signup
    public boolean signup(String name, String pass, String playerName);

    // save
    public int save(int playerID, String name, String pass, String playerName);
}
