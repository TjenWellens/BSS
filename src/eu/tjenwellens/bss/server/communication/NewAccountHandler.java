package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import java.util.ArrayList;

/**
 *
 * @author Tjen
 */
public class NewAccountHandler
{
    private ArrayList<Account> accounts = new ArrayList<>();

    public InitPlayer login(String name, String password)
    {
        int id = accounts.size() + 1;
        accounts.add(new Account(id, name, password, name + id));
        return new InitPlayer(id, name + id);
    }
}
