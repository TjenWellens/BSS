package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataPlayer;

/**
 *
 * @author tjen
 */
public class ClientPlayer
{
    public String playerName;
    public int yPosition;
    public int xPosition;
    public String factionName;
    public String state;
    public int winns;
    public int losses;

    public ClientPlayer(DataPlayer dataPlayer)
    {
        this.playerName = dataPlayer.getPlayerName();
        this.yPosition = dataPlayer.getYPosition();
        this.xPosition = dataPlayer.getXPosition();
        this.factionName = dataPlayer.getFactionName();
        this.state = dataPlayer.getState();
        this.winns = dataPlayer.getWinns();
        this.losses = dataPlayer.getLosses();
    }
}
