package eu.tjenwellens.bss.serverVSclient.server;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.commands.command.Command;
import eu.tjenwellens.bss.commands.command.CreatePlayerCommand;
import eu.tjenwellens.bss.commands.command.LoadPlayerCommand;
import eu.tjenwellens.bss.commands.command.LogoutPlayerCommand;
import eu.tjenwellens.bss.commands.command.SaveAndLogoutPlayerCommand;
import eu.tjenwellens.bss.commands.command.UpdateIDCommand;
import eu.tjenwellens.bss.database.AccountDB;
import eu.tjenwellens.bss.database.DBPlayer;
import eu.tjenwellens.bss.database.PlayerDB;
import eu.tjenwellens.bss.database.PlayerSaver;
import eu.tjenwellens.bss.database.SavePlayer;
import eu.tjenwellens.bss.mvc.controller.CommandInvokerInterface;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
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
    private CommandInvokerInterface ci;
    private CommandReceiverInterface cr;
    private PlayerSaver playerSaver;

    public DBAccountHandler(CommandInvokerInterface ci, CommandReceiverInterface cr)
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
//        ci.addCommand(new CreatePlayerCommand(cr, id, playerName, factionName, position));
        DBPlayer player = new PlayerDB().getPlayer(id, playerName);
        ci.addCommand(new LoadPlayerCommand(cr, id, playerName, player.getWinns(), player.getWinns(), factionName, position));
        System.out.println("LOGIN: "+player);
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
        id = login(name, pass, playerName, factionName, position);
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
        ci.addCommand(new UpdateIDCommand(cr, oldID, id));
        removeAccount(oldAccount);
        return id;
    }

    @Override
    public void logout(int playerID)
    {
        Command command;
        if (playerID < 0)
        {
            command = new LogoutPlayerCommand(cr, playerID);
        } else if (playerID > 0)
        {
            command = new SaveAndLogoutPlayerCommand(cr, playerID, playerSaver);
        } else
        {
            return;
        }
        ci.addCommand(command);
        // create account with existing progress
        System.out.println("Client logged out: " + playerID);
    }

//    public boolean deleteAccount(String name, String pass, String playerName)
//    {
//        if (name == null || pass == null || playerName == null)
//        {
//            return false;
//        }
//        Integer id = name_id.get(name);
//        if (id == null || id == 0)
//        {
//            return false;
//        }
//        Account account = accounts.get(id);
//        if (!account.validate(name, pass, playerName))
//        {
//            return false;
//        }
//        removeAccount(id, name, pass, playerName);
//        return true;
//    }
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