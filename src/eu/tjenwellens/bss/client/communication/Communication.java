package eu.tjenwellens.bss.client.communication;

import eu.tjenwellens.bss.client.action.Transaction;
import eu.tjenwellens.bss.client.action.Decoration;
import eu.tjenwellens.bss.client.components.items.Item;
import eu.tjenwellens.bss.client.components.items.Tool;
import eu.tjenwellens.bss.client.components.items.Weapon;

/**
 *
 * @author tjen
 */
public interface Communication
{
    // command
    // -bank
    public void bank(Transaction transaction, int diamonds, Item item);

    // -chooseweapon
    public void chooseWeapon(Weapon weapon);

    // -decorate
    public void decorate(Decoration d, int row, int col, Tool t);

    // -engage
    public void engage(String opponentName);

    // -idle
    public void idle();

    // -walk
    public void walk(int x, int y);

    // login
    public void fullInit(String name, String pass, String playerName, String factionName, int x, int y);

    // end
    public void logout();

    // quickplay
    public void quickplay(String playerName, String factionName, int x, int y);

    // signup
    public void signup(String name, String pass, String playerName);

    // save
    public void save(String name, String pass, String playerName);
}
