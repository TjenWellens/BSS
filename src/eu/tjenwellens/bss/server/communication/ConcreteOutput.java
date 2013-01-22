package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.server.communication.parse.SimpleViewToData;
import eu.tjenwellens.bss.server.communication.parse.ViewToData;
import eu.tjenwellens.bss.server.mvc.view.View;

/**
 *
 * @author tjen
 */
public class ConcreteOutput implements Output
{
    private ViewToData vtd;
    private View view;

    public ConcreteOutput(View view)
    {
        this.vtd = new SimpleViewToData();
        this.view = view;
    }

    @Override
    public DataForClient getData(int playerId)
    {
        return vtd.convert(view, playerId);
    }
}
