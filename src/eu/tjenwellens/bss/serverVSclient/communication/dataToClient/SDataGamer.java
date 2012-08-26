package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.actionhandlers.attackAction.AttackResult;
import eu.tjenwellens.bss.players.GetPlayer;

/**
 *
 * @author Tjen
 */
public class SDataGamer implements DataGamer
{
    private String playerName;
    private int xPosition;
    private int yPosition;
    private String factionName;
    private String state;
    private int xDestination;
    private int yDestination;
    private int winns;
    private int losses;
    private int duelResult;
    private String opponentName;

    public SDataGamer(GetPlayer player)
    {
        playerName = player.getPlayerName();
        xPosition = player.getPosition().getX();
        yPosition = player.getPosition().getY();
        factionName = player.getFaction().getFactionName();
        state = player.getState().name();
        xDestination = player.getDestination().getX();
        yDestination = player.getDestination().getY();
        winns = player.getWinns();
        losses = player.getLosses();
        AttackResult a = player.getPreviousDuelResult();
        duelResult = a.isDraw() ? 0 : a.getWinner().getPlayerID() == player.getPlayerID() ? 1 : 0;
        opponentName = player.getOpponent().getPlayerName();
    }

    @Override
    public String getPlayerName()
    {
        return playerName;
    }

    @Override
    public int getXPosition()
    {
        return xPosition;
    }

    @Override
    public int getYPosition()
    {
        return yPosition;
    }

    @Override
    public String getFactionName()
    {
        return factionName;
    }

    @Override
    public String getState()
    {
        return state;
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
}
