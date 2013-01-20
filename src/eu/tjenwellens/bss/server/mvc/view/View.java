package eu.tjenwellens.bss.server.mvc.view;

import java.util.HashMap;
import java.util.List;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.map.GetMap;
import eu.tjenwellens.bss.server.components.map.GetTile;
import eu.tjenwellens.bss.server.mvc.model.GetModelData;
import eu.tjenwellens.bss.server.mvc.model.observers.GlobalObserver;
import eu.tjenwellens.bss.server.components.players.GetPlayer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author tjen
 */
public class View implements GlobalObserver
{
    private volatile GetModelData model;
    private volatile List<Faction> factions = null;
    private volatile GetMap map = null;
    private volatile HashMap<Integer, GetPlayer> players = null;

    public View(GetModelData model)
    {
        this.model = model;
    }

    @Override
    public void notifyGlobalObserver()
    {
//        System.out.println("notified");
        factions = model.getFactions();
        map = new PrivateMap(model.getMap());
//        System.out.println("View map"+model.getMap());
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

    public GetMap getMap()
    {
        return map;
    }

    public HashMap<Integer, GetPlayer> getPlayers()
    {
        return players;
    }

    private static class PrivateMap implements GetMap
    {
        private int rows;
        private int cols;
        private List<GetTile> tiles;

        public PrivateMap(GetMap getMap)
        {
            rows = getMap.getRows();
            cols = getMap.getCols();
            tiles = getMap.getTiles();
        }

        @Override
        public List<GetTile> getTiles()
        {
            return tiles;
        }

        @Override
        public int getRows()
        {
            return rows;
        }

        @Override
        public int getCols()
        {
            return cols;
        }
    }
}
