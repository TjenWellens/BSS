package eu.tjenwellens.bss.client.mvc;

import eu.tjenwellens.bss.client.components.ClientFaction;
import eu.tjenwellens.bss.client.components.ClientGamer;
import eu.tjenwellens.bss.client.components.ClientMap;
import eu.tjenwellens.bss.client.components.ClientPlayer;
import eu.tjenwellens.bss.data.commands.dataToClient.DataFaction;
import eu.tjenwellens.bss.data.commands.dataToClient.DataGamer;
import eu.tjenwellens.bss.data.commands.dataToClient.DataMap;
import eu.tjenwellens.bss.data.commands.dataToClient.DataPlayer;
import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.update.Updatable;
import eu.tjenwellens.update.Updater;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class ClientModel implements Updater, Data, ServerToModel
{
    private ClientGamer gamer = null;
    private List<ClientPlayer> players = null;
    private ClientMap map = null;
    private List<ClientFaction> factions = null;
    private final List<Updatable> observers = new ArrayList<>();
    private boolean updateReady;
    private boolean initDone = false;

    @Override
    synchronized public ClientGamer getGamer()
    {
        return this.gamer;
    }

    @Override
    synchronized public List<ClientPlayer> getPlayers()
    {
        return this.players;
    }

    @Override
    synchronized public ClientMap getMap()
    {
        return this.map;
    }

    @Override
    synchronized public List<ClientFaction> getFactions()
    {
        return this.factions;
    }

    @Override
    public void updateData(DataForClient dfc)
    {
        updateReady = false;
        // gamer
        DataGamer dg = dfc.getPlayer();
        if (dg != null)
        {
            this.gamer = new ClientGamer(dg);
            updateReady = true;
        }
        // players
        List<DataPlayer> dps = dfc.getOpponents();
        if (dps != null)
        {
            this.players = new ArrayList<>();
            for (DataPlayer dataPlayer : dps)
            {
                this.players.add(new ClientPlayer(dataPlayer));
            }
            updateReady = true;
        }
        // map
        DataMap dm = dfc.getMap();
        if (dm != null)
        {
            this.map = new ClientMap(dm);
            updateReady = true;
        }
        // factions
        List<DataFaction> dfs = dfc.getFactions();
        if (dfs != null)
        {
            this.factions = new ArrayList<>();
            for (DataFaction dataFaction : dfs)
            {
                this.factions.add(new ClientFaction(dataFaction));
            }
            updateReady = true;
        }
        // notify observers
        if (updateReady)
        {
            notifyObservers();
            updateReady = false;
        }
    }

    @Override
    public boolean registerUpdatable(Updatable u)
    {
        return observers.add(u);
    }

    @Override
    public boolean removeUpdatable(Updatable u)
    {
        return observers.remove(u);
    }

    private void notifyObservers()
    {
        List<Updatable> observerCopy;
        if (this.observers.isEmpty())
        {
            return;
        }
        synchronized (this.observers)
        {
            observerCopy = new ArrayList<>(this.observers);
        }
        for (Updatable observer : observerCopy)
        {
//            System.out.println("notify");
            observer.update();
        }
    }

    @Override
    public void fullInitDone()
    {
        System.out.println("Model init done");
        initDone = true;
        notifyObservers();
    }

    @Override
    public boolean isInitDone()
    {
        return initDone;
    }
}
