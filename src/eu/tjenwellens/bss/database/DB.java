package eu.tjenwellens.bss.database;

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

    protected Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
