package eu.tjenwellens.bss.serverVSclient.client.components;

/**
 *
 * @author tjen
 */
public class ClientPlayer
{
    /* "players"    + [beginDelim]
     + playerName + [pieceDelim]
     + int factionId + [pieceDelim]
     + int x + [pieceDelim]
     + int y + [pieceDelim]
     + int stateID + [pieceDelim]
     + int continuesmoving + [pieceEnd]
     ...
     + [endDelim]
     */
    String playerName;
    int factionID;
    int x;
    int y;
    int stateID;
    int continuesMoving;

    public boolean fromData(String[] props)
    {
        if (props.length < 6)
        {
            return false;
        }
        playerName = props[0];
        try
        {
            factionID = Integer.parseInt(props[1]);
            x = Integer.parseInt(props[2]);
            y = Integer.parseInt(props[3]);
            stateID = Integer.parseInt(props[4]);
            continuesMoving = Integer.parseInt(props[5]);
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}
