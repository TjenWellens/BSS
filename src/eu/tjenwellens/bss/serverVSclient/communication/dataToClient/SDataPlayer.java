package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.players.GetPlayer;

/**
 *
 * @author Tjen
 */
public class SDataPlayer implements DataPlayer
{
    private String playerName;
    private int yPosition;
    private int xPosition;
    private String factionName;
    private String state;
    private int winns;
    private int losses;

    public SDataPlayer(GetPlayer player)
    {
        this.playerName = player.getPlayerName();
        this.yPosition = player.getPosition().getX();
        this.xPosition = player.getPosition().getY();
        this.factionName = player.getFaction().getFactionName();
        this.state = player.getState().name();
        this.winns = player.getWinns();
        this.losses = player.getLosses();
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
    public int getWinns()
    {
        return winns;
    }

    @Override
    public int getLosses()
    {
        return losses;
    }
}
