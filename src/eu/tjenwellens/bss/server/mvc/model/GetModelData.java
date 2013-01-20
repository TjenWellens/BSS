package eu.tjenwellens.bss.server.mvc.model;

import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.map.GetMap;
import eu.tjenwellens.bss.server.components.players.GetPlayer;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tjen
 */
public interface GetModelData
{
    GetMap getMap();

    HashMap<Integer, GetPlayer> getPlayers();

    List<Faction> getFactions();
}
