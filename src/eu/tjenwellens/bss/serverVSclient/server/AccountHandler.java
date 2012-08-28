package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.Position;

/**
 *
 * @author Tjen
 */
public interface AccountHandler
{
    boolean signup(String name, String pass, String playerName);

    int login(String name, String pass, String playerName, String factionName, Position position);

    int quickPlay(String playerName, String factionName, Position position);

    int save(int oldID, String name, String pass, String playerName);

    void logout(int playerID);

    boolean deleteAccount(String name, String pass, String playerName);
}
