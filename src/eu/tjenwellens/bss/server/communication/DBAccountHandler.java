package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.database.AccountDB;
import eu.tjenwellens.bss.server.database.DBPlayer;
import eu.tjenwellens.bss.server.database.PlayerDB;
import eu.tjenwellens.bss.server.database.PlayerSaver;
import eu.tjenwellens.bss.server.database.SavePlayer;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;
import eu.tjenwellens.bss.server.server_commands.ServerCommand;
import eu.tjenwellens.bss.server.server_commands.ServerCommandUpdateID;
import eu.tjenwellens.bss.server.server_commands.SeverCommandLogoutPlayer;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Tjen
 */
public class DBAccountHandler implements AccountHandler, PlayerSaver
{
    private final static String PASS = "p";
    private final static String NAME = "p";
    private volatile HashMap<String, Integer> name_id = new HashMap<>();
    private volatile HashSet<String> playerNames = new HashSet<>();
    private volatile HashMap<Integer, Account> accounts = new HashMap<>();
    private CommandInvoker ci;
    private CommandReceiver cr;
    private PlayerSaver playerSaver;

    public DBAccountHandler(CommandInvoker ci, CommandReceiver cr)
    {
        this.cr = cr;
        this.ci = ci;
        // TODO: setup player saver
        this.playerSaver = this;
        // TODO: load state
        loadAccounts();
    }

    private void loadAccounts()
    {
        AccountDB db = new AccountDB();
        for (Account a : db.getAllAccounts())
        {
            int id = a.getId();
            String name = a.getName();
            name_id.put(name, id);
            playerNames.add(name);
            accounts.put(id, a);
        }
//        System.out.println(""+playerNames);
    }

    @Override
    public boolean signup(String name, String pass, String playerName)
    {
        int id = signup(name, pass, playerName, 1);
        if (id > 0)
        {
            new PlayerDB().addPlayer(id, playerName);
        }
        return id > 0;
    }

    private int signup(String name, String pass, String playerName, int multiplier)
    {

        if (name == null || pass == null || playerName == null)
        {
            System.out.println("Signup failure: null value");
            return 0;
        }
        int id = name_id.size() * multiplier;
        if (name_id.containsKey(name))
        {
            System.out.println("Signup failure: account name already exists");
            return 0;
        }
        if (id > 0 && playerNames.contains(playerName))
        {
            System.out.println("Signup failure: playerName already exists");
            return 0;
        }
        // all is ok
        Account account = new Account(id, name, pass, playerName);
        name_id.put(name, id);
        playerNames.add(playerName);
        accounts.put(id, account);
        return id;
    }

    @Override
    public int fullinit(String name, String pass, String playerName, String factionName, Position position)
    {
        if (name == null || pass == null || playerName == null || factionName == null || position == null)
        {
            return 0;
        }
        Integer id = name_id.get(name);
        if (id == null || id == 0)
        {
            return 0;
        }
        Account account = accounts.get(id);
        if (!account.validate(name, pass))
        {
            return 0;
        }
        // login
//        ci.addCommand(new CreatePlayerCommand(cr, id, playerName, factionName, position));
        DBPlayer player = new PlayerDB().getPlayer(id, playerName);
//        ci.addCommand(new SeverCommandLoadPlayer(cr, id, playerName, player.getWinns(), player.getWinns(), factionName, position));
        System.out.println("fullinit: " + player);
        return id;
    }

    @Override
    public int quickPlay(String playerName, String factionName, Position position)
    {
        if (playerNames.contains(playerName))
        {
            return 0;
        }
        int id = signup(null, null, playerName, -1);
        int id1 = name_id.size();
        String name = getUniqueName(id);
        String pass = getUniquePassword(id);
        if (id == 0)
        {
            return 0;
        }
        id = fullinit(name, pass, playerName, factionName, position);
        if (id1 != id)
        {
            System.out.println("ERROR: id's in quickplay: " + id1 + ", " + id);
        }
        return id;
    }

    @Override
    public int save(int oldID, String name, String pass, String playerName)
    {
        if (oldID == 0 || name == null || pass == null || playerName == null)
        {
            return 0;
        }
        Account oldAccount = accounts.get(oldID);
        if (oldAccount == null)
        {
            return 0;
        }
        int id = signup(name, pass, playerName, 1);
        if (id == 0)
        {
            return oldID;
        }
        ci.addCommand(new ServerCommandUpdateID(cr, oldID, id));
        removeAccount(oldAccount);
        return id;
    }

    @Override
    public void logout(int playerID)
    {
        ServerCommand command;
        if (playerID < 0)
        {
            command = new SeverCommandLogoutPlayer(cr, playerID);
        } else if (playerID > 0)
        {
//            command = new SeverCommandSaveAndLogoutPlayer(cr, playerID, playerSaver);
        } else
        {
            return;
        }
//        ci.addCommand(command);

        // create account with existing progress
        System.out.println("Client logged out: " + playerID);
    }

    private void removeAccount(Account account)
    {
        name_id.remove(account.getName());
        accounts.remove(account.getId());
        playerNames.remove(account.getName());
    }

    @Override
    public void savePlayer(SavePlayer player)
    {
        new PlayerDB().updatePlayer(player.getPlayerID(), player);
    }

    private String getUniqueName(int id)
    {
        return NAME + Math.abs(id);
    }

    private String getUniquePassword(int id)
    {
        return PASS + id;
    }

}
