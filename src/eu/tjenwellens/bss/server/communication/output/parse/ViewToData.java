package eu.tjenwellens.bss.server.communication.output.parse;

import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.server.mvc.view.View;

/**
 *
 * @author Tjen
 */
public interface ViewToData
{
    public DataForClient convert(View view, int playerId);

    public DataForClient convertFactions(View view);

    public DataForClient convertMiniMap(View view);
}
