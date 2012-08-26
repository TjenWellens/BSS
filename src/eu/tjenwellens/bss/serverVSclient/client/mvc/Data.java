package eu.tjenwellens.bss.serverVSclient.client.mvc;

import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.Tile;
import eu.tjenwellens.bss.serverVSclient.CommunicationConstants;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class Data implements CommunicationConstants
{
    String[] line;
    List<ClientPlayer> players;
    List<Tile> tiles;
    List<Faction> factions;

    /*
     * line 1: "update" line 2: "Players..."[endDelim]"Factions..."[endDelim]"Map..."
     */
    public Data(String[] line)
    {
        this.line = line;
        decode(line[1]);
    }

    private void decode(String data)
    {
        for (String block : data.split(END))
        {
            decodeBlock(block);
        }
    }

    private void decodeBlock(String block)
    {
        block = block.trim();
        if (block.startsWith("update"))
        {
            // do nothing and proceed to next block
        } else if (block.startsWith("players"))
        {
            decodePlayers(block);
        } else if (block.startsWith("map"))
        {
            decodeMap(block);
        } else if (block.startsWith("factions"))
        {
            decodeFactions(block);
        } else
        {
            System.out.println("UNKNOWN DATA RECEIVED");
        }
    }

    /* "players"    + [beginDelim]
     *              + playerName + [pieceDelim]
     *              + int factionId + [pieceDelim]
     *              + int x + [pieceDelim]
     *              + int y + [pieceDelim]
     *              + int stateID + [pieceDelim]
     *              + int continuesmoving
     *              + [pieceEnd]
     *              ...
     */
    private ArrayList<ClientPlayer> decodePlayers(String decode)
    {
        ArrayList<ClientPlayer> tempPlayers = new ArrayList<ClientPlayer>();

        String[] playerStrs = decode.split(BEGIN)[1].split(PIECE_END);

        for (String playerStr : playerStrs)
        {
            ClientPlayer player = new ClientPlayer();
            if (player.fromData(playerStr.split(PIECE_BEGIN)))
            {
                tempPlayers.add(player);
            } else
            {
                System.out.println("Decode failure");
            }
        }

        return tempPlayers;
    }

    private ArrayList<Tile> decodeMap(String decode)
    {
        ArrayList<Tile> tempTiles = new ArrayList<Tile>();

        return tempTiles;
    }

    private ArrayList<Faction> decodeFactions(String decode)
    {
        ArrayList<Faction> tempTiles = new ArrayList<Faction>();

        return tempTiles;
    }
}
