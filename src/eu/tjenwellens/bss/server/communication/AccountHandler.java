package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.components.Position;

/**
 *
 * @author Tjen
 */
public interface AccountHandler
{
    boolean signup(String name, String pass, String playerName);

    int fullinit(String name, String pass, String playerName, String factionName, Position position);

    int quickPlay(String playerName, String factionName, Position position);

    int save(int oldID, String name, String pass, String playerName);

    void logout(int playerID);

}
