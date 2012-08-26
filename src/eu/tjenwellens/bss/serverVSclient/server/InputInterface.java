package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

/**
 *
 * @author tjen
 */
public interface InputInterface
{
    // command
    // -bank
    public void bank(int playerID, Transaction transaction, int diamonds, Item item);

    // -chooseweapon
    public void chooseWeapon(int playerID, Weapon weapon);

    // -decorate
    public void decorate(int playerID, Decoration d, Position destination, Tool t);

    // -engage
    public void engage(int playerID, String opponentName);

    // -idle
    public void idle(int playerID);

    // -walk
    public void walk(int playerID, Position destination);

    // login
    public int login(String name, String pass, String playerName, String factionName, Position position);

    // end
    public void logout(int playerID);

    // quickplay
    public int quickplay(String playerName, String factionName, Position position);

    // signup
    public boolean signup(String name, String pass, String playerName);

    // save
    public int save(int playerID, String name, String pass, String playerName);
}
