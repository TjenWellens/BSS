package eu.tjenwellens.bss.client.communication;

/**
 *
 * @author tjen
 */
public interface Communication extends PlayCommunication, InitCommunication
{
    // end
    public void logout();
//    // quickplay
//    public void quickplay(String playerName, String factionName, int x, int y);
//    // signup
//    public void signup(String name, String pass, String playerName);
//    // save
//    public void save(String name, String pass, String playerName);
}
