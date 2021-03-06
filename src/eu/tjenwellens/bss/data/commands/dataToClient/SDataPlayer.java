package eu.tjenwellens.bss.data.commands.dataToClient;

//import eu.tjenwellens.bss.players.GetPlayer;

/**
 *
 * @author Tjen
 */
public class SDataPlayer implements DataPlayer
{
    private String playerName;
    private int xPosition;
    private int yPosition;
    private int factionId;
    private String state;
    private int winns;
    private int losses;

    public SDataPlayer(String playerName, int xPosition, int yPosition, int factionId, String state, int winns, int losses)
    {
        this.playerName = playerName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.factionId = factionId;
        this.state = state;
        this.winns = winns;
        this.losses = losses;
    }
//
//    public SDataPlayer(GetPlayer player)
//    {
//        this.playerName = player.getPlayerName();
//        this.yPosition = player.getPosition().getX();
//        this.xPosition = player.getPosition().getY();
//        this.factionName = player.getFaction().getFactionName();
//        this.state = player.getState().name();
//        this.winns = player.getWinns();
//        this.losses = player.getLosses();
//    }

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

    @Override
    public int getFactionId()
    {
        return factionId;
    }
}
