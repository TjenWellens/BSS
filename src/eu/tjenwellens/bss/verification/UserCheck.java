package eu.tjenwellens.bss.verification;

import java.util.HashMap;

/**
 *
 * @author tjen
 */
public class UserCheck
{
    private HashMap<String, ID> pnid = new HashMap<String, ID>();

    private synchronized boolean checkID(ID id, String playerName)
    {
        if (pnid.containsKey(playerName))
        {
            if (pnid.get(playerName).equals(id))
            {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean createPlayer(ID id, String playerName)
    {
        if (!pnid.containsKey(playerName))
        {
            pnid.put(playerName, id);
            if (checkID(id, playerName))
            {
                return true;
            } else
            {
                pnid.remove(playerName);
                return false;
            }
        }
        return false;
    }
}
