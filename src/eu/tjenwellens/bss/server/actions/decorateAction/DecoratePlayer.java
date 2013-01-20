package eu.tjenwellens.bss.server.actions.decorateAction;

import eu.tjenwellens.bss.server.actions.ActionPlayer;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Tool;

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
