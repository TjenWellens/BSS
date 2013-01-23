package eu.tjenwellens.bss.server.components.map;

import eu.tjenwellens.bss.server.actions.decorateAction.DecoratePlayer;
import eu.tjenwellens.bss.server.components.Position;

/**
 *
 * @author tjen
 */
public interface MapHandlerInterface
{
    GetMap getMapCopy();
    //not player but an interface of player

    boolean build(DecoratePlayer player, int row, int col);

    boolean paint(DecoratePlayer player, int row, int col);

    boolean destroy(DecoratePlayer player, int row, int col);

    GetTile getTile(int row, int col);
    
    boolean isPositionInMap(Position p);
}
