package eu.tjenwellens.bss.client.communication;

/**
 *
 * @author Tjen
 */
interface InitCommunication
{
    void login(String name, String password);

    void selectFaction(String factionName);

    void selectPosition(int x, int y);

    void play();
}
