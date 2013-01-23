package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.communication.init.InitPlayer;

/**
 *
 * @author Tjen
 */
public interface InitInput
{
    InitPlayer login(String name, String password);

    void selectFaction(int id, String factionName);

    void selectPosition(int id, int x, int y);

    void play(int id);
}
