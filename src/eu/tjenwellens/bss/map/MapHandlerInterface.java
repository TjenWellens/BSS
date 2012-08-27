package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.actionhandlers.decorateAction.DecoratePlayer;

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
}
