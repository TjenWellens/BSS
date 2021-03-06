package eu.tjenwellens.bss.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tjen
 */
public class DB
{
    // db login
    private static final String URL = "jdbc:derby://localhost:1527/BSSdata";
    private static final String USER = "BSSname";
    private static final String PASSWORD = "BSSpass";

    static
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
            System.out.println(ex);
        }
    }

    protected static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean canConnect()
    {
        try (Connection c = getConnection())
        {
            System.out.println("Database server found");
        } catch (SQLException e)
        {
            System.out.println("No database server found...");
            return false;
        }
        return true;
    }
}
