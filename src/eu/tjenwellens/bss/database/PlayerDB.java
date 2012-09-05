package eu.tjenwellens.bss.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    // queries
    private static final String insert =
            " INSERT INTO " + TABLE
            + " ( " + KEY_ID + " , " + KEY_PLAYERNAME + " , " + KEY_WINNS + " , " + KEY_LOSSES
            + " ) values (?, ?, ?, ?)";
    private static final String update =
            "UPDATE " + TABLE
            + " SET " + KEY_ID + " = ?, " + KEY_PLAYERNAME + " = ?, " + KEY_WINNS + " = ?, " + KEY_LOSSES + " = ?"
            + " WHERE " + KEY_ID + " = ?";
    private static final String getPlayer = "SELECT * FROM " + TABLE + " WHERE " + KEY_ID + " = ?";

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
        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(getPlayer))
        {
            s.setInt(1, id);
            try (ResultSet rs = s.executeQuery())
            {
                if (rs.next())
                {
//                int id = rs.getInt(KEY_ID);
                    String playerName = rs.getString(KEY_PLAYERNAME);
                    int winns = rs.getInt(KEY_WINNS);
                    int losses = rs.getInt(KEY_LOSSES);
                    player = new DBPlayer(id, playerName, winns, losses);
                }
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
        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(insert))
        {
            s.setInt(1, id);
            s.setString(2, playerName);
            s.setInt(3, 0);
            s.setInt(4, 0);
            i = s.executeUpdate();
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
        String prep;
        boolean exists = getPlayer(id) != null;
        if (exists)
        {
            prep = update;
        } else
        {
            prep = insert;
        }
        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(prep))
        {
            s.setInt(1, player.getPlayerID());
            s.setString(2, player.getPlayerName());
            s.setInt(3, player.getWinns());
            s.setInt(4, player.getLosses());
            if (exists)
            {
                s.setInt(5, id);
            }
            i = s.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println("hier: " + e);
        }
        return i > 0;
    }
}
