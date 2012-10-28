package eu.tjenwellens.bss.serverVSclient.client.mvc;

import eu.tjenwellens.bss.serverVSclient.client.components.ClientFaction;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientGamer;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientMap;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientPlayer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataFaction;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataGamer;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataMap;
import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class ClientModel implements Observable, Data, ServerToModel
{
    private ClientGamer gamer = null;
    private List<ClientPlayer> players = null;
    private ClientMap map = null;
    private List<ClientFaction> factions = null;
    private final List<Observer> observers = new ArrayList<>();
    private boolean updateReady;

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
//            System.out.println("notifyobservers");
            notifyObservers();
        }
    }

    @Override
    public void registerObserver(Observer o)
    {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o)
    {
        observers.remove(o);
    }

    private void notifyObservers()
    {
        List<Observer> observerCopy;
        synchronized (this.observers)
        {
            observerCopy = new ArrayList<>(this.observers);
        }
        for (Observer observer : observerCopy)
        {
//            System.out.println("notify");
            observer.update();
        }
        updateReady = false;
    }
}
