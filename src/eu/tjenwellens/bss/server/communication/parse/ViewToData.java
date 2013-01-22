package eu.tjenwellens.bss.server.communication.parse;

import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.server.mvc.view.View;

/**
 *
 * @author Tjen
 */
public interface ViewToData
{
    public DataForClient convert(View view, int playerId);
}
