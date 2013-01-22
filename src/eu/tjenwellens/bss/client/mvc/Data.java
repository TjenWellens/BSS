package eu.tjenwellens.bss.client.mvc;

import eu.tjenwellens.bss.client.components.ClientFaction;
import eu.tjenwellens.bss.client.components.ClientGamer;
import eu.tjenwellens.bss.client.components.ClientMap;
import eu.tjenwellens.bss.client.components.ClientPlayer;
import java.util.List;

/**
 *
 * @author Tjen
 */
public interface Data
{
    ClientGamer getGamer();

    List<ClientPlayer> getPlayers();

    ClientMap getMap();

    List<ClientFaction> getFactions();

    boolean isInitDone();
}
