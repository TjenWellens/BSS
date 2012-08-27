package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataGamer;

/**
 *
 * @author Tjen
 */
public class ClientGamer
{
    public String playerName;
    public int xPosition;
    public int yPosition;
    public String factionName;
    public String state;
    public int xDestination;
    public int yDestination;
    public int winns;
    public int losses;
    public int duelResult;
    public String opponentName;

    public ClientGamer(DataGamer player)
    {
        this.playerName = player.getPlayerName();
        this.xPosition = player.getXPosition();
        this.yPosition = player.getYPosition();
        this.factionName = player.getFactionName();
        this.state = player.getState();
        this.xDestination = player.getXDestination();
        this.yDestination = player.getYDestination();
        this.winns = player.getWinns();
        this.losses = player.getLosses();
        this.duelResult = player.getDuelResult();
        this.opponentName = player.getOpponentName();
    }
}
