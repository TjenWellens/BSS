package eu.tjenwellens.bss.data.commands.dataToClient;

/**
 *
 * @author Tjen
 */
public class SDataGamer extends SDataPlayer implements DataGamer
{
    private int xDestination;
    private int yDestination;
    private int duelResult;
    private String opponentName;

    public SDataGamer(int xDestination, int yDestination, int duelResult, String opponentName, String playerName, int xPosition, int yPosition, int factionId, String state, int winns, int losses)
    {
        super(playerName, xPosition, yPosition, factionId, state, winns, losses);
        this.xDestination = xDestination;
        this.yDestination = yDestination;
        this.duelResult = duelResult;
        this.opponentName = opponentName;
    }

    @Override
    public int getDuelResult()
    {
        return duelResult;
    }

    @Override
    public String getOpponentName()
    {
        return opponentName;
    }

    @Override
    public int getXDestination()
    {
        return xDestination;
    }

    @Override
    public int getYDestination()
    {
        return yDestination;
    }
}
