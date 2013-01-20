package eu.tjenwellens.bss.server.communication;

/**
 *
 * @author Tjen
 */
public class Account
{
    private int id;
    private String name;
    private String pass;
    private String playerName;

    public Account(int id, String name, String pass, String playerName)
    {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.playerName = playerName;
    }

    @Override
    public String toString()
    {
        return "Account{" + "id=" + id + ", name=" + name + ", pass=" + pass + ", playerName=" + playerName + '}';
    }

    boolean validate(String name, String pass, String playerName)
    {
        if (!this.name.equals(name))
        {
            return false;
        }
        if (!this.pass.equals(pass))
        {
            return false;
        }
        if (!this.playerName.equals(playerName))
        {
            return false;
        }
        return true;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public String getPass()
    {
        return pass;
    }

    public String getPlayerName()
    {
        return playerName;
    }
}
