package eu.tjenwellens.bss.client.components;

import eu.tjenwellens.bss.data.commands.dataToClient.DataPlayer;

/**
 *
 * @author tjen
 */
public class ClientPlayer
{
    public String playerName;
    public int xPosition;
    public int yPosition;
    public int factionId;
    public String state;
    public int winns;
    public int losses;

    public ClientPlayer(DataPlayer dataPlayer)
    {
        this.playerName = dataPlayer.getPlayerName();
        this.xPosition = dataPlayer.getXPosition();
        this.yPosition = dataPlayer.getYPosition();
        this.factionId = dataPlayer.getFactionId();
        this.state = dataPlayer.getState();
        this.winns = dataPlayer.getWinns();
        this.losses = dataPlayer.getLosses();
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public int getYPosition()
    {
        return yPosition;
    }

    public int getXPosition()
    {
        return xPosition;
    }

    public int getFactionId()
    {
        return factionId;
    }

    public String getState()
    {
        return state;
    }

    public int getWinns()
    {
        return winns;
    }

    public int getLosses()
    {
        return losses;
    }

    @Override
    public String toString()
    {
        String n = "\n";
        String text = "playerName: " + playerName + n
                + "pos: " + xPosition + "," + yPosition + n
                + "factionId: " + factionId + n
                + "state: " + state + n
                + "winns: " + winns + n
                + "losses: " + losses + n;
        return text;
    }
}
