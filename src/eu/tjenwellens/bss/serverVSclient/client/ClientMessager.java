package eu.tjenwellens.bss.serverVSclient.client;

import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.inventory.items.Weapon;
import eu.tjenwellens.bss.serverVSclient.client.mvc.Data;
import eu.tjenwellens.bss.serverVSclient.client.mvc.ServerToModel;

/**
 *
 * @author tjen
 */
public class ClientMessager extends ServerHandler implements Communication
{
    private ServerToModel model;

    public ClientMessager(ServerToModel model, String HOST)
    {
        super(HOST);
        this.model = model;
    }

    @Override
    public void bank(Transaction transaction, int diamonds, Item item)
    {
        String command = "command bank "
                + transaction.toString() + " "
                + diamonds + " "
                + item.toString();
        send(command);
    }

    @Override
    public void chooseWeapon(Weapon weapon)
    {
        String command = "command chooseweapon " + weapon;
        send(command);
    }

    @Override
    public void decorate(Decoration d, int x, int y, Tool t)
    {
        String command = "command decorate "
                + d.toString() + " "
                + x + " "
                + y + " "
                + t.toString();
        send(command);
    }

    @Override
    public void engage(String opponentName)
    {
        String command = "command "
                + opponentName;
        send(command);
    }

    @Override
    public void idle()
    {
        String command = "command idle";
        send(command);
    }

    @Override
    public void walk(int x, int y)
    {
        String command = "command walk "
                + x + " "
                + y;
        send(command);
    }

    @Override
    public void login(String name, String pass, String playerName, String factionName, int x, int y)
    {
        String command = "login "
                + name + " "
                + pass + " "
                + playerName + " "
                + factionName + " "
                + x + " "
                + y;
        send(command);
    }

    @Override
    public void logout()
    {
        String command = "logout";
        send(command);
    }

    @Override
    public void quickplay(String playerName, String factionName, int x, int y)
    {
        String command = "quickplay "
                + playerName + " "
                + factionName + " "
                + x + " "
                + y;
        send(command);
    }

    @Override
    public void signup(String name, String pass, String playerName)
    {
        String command = "signup "
                + name + " "
                + pass + " "
                + playerName;
        send(command);
    }

    @Override
    public void save(String name, String pass, String playerName)
    {
        String command = "save "
                + name + " "
                + pass + " "
                + playerName;
        send(command);
    }

    @Override
    public void interpretLine(String line)
    {
        line = line.trim();
        // TODO: convert line to data if possible
        if (line.startsWith("update ")) // if the input are actual updates
        {
            Data data = new Data(line.split(" "));
            model.update(data);
        } else if (line.startsWith("end")) // if connection is ended
        {
            end();
        }// ...
    }
}
