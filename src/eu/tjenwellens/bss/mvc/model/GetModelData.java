package eu.tjenwellens.bss.mvc.model;

import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.GetMap;
import eu.tjenwellens.bss.players.GetPlayer;
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
