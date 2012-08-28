package eu.tjenwellens.bss.test;

import eu.tjenwellens.bss.database.DBAccount;
import eu.tjenwellens.bss.serverVSclient.server.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tjen
 */
public class DatabaseTest
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        DBAccount dba = DBAccount.getInstance();
//        for (int i = 0; i < 10; i++)
//        {
//            dba.addAccount("" + i, "" + i, "" + i);
//        }
//        dba.printAllAccounts();
//        new DatabaseTest();
    }

    private void printAccountFromName(String name)
    {
        Account a = DBAccount.getInstance().getAccount(name);
        if (a == null)
        {
            System.out.println("account is null");
        } else
        {
            System.out.println(a);
        }
    }

    public DatabaseTest()
    {
        try
        {
            connectToAndQueryDatabase("BSSname", "BSSpass");
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    static
    {
        try
        {// from the createConnection method
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
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

    public void connectToAndQueryDatabase(String username, String password) throws SQLException
    {

        Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/BSSdata",
                username,
                password);
        Statement stmt2 = con.createStatement();
        stmt2.executeUpdate("Insert into accounts (name, pass, playername) values ('naam', 'pass', 'playerNaam')");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");
        System.out.println("boohjaa");
        printRS(rs);
    }

    public void printRS(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String pass = rs.getString("pass");
            String playerName = rs.getString("playername");
            System.out.println("ID: " + id + " - NAME: " + name + " - PASS: " + pass + " - PLAYERNAME: " + playerName);
        }
    }
}
