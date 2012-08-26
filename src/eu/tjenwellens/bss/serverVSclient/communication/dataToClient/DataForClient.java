package eu.tjenwellens.bss.serverVSclient.communication.dataToClient;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataFaction;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataGamer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataMap;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataPlayer;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tjen
 */
public class DataForClient implements Serializable
{
    private DataGamer player;
    private List<DataPlayer> opponents;
    private DataMap map;
    private List<DataFaction> factions;

    public DataForClient(DataGamer player, List<DataPlayer> opponents, DataMap map, List<DataFaction> factions)
    {
        this.player = player;
        this.opponents = opponents;
        this.map = map;
        this.factions = factions;
    }

    public DataGamer getPlayer()
    {
        return player;
    }

    public List<DataFaction> getFactions()
    {
        return factions;
    }

    public List<DataPlayer> getOpponents()
    {
        return opponents;
    }

    public DataMap getMap()
    {
        return map;
    }
}
