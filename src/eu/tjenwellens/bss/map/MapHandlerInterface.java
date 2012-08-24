package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecoratePlayer;

/**
 *
 * @author tjen
 */
public interface MapHandlerInterface
{
    GetTile[][] getMapCopy();
    //not player but an interface of player

    boolean build(DecoratePlayer player, Position tilePosition);

    boolean paint(DecoratePlayer player, Position tilePosition);

    boolean destroy(DecoratePlayer player, Position tilePosition);

    GetTile getTile(int row, int col);
}
