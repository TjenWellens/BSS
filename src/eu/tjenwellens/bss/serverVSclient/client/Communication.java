package eu.tjenwellens.bss.serverVSclient.client;

import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;

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
    public void decorate(Decoration d, int x, int y, Tool t);

    // -engage
    public void engage(String opponentName);

    // -idle
    public void idle();

    // -walk
    public void walk(int x, int y);

    // login
    public void login(String name, String pass, String playerName, String factionName, int x, int y);

    // end
    public void logout();

    // quickplay
    public void quickplay(String playerName, String factionName, int x, int y);

    // signup
    public void signup(String name, String pass, String playerName);

    // save
    public void save(String name, String pass, String playerName);
}
