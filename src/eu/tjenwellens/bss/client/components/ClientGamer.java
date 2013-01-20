package eu.tjenwellens.bss.client.components;

import eu.tjenwellens.bss.data.commands.dataToClient.DataGamer;

/**
 *
 * @author Tjen
 */
public class ClientGamer extends ClientPlayer
{
    public int xDestination;
    public int yDestination;
    public int duelResult;
    public String opponentName;

    public ClientGamer(DataGamer player)
    {
        super(player);
        this.xDestination = player.getXDestination();
        this.yDestination = player.getYDestination();
        this.duelResult = player.getDuelResult();
        this.opponentName = player.getOpponentName();
    }

    @Override
    public String toString()
    {
        String n = "\n";
        String text = super.toString()
                + "towards: " + xDestination + "," + yDestination + n
                + "opponent: " + opponentName + n
                + "w" + winns + " - l" + losses + n
                + "result:" + duelResult;
        return text;
    }
}
