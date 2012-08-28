package eu.tjenwellens.bss.database;

import eu.tjenwellens.bss.serverVSclient.server.Account;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class DBAccount
{
    // db login
    private static final String USER = "BSSname";
    private static final String PASSWORD = "BSSpass";
    // keys
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "pass";
    private static final String KEY_PLAYERNAME = "playername";
    //
    private static DBAccount me;
    private Connection connection;

    private DBAccount()
    {
        try
        {
            this.connection = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/BSSdata",
                    USER,
                    PASSWORD);
        } catch (SQLException ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    private ResultSet query(String s)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(s);
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return rs;
    }

    private int update(String s)
    {
        int i = 0;
        try
        {
            Statement stmt = connection.createStatement();
            i = stmt.executeUpdate(s);
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return i;
    }

    public void printAllAccounts()
    {
        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");
            printAccountRS(rs);
        } catch (SQLException ex)
        {
            Logger.getLogger(DBAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printAccountRS(ResultSet rs) throws SQLException
    {
        System.out.println("" + KEY_ID + "\t" + KEY_NAME + "\t" + KEY_PASS + "\t" + KEY_PLAYERNAME);
        while (rs.next())
        {
            int id = rs.getInt(KEY_ID);
            String name = rs.getString(KEY_NAME);
            String pass = rs.getString(KEY_PASS);
            String playerName = rs.getString(KEY_PLAYERNAME);
            System.out.println("" + id + "\t" + name + "\t" + pass + "\t" + playerName);
        }
    }

    public Account getAccount(String name)
    {
        name = name.toLowerCase();
        String q = "SELECT * FROM accounts WHERE " + KEY_NAME + " = '" + name + "'";
        ResultSet rs = query(q);
        Account a = null;
        try
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
        List<Account> accounts = new ArrayList<Account>();
        String q = "SELECT * FROM accounts";
        ResultSet rs = query(q);
        Account a = null;
        try
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
        String q = "Insert into accounts (" + KEY_NAME + ", " + KEY_PASS + ", " + KEY_PLAYERNAME + ") values ('" + name + "', '" + pass + "', '" + playerName + "')";
        int i = update(q);
        return i > 0;
    }

    public static DBAccount getInstance()
    {
        if (me == null)
        {
            me = new DBAccount();
        }
        return me;
    }

    static
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        } catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
        } catch (InstantiationException ex)
        {
            System.out.println(ex);
        } catch (IllegalAccessException ex)
        {
            System.out.println(ex);
        }
    }
}
