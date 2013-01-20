package eu.tjenwellens.bss.server.communication.parse;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackResult;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.map.GetMap;
import eu.tjenwellens.bss.server.components.map.GetTile;
import eu.tjenwellens.bss.server.mvc.view.View;
import eu.tjenwellens.bss.server.components.players.GetPlayer;
import eu.tjenwellens.bss.server.components.players.OpponentPlayer;
import eu.tjenwellens.bss.data.commands.dataToClient.DataFaction;
import eu.tjenwellens.bss.data.commands.dataToClient.DataForClient;
import eu.tjenwellens.bss.data.commands.dataToClient.DataGamer;
import eu.tjenwellens.bss.data.commands.dataToClient.DataMap;
import eu.tjenwellens.bss.data.commands.dataToClient.DataPlayer;
import eu.tjenwellens.bss.data.commands.dataToClient.DataTile;
import eu.tjenwellens.bss.data.commands.dataToClient.SDataFaction;
import eu.tjenwellens.bss.data.commands.dataToClient.SDataGamer;
import eu.tjenwellens.bss.data.commands.dataToClient.SDataMap;
import eu.tjenwellens.bss.data.commands.dataToClient.SDataPlayer;
import eu.tjenwellens.bss.data.commands.dataToClient.SDataTile;
import java.util.ArrayList;
import java.util.LinkedList;
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
        List<DataPlayer> players = new ArrayList<>();
        for (Map.Entry<Integer, GetPlayer> entry : view.getPlayers().entrySet())
        {
//                System.out.println("check players");
            GetPlayer p = entry.getValue();
            if (entry.getKey() == playerId)
            {
                AttackResult ar = p.getPreviousDuelResult();
                int duelresult = -1;
                if (ar != null)
                {
                    //TODO: duelresult tonen
                    duelresult = p.getPreviousDuelResult().hashCode();
                }
                Position dest = p.getDestination();
                int destX = -1;
                int destY = -1;
                if (dest != null)
                {
                    destX = dest.getX();
                    destY = dest.getY();
                }
                OpponentPlayer op = p.getOpponent();
                String oppName = null;
                if(op!=null){
                    oppName=op.getPlayerName();
                }
                gamer = new SDataGamer(destX, destY, duelresult, oppName, p.getPlayerName(), p.getPosition().getX(), p.getPosition().getY(), p.getFaction().getFactionId(), p.getState().name(), p.getWinns(), p.getLosses());
//                System.out.println("Gamer found");
            } else
            {
                players.add(new SDataPlayer(p.getPlayerName(), p.getPosition().getX(), p.getPosition().getY(), p.getFaction().getFactionId(), p.getState().name(), p.getWinns(), p.getLosses()));
            }
        }
        if (players.isEmpty())
        {
            players = null;
        }
        // init map
        List<DataTile> tiles = new LinkedList<>();
        for (GetTile t : view.getMap().getTiles())
        {
            tiles.add(new SDataTile(t.getRow(), t.getCol(), t.getFaction().getFactionId(), t.isWalled()));
        }
        GetMap m = view.getMap();
        DataMap map = new SDataMap(tiles, m.getRows(), m.getRows());
        if (map.isEmpty())
        {
            System.out.println("Map is empty");
            map = null;
        }
        // init factions
        List<DataFaction> factions = new ArrayList<>();
        for (Faction f : view.getFactions())
        {
            factions.add(new SDataFaction(f.getFactionId(), f.getFactionName()));
        }
        if (factions.isEmpty())
        {
            factions = null;
        }
        return new DataForClient(gamer, players, map, factions);
    }
}
