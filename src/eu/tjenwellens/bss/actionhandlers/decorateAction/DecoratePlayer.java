package eu.tjenwellens.bss.actionhandlers.decorateAction;

import eu.tjenwellens.bss.actionhandlers.ActionPlayer;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.players.inventory.items.Item;
import eu.tjenwellens.bss.players.inventory.items.Tool;

/**
 *
 * @author tjen
 */
public interface DecoratePlayer extends ActionPlayer
{
    boolean idle();

    Faction getFaction();

    boolean useTool(Tool tool);

    boolean hasItem(Item item);
}
