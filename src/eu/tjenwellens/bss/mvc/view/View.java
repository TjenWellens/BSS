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
    private GetModelData model;
    private List<Faction> factions = null;
    private GetTile[][] map = null;
    private HashMap<Integer, GetPlayer> players = null;

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

    public GetModelData getModel()
    {
        return model;
    }

    public List<Faction> getFactions()
    {
        return factions;
    }

    public GetTile[][] getMap()
    {
        return map;
    }

    public HashMap<Integer, GetPlayer> getPlayers()
    {
        return players;
    }
}
