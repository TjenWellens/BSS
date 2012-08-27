package eu.tjenwellens.bss.actionhandlers.decorateAction;

import eu.tjenwellens.bss.actionhandlers.ActionHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Tool;

/**
 *
 * @author tjen
 */
public interface DecorateHanderInterface extends ActionHandlerInterface
{
    public boolean addDecorate(DecoratePlayer player, Decoration decoration, int row, int col, Tool tool);

    public boolean updateDecorates();

    public void removeDecorate(Decorate decorate);
}
