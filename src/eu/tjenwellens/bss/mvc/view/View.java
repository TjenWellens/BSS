package eu.tjenwellens.bss.mvc.view;

import java.util.HashMap;
import java.util.List;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.GetTile;
import eu.tjenwellens.bss.mvc.model.GetModelData;
import eu.tjenwellens.bss.mvc.observe.GlobalObserver;
import eu.tjenwellens.bss.players.GetPlayer;

/**
 *
 * @author tjen
 */
public class View implements GlobalObserver
{
    public GetModelData model;
    public List<Faction> factions = null;
    public GetTile[][] map = null;
    public HashMap<String, GetPlayer> players = null;

    public View(GetModelData model)
    {
        this.model = model;
    }

    @Override
    public void notifyGlobalObserver()
    {
        factions = model.getFactions();
        map = model.getMap();
        players = model.getPlayers();
    }
}
