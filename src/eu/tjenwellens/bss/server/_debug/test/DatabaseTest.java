package eu.tjenwellens.bss.server._debug.test;

import eu.tjenwellens.bss.server.database.AccountDB;
import eu.tjenwellens.bss.server.database.DB;
import eu.tjenwellens.bss.server.database.DBPlayer;
import eu.tjenwellens.bss.server.database.PlayerDB;
import eu.tjenwellens.bss.server.communication.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
//        System.out.println(new PlayerDB().updatePlayer(-5, new DBPlayer(-11, "-11", -11, -11)));
        System.out.println(DB.canConnect());
//        DBAccount dba = DBAccount.getInstance();
//        for (int i = 0; i < 10; i++)
//        {
//            dba.addAccount("" + i, "" + i, "" + i);
//        }
//        dba.printAllAccounts();
//        new DatabaseTest();
//        System.out.println(DBAccount.getInstance().getAccount("miel"));
    }

    private void printAccountFromName(String name)
    {
//        Account a = DBAccount.getInstance().getAccount(name);
//        if (a == null)
//        {
//            System.out.println("account is null");
//        } else
//        {
//            System.out.println(a);
//        }
    }

    public DatabaseTest()
    {
//        try
//        {
//            connectToAndQueryDatabase("BSSname", "BSSpass");
//        } catch (SQLException e)
//        {
//            System.out.println(e);
//        }
    }

//    public void connectToAndQueryDatabase(String username, String password) throws SQLException
//    {
//
//        Connection con = DriverManager.getConnection(
//                "jdbc:derby://localhost:1527/BSSdata",
//                username,
//                password);
//        Statement stmt2 = con.createStatement();
//        stmt2.executeUpdate("Insert into accounts (name, pass, playername) values ('naam', 'pass', 'playerNaam')");
//
//        Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");
//        System.out.println("boohjaa");
//        printRS(rs);
//    }
//
//    public void printRS(ResultSet rs) throws SQLException
//    {
//        while (rs.next())
//        {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            String pass = rs.getString("pass");
//            String playerName = rs.getString("playername");
//            System.out.println("ID: " + id + " - NAME: " + name + " - PASS: " + pass + " - PLAYERNAME: " + playerName);
//        }
//    }
}
