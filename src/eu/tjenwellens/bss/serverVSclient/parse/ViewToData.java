package eu.tjenwellens.bss.serverVSclient.parse;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataForClient;
import eu.tjenwellens.bss.mvc.view.View;

/**
 *
 * @author Tjen
 */
public interface ViewToData
{
    public DataForClient convert(View view, int playerId);
}
