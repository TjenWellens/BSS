package eu.tjenwellens.bss.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tjen
 */
public class PlayerDB extends DB
{
    private static final String TABLE = "players";
    // keys
    private static final String KEY_ID = "id";
    private static final String KEY_PLAYERNAME = "playername";
    private static final String KEY_WINNS = "winns";
    private static final String KEY_LOSSES = "losses";

    public DBPlayer getPlayer(int id, String playerName)
    {
        DBPlayer player = getPlayer(id);
        if (player == null)
        {
            player = new DBPlayer(id, playerName);
        }
        return player;
    }

    private DBPlayer getPlayer(int id)
    {
        DBPlayer player = null;
        String q = "SELECT * FROM " + TABLE + " WHERE " + KEY_ID + " = " + id;
        try (Connection c = getConnection(); Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(q))
        {

            if (rs.next())
            {
//                int id = rs.getInt(KEY_ID);
                String playerName = rs.getString(KEY_PLAYERNAME);
                int winns = rs.getInt(KEY_WINNS);
                int losses = rs.getInt(KEY_LOSSES);
                player = new DBPlayer(id, playerName, winns, losses);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return player;
    }

    public boolean addPlayer(int id, String playerName)
    {
//        name = name.toLowerCase();
        int i = 0;
        String q = "Insert into " + TABLE + " (" + KEY_ID + ", " + KEY_PLAYERNAME + ") values ('" + id + "', '" + playerName + "')";
        try (Connection c = getConnection(); Statement stmt = c.createStatement())
        {
            i = stmt.executeUpdate(q);
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return i > 0;
    }

    public boolean updatePlayer(int id, SavePlayer player)
    {
//        name = name.toLowerCase();
        int i = 0;
        String update = "UPDATE " + TABLE
                + " SET " + KEY_ID + " = " + player.getPlayerID()
                + " , " + KEY_PLAYERNAME + " = '" + player.getPlayerName() + "'"
                + " , " + KEY_WINNS + " = " + player.getWinns()
                + " , " + KEY_LOSSES + " = " + player.getLosses()
                + " WHERE " + KEY_ID + " = " + id;
        String insert = " INSERT INTO " + TABLE
                + " ( " + KEY_ID
                + " , " + KEY_PLAYERNAME
                + " , " + KEY_WINNS
                + " , " + KEY_LOSSES
                + " ) values ( " + player.getPlayerID()
                + " , " + "'" + player.getPlayerName() + "'"
                + " , " + player.getWinns()
                + " , " + player.getLosses() + ")";
        DBPlayer p = getPlayer(id);
        try (Connection c = getConnection(); Statement stmt = c.createStatement())
        {
            if (p == null)
            {
                i = stmt.executeUpdate(insert);
            } else
            {
                i = stmt.executeUpdate(update);
            }
//            if (i == 0)
//            {
//                i = stmt.executeUpdate(create);
//            }
        } catch (SQLException e)
        {
            System.out.println("hier: " + e);
        }
        return i > 0;
    }
}
