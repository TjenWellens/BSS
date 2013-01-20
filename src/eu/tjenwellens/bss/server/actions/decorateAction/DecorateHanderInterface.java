package eu.tjenwellens.bss.server.actions.decorateAction;

import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;
import eu.tjenwellens.bss.server.components.items.Tool;

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
