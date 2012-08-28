package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.commands.command.CreatePlayerCommand;
import eu.tjenwellens.bss.database.DBAccount;
import eu.tjenwellens.bss.mvc.controller.CommandInvokerInterface;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Tjen
 */
public class DBAccountHandler implements AccountHandler
{
    private final static String PASS = "p";
    private final static String NAME = "p";
    private volatile HashMap<String, Integer> name_id = new HashMap<String, Integer>();
    private volatile HashSet<String> playerNames = new HashSet<String>();
    private volatile HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
    private CommandInvokerInterface ci;
    private CommandReceiverInterface cr;

    public DBAccountHandler(CommandInvokerInterface ci, CommandReceiverInterface cr)
    {
        this.cr = cr;
        this.ci = ci;
        // TODO: load state
        loadAccounts();
    }

    private void loadAccounts()
    {
        DBAccount db = DBAccount.getInstance();
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
        if (name == null || pass == null || playerName == null)
        {
            System.out.println("Signup failure: null value");
            return false;
        }
        int id = name_id.size();
        if (name_id.containsKey(name))
        {
            System.out.println("Signup failure: account name already exists");
            return false;
        }
        if (playerNames.contains(playerName))
        {
            System.out.println("Signup failure: playerName already exists");
            return false;
        }
        // all is ok
        Account account = new Account(id, name, pass, playerName);
        name_id.put(name, id);
        playerNames.add(playerName);
        accounts.put(id, account);
        return true;
    }

    @Override
    public int login(String name, String pass, String playerName, String factionName, Position position)
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
        if (!account.validate(name, pass, playerName))
        {
            return 0;
        }
        // login
        ci.addCommand(new CreatePlayerCommand(cr, id, playerName, factionName, position));
        return id;
    }

    @Override
    public int quickPlay(String playerName, String factionName, Position position)
    {
        if (playerNames.contains(playerName))
        {
            return 0;
        }
        int id1 = name_id.size();
        String name = NAME + id1;
        String pass = PASS;
        if (!signup(name, pass, playerName))
        {
            return 0;
        }
        int id3 = login(name, pass, playerName, factionName, position);
        if (id1 != id3)
        {
            System.out.println("ERROR: id's in quickplay: " + id1 + ", " + id3);
        }
        return id3;
    }

    @Override
    public int save(int oldID, String name, String pass, String playerName)
    {
        if (oldID == 0 || name == null || pass == null || playerName == null)
        {
            return 0;
        }
        Account account = accounts.get(oldID);
        if (account == null)
        {
            return 0;
        }
        String oldName = account.getPlayerName();
        String oldPass = PASS;
        String oldPlayerName = NAME + oldID;
        // TODO: get old faction name
        String factionName = "geel";
        // TODO: get old position
        Position oldPosition = new Position(0, 0);
        if (factionName == null || oldPosition == null)
        {
            return 0;
        }
        int id = login(name, pass, playerName, factionName, oldPosition);
        if (id == 0)
        {
            return 0;
        }
        // TODO: transfer inventory and such
        removeAccount(oldID, oldName, oldPass, oldPlayerName);
        return id;
    }

    @Override
    public void logout(int playerID)
    {
        if (playerID == 0)
        {
            return;
        }
        Account a = accounts.get(playerID);
        if (a == null)
        {
            return;
        }
        // TODO: notify model
        // TODO: save state
        // create account with existing progress
        System.out.println("Client logged out"+a.getName());
    }

    @Override
    public boolean deleteAccount(String name, String pass, String playerName)
    {
        if (name == null || pass == null || playerName == null)
        {
            return false;
        }
        Integer id = name_id.get(name);
        if (id == null || id == 0)
        {
            return false;
        }
        Account account = accounts.get(id);
        if (!account.validate(name, pass, playerName))
        {
            return false;
        }
        removeAccount(id, name, pass, playerName);
        return true;
    }

    private void removeAccount(int id, String name, String pass, String playerName)
    {
        name_id.remove(name);
        accounts.remove(id);
        playerNames.remove(playerName);
    }
}
