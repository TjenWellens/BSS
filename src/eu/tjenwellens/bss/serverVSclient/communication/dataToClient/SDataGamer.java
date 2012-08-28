package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackResult;
import eu.tjenwellens.bss.players.GetPlayer;
import eu.tjenwellens.bss.players.OpponentPlayer;

/**
 *
 * @author Tjen
 */
public class SDataGamer implements DataGamer
{
    private String playerName;
    private Position position;
    private String factionName;
    private String state;
    private Position destination;
    private int winns;
    private int losses;
    private int duelResult;
    private String opponentName;

    public SDataGamer(GetPlayer player)
    {
        playerName = player.getPlayerName();
        position = player.getPosition();
        factionName = player.getFaction().getFactionName();
        state = player.getState().name();
        destination = player.getDestination();
        winns = player.getWinns();
        losses = player.getLosses();
        AttackResult a = player.getPreviousDuelResult();
        if (a != null)
        {
            duelResult = a.isDraw() ? 0 : a.getWinner().getPlayerID() == player.getPlayerID() ? 1 : 0;
        } else
        {
            duelResult = -3;
        }
        OpponentPlayer op = player.getOpponent();
        if (op == null)
        {
            opponentName = null;
        } else
        {
            opponentName = player.getOpponent().getPlayerName();
        }
    }

    @Override
    public String getPlayerName()
    {
        return playerName;
    }

    public Position getPosition()
    {
        return position;
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

    public Position getDestination()
    {
        return destination;
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
