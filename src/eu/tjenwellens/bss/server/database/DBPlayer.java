package eu.tjenwellens.bss.server.database;

/**
 *
 * @author Tjen
 */
public class DBPlayer implements SavePlayer
{
    private int id;
    private String playerName;
    private int winns;
    private int losses;

    public DBPlayer(int id, String playerName)
    {
        this.id = id;
        this.playerName = playerName;
    }

    public DBPlayer(int id, String playerName, int winns, int losses)
    {
        this.id = id;
        this.playerName = playerName;
        this.winns = winns;
        this.losses = losses;
    }

    @Override
    public String toString()
    {
        return "DBPlayer{" + "id=" + id + ", playerName=" + playerName + ", winns=" + winns + ", losses=" + losses + '}';
    }

    @Override
    public int getPlayerID()
    {
        return id;
    }

    @Override
    public String getPlayerName()
    {
        return playerName;
    }

    @Override
    public int getWinns()
    {
        return winns;
    }

    @Override
    public int getLosses()
    {
        return losses;
    }
}
