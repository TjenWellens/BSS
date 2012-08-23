/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.map;

import eu.tjenwellens.bss.actionhandlers.decorateAction.DecoratePlayer;
import eu.tjenwellens.bss.Position;

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
