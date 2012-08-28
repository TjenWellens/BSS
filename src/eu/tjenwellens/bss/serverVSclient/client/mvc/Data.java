package eu.tjenwellens.bss.serverVSclient.client.mvc;

import eu.tjenwellens.bss.serverVSclient.client.components.ClientFaction;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientGamer;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientMap;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientPlayer;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface Data
{
    public ClientGamer getGamer();

    public List<ClientPlayer> getPlayers();

    public ClientMap getMap();

    public List<ClientFaction> getFactions();
}
