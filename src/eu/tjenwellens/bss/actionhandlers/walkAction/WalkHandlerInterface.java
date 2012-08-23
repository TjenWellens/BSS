/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction;

import eu.tjenwellens.bss.actionhandlers.walkAction.walk.WalkInterface;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;

/**
 *
 * @author tjen
 */
public interface WalkHandlerInterface extends ActionHandlerInterface
{

    public boolean addWalk(WalkPlayer player, Position destination);

    public void removeWalk(WalkInterface walk);

    boolean updateWalks();
}
