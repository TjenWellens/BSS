package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.server.mvc.view.View;
import eu.tjenwellens.bss.data.commands.dataToClient.DataForClient;
import eu.tjenwellens.bss.server.communication.parse.ViewToData;

/**
 *
 * @author tjen
 */
public class Output implements OutputInterface
{
    ViewToData vtd;
    View view;

    public Output(ViewToData vtd, View view)
    {
        this.vtd = vtd;
        this.view = view;
    }

//    @Override
//    public String getData()
//    {
//        // TODO: return something from view?
////        return Model.getInstance().toData(BEGIN, PIECE_BEGIN, PIECE_END, END);
//        return null;
//    }
    @Override
    public DataForClient getData(int playerId)
    {
        return vtd.convert(view, playerId);
    }
}
