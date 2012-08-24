package eu.tjenwellens.bss.mvc.model;

import java.util.HashMap;
import java.util.List;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.GetTile;
import eu.tjenwellens.bss.players.GetPlayer;

/**
 *
 * @author tjen
 */
public interface GetModelData
{
    GetTile[][] getMap();

    HashMap<String, GetPlayer> getPlayers();

    List<Faction> getFactions();
}
