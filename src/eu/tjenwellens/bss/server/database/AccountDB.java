package eu.tjenwellens.bss.server.database;

import eu.tjenwellens.bss.server.communication.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    // queries
    private static final String getAcount = "SELECT * FROM accounts WHERE " + KEY_NAME + " = ?";
    private static final String getAll = "SELECT * FROM " + TABLE;
    private static final String insert = "Insert into " + TABLE + " (" + KEY_NAME + ", " + KEY_PASS + ", " + KEY_PLAYERNAME + ") values (?, ?, ?)";
    private static final String update = "UPDATE Persons" + TABLE
            + "SET " + KEY_ID + "=?, " + KEY_NAME + "=?, " + KEY_PASS + "=?, " + KEY_PLAYERNAME + "=? WHERE " + KEY_ID + "=?";

    public Account getAccount(String name)
    {
        Account a = null;
        name = name.toLowerCase();
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(getAcount))
        {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    int id = rs.getInt(KEY_ID);
                    String pass = rs.getString(KEY_PASS);
                    String playerName = rs.getString(KEY_PLAYERNAME);
                    a = new Account(id, name, pass, playerName);
                }
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
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(getAll); ResultSet rs = stmt.executeQuery())
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
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(insert))
        {
            stmt.setString(1, name);
            stmt.setString(2, pass);
            stmt.setString(3, playerName);
            i = stmt.executeUpdate();
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
        try (Connection c = getConnection(); PreparedStatement stmt = c.prepareStatement(update))
        {
            stmt.setInt(1, account.getId());
            stmt.setString(2, account.getName());
            stmt.setString(3, account.getPass());
            stmt.setString(4, account.getPlayerName());
            stmt.setInt(5, id);
            i = stmt.executeUpdate();
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
            ResultSet rs = stmt.executeQuery(getAll);
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
