package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataGamer;

/**
 *
 * @author Tjen
 */
public class ClientGamer
{
    public String playerName;
    public Position position;
    public String factionName;
    public String state;
    public Position destination;
    public int winns;
    public int losses;
    public int duelResult;
    public String opponentName;

    public ClientGamer(DataGamer player)
    {
        this.playerName = player.getPlayerName();
        this.position = player.getPosition();
        this.factionName = player.getFactionName();
        this.state = player.getState();
        this.destination = player.getDestination();
        this.winns = player.getWinns();
        this.losses = player.getLosses();
        this.duelResult = player.getDuelResult();
        this.opponentName = player.getOpponentName();
    }

    @Override
    public String toString()
    {
        String n = "\n";
        String text = playerName + n
                + position + n
                + state + n
                + "'" + factionName + "'" + n;
        if (destination != null)
        {
            text += "towards: " + destination + n;
        }
        text += "opponent: " + opponentName + n
                + "w" + winns + " - l" + losses + n
                + "result:" + duelResult;
        return text;
    }
}
