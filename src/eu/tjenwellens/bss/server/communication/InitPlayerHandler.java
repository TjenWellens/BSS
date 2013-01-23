package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;
import java.util.HashMap;

/**
 *
 * @author Tjen
 */
public class InitPlayerHandler
{
    private HashMap<Integer, InitPlayer> players = new HashMap<>();

    public boolean loginPlayer(InitPlayer ip)
    {
        if (players.containsKey(ip.getId()))
        {
            ip.loginFailed();
            return false;
        }
        ip.login();
        players.put(ip.getId(), ip);
        return true;
    }

    public boolean selectFaction(int id, Faction faction)
    {
        InitPlayer ip = players.get(id);
        if (ip == null)
        {
            return false;
        }
        if (faction == null)
        {
            ip.factonFailed();
            return false;
        }
        ip.selectFaction(faction);
        return true;
    }

    public boolean selectPosition(int id, Position p)
    {
        InitPlayer ip = players.get(id);
        if (ip == null)
        {
            return false;
        }
        if (p == null)
        {
            ip.positionFailed();
            return false;
        }
        ip.selectPosition(p);
        return true;
    }

    public boolean updatePlayerID(int id, int newID)
    {
        if (players.containsKey(newID))
        {
            return false;
        }
        InitPlayer ip = players.remove(id);
        if (ip == null)
        {
            return false;
        }
        ip.setId(newID);
        players.put(ip.getId(), ip);
        return true;
    }

    public InitPlayer play(int id)
    {
        InitPlayer ip = players.get(id);
        if (ip == null)
        {
            return null;
        }
        if (!ip.isValid())
        {
            ip.playFailed();
            return null;
        }
        players.remove(id);
        ip.play();
        return ip;
    }
}
