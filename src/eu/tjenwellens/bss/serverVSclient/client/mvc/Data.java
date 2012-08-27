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
public class Data
{
    ClientGamer gamer = null;
    List<ClientPlayer> players = null;
    ClientMap map = null;
    List<ClientFaction> factions = null;

    public Data(DataForClient dfc)
    {
        // gamer
        DataGamer dg = dfc.getPlayer();
        if (dg != null)
        {
            this.gamer = new ClientGamer(dg);
        }
        // players
        List<DataPlayer> dps = dfc.getOpponents();
        if (dps != null)
        {
            players = new ArrayList<ClientPlayer>();
            for (DataPlayer dataPlayer : dps)
            {
                players.add(new ClientPlayer(dataPlayer));
            }
        }
        // map
        DataMap dm = dfc.getMap();
        if (dm != null)
        {
            map = new ClientMap(dm);
        }
        // factions
        List<DataFaction> dfs = dfc.getFactions();
        if (dfs != null)
        {
            factions = new ArrayList<ClientFaction>();
            for (DataFaction dataFaction : dfs)
            {
                factions.add(new ClientFaction(dataFaction));
            }
        }
    }
}
