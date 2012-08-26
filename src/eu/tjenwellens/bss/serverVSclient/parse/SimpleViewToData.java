package eu.tjenwellens.bss.serverVSclient.parse;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.mvc.view.View;
import eu.tjenwellens.bss.players.GetPlayer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataFaction;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataGamer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataMap;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataPlayer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.SDataFaction;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.SDataGamer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.SDataMap;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.SDataPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tjen
 */
public class SimpleViewToData implements ViewToData
{
    @Override
    public DataForClient convert(View view, int playerId)
    {
        // init gamer & players
        DataGamer gamer = null;
        List<DataPlayer> players = new ArrayList<DataPlayer>();
        for (Map.Entry<Integer, GetPlayer> entry : view.getPlayers().entrySet())
        {
            if (entry.getKey() == playerId)
            {
                gamer = new SDataGamer(entry.getValue());
            } else
            {
                players.add(new SDataPlayer(entry.getValue()));
            }
        }
        if (players.isEmpty())
        {
            players = null;
        }
        // init map
        DataMap map = new SDataMap(view.getMap());
        if (map.isEmpty())
        {
            map = null;
        }
        // init factions
        List<DataFaction> factions = new ArrayList<DataFaction>();
        for (Faction faction : view.getFactions())
        {
            factions.add(new SDataFaction(faction));
        }
        if (factions.isEmpty())
        {
            factions = null;
        }
        return new DataForClient(gamer, players, map, factions);
    }
}
