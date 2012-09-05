package eu.tjenwellens.bss.database;

import eu.tjenwellens.bss.serverVSclient.server.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tjen
 */
public class AccountDB extends DB
{
    private static final String TABLE = "accounts";
    // keys
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "pass";
    private static final String KEY_PLAYERNAME = "playername";

    public Account getAccount(String name)
    {
        Account a = null;
        name = name.toLowerCase();
        String q = "SELECT * FROM accounts WHERE " + KEY_NAME + " = '" + name + "'";
        try (Connection c = getConnection(); Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(q))
        {

            if (rs.next())
            {
                int id = rs.getInt(KEY_ID);
                String pass = rs.getString(KEY_PASS);
                String playerName = rs.getString(KEY_PLAYERNAME);
                a = new Account(id, name, pass, playerName);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return a;
    }

    public List<Account> getAllAccounts()
    {
        List<Account> accounts = new ArrayList<>();
        String q = "SELECT * FROM " + TABLE;
        try (Connection c = getConnection(); Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(q))
        {
            while (rs.next())
            {
                int id = rs.getInt(KEY_ID);
                String name = rs.getString(KEY_NAME);
                String pass = rs.getString(KEY_PASS);
                String playerName = rs.getString(KEY_PLAYERNAME);
                accounts.add(new Account(id, name, pass, playerName));
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return accounts;
    }

    public boolean addAccount(String name, String pass, String playerName)
    {
        name = name.toLowerCase();
        int i = 0;
        String q = "Insert into " + TABLE + " (" + KEY_NAME + ", " + KEY_PASS + ", " + KEY_PLAYERNAME + ") values ('" + name + "', '" + pass + "', '" + playerName + "')";
        try (Connection c = getConnection(); Statement stmt = c.createStatement())
        {
            i = stmt.executeUpdate(q);
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return i > 0;
    }

    public boolean updateAccount(int id, Account account)
    {
//        name = name.toLowerCase();
        int i = 0;
        String q = "UPDATE Persons" + TABLE
                + "SET " + KEY_ID + "=" + account.getId()
                + ", " + KEY_NAME + "=" + account.getName()
                + ", " + KEY_PASS + "=" + account.getPass()
                + ", " + KEY_PLAYERNAME + "=" + account.getPlayerName()
                + "WHERE " + KEY_ID + "=" + id;
        try (Connection c = getConnection(); Statement stmt = c.createStatement())
        {
            i = stmt.executeUpdate(q);
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return i > 0;
    }

    public void printAllAccounts()
    {
        try (Connection connection = getConnection())
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next())
            {
                int id = rs.getInt(KEY_ID);
                String name = rs.getString(KEY_NAME);
                String pass = rs.getString(KEY_PASS);
                String playerName = rs.getString(KEY_PLAYERNAME);
                System.out.println("" + id + "\t" + name + "\t" + pass + "\t" + playerName);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
