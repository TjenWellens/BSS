package eu.tjenwellens.bss.server.communication.init;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;

/**
 *
 * @author Tjen
 */
public class InitPlayer
{
    private int id = 0;
    private String playerName = null;
    private Faction faction = null;
    private Position position = null;
    private int winns = -1;
    private int losses = -1;
    private boolean play = false;
    private boolean login = false;
    private boolean pending = false;

    public InitPlayer()
    {
    }

    public InitPlayer(int id, String playerName)
    {
        this.id = id;
        this.playerName = playerName;
    }

    public void pending()
    {
        pending = true;
    }

    public boolean isPending()
    {
        return pending;
    }

    public boolean isLoginDone()
    {
        return login;
    }

    public boolean isFactionDone()
    {
        return faction != null;
    }

    public boolean isPositionDone()
    {
        return position != null;
    }

    public boolean isPlayDone()
    {
        return play;
    }

    public boolean isValid()
    {
        return id != 0 && playerName != null && faction != null && position != null;
    }

    public boolean isWinnsSet()
    {
        return winns >= 0;
    }

    public boolean isLossesSet()
    {
        return losses >= 0;
    }

    public int getId()
    {
        return id;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public Faction getFaction()
    {
        return faction;
    }

    public Position getPosition()
    {
        return position;
    }

    public int getWinns()
    {
        return winns;
    }

    public int getLosses()
    {
        return losses;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setWinns(int winns)
    {
        this.winns = winns;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public void loginFailed()
    {
        id = 0;
        playerName = null;
        pending = false;
    }

    public void factonFailed()
    {
        faction = null;
        pending = false;
    }

    public void positionFailed()
    {
        position = null;
        pending = false;
    }

    public void playFailed()
    {
        play = false;
        pending = false;
    }

    public void play()
    {
        System.out.println("InitPlayer.play");
        play = true;
        pending = false;
    }

    public void selectPosition(Position position)
    {
        this.position = position;
        pending = false;
    }

    public void selectFaction(Faction faction)
    {
        this.faction = faction;
        pending = false;
    }

    public void login()
    {
        login = true;
        pending = false;
    }
}
