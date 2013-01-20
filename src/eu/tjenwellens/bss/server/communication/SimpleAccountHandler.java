package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.server_commands.SeverCommandCreatePlayer;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvokerInterface;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Tjen
 */
public class SimpleAccountHandler implements AccountHandler
{
    private final static String PASS = "p";
    private final static String NAME = "p";
    private volatile HashMap<String, Integer> name_id = new HashMap<>();
    private volatile HashSet<String> playerNames = new HashSet<>();
    private volatile HashMap<Integer, Account> accounts = new HashMap<>();
    private CommandInvokerInterface ci;
    private CommandReceiverInterface cr;

    public SimpleAccountHandler(CommandInvokerInterface ci, CommandReceiverInterface cr)
    {
        this.cr = cr;
        this.ci = ci;
        // TODO: remove
        initAccounts();
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
        addAccount(new Account(id, name, pass, playerName));
        return true;
    }

    @Override
    public int login(String name, String pass, String playerName, String factionName, Position position)
    {
        if (name == null || pass == null || playerName == null || factionName == null || position == null)
        {
            return -1;
        }
        Integer id = name_id.get(name);
        if (id == null || id < 0)
        {
            return -1;
        }
        Account account = accounts.get(id);
        if (!account.validate(name, pass, playerName))
        {
            return -1;
        }
        // login
        ci.addCommand(new SeverCommandCreatePlayer(cr, id, playerName, factionName, position));
        return id;
    }

    @Override
    public int quickPlay(String playerName, String factionName, Position position)
    {
        if (playerNames.contains(playerName))
        {
            return -1;
        }
        int id1 = name_id.size();
        String name = NAME + id1;
        String pass = PASS;
        if (!signup(name, pass, playerName))
        {
            return -1;
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
        if (oldID < 0 || name == null || pass == null || playerName == null)
        {
            return -1;
        }
        Account account = accounts.get(oldID);
        if (account == null)
        {
            return -1;
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
            return -1;
        }
        int id = login(name, pass, playerName, factionName, oldPosition);
        if (id < 0)
        {
            return -1;
        }
        // TODO: transfer inventory and such
        removeAccount(oldID, oldName, oldPass, oldPlayerName);
        return id;
    }

    @Override
    public void logout(int playerID)
    {
        // create account with existing progress
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteAccount(String name, String pass, String playerName)
    {
        if (name == null || pass == null || playerName == null)
        {
            return false;
        }
        Integer id = name_id.get(name);
        if (id == null || id < 0)
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

    private void initAccounts()
    {
        addAccount(new Account(1, "1", "1", "1"));
        addAccount(new Account(2, "2", "2", "2"));
        addAccount(new Account(3, "3", "3", "3"));
        addAccount(new Account(4, "4", "4", "4"));
        addAccount(new Account(5, "5", "5", "5"));
        addAccount(new Account(6, "6", "6", "6"));
        addAccount(new Account(7, "7", "7", "7"));
        addAccount(new Account(8, "8", "8", "8"));
        addAccount(new Account(9, "9", "9", "9"));
        addAccount(new Account(10, "tjen", "tjen", "tjen"));
        addAccount(new Account(11, "miel", "miel", "miel"));
    }

    private void addAccount(Account a)
    {
        name_id.put(a.getName(), a.getId());
        playerNames.add(a.getPlayerName());
        accounts.put(a.getId(), a);
    }
}
