/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.decorateAction;

import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.players.inventory.items.Tool;

/**
 *
 * @author tjen
 */
public interface DecoratePlayer extends ActionPlayer
{

    Faction getFaction();

    boolean useTool(Tool tool);
}
