package eu.tjenwellens.bss.server.components.players.playerstate;

import eu.tjenwellens.bss.server.components.players.playerstate.playerstates.PlayerState;
import eu.tjenwellens.bss.server.actions.ActionHandlerInterface;
import eu.tjenwellens.bss.server.actions.bankAction.BankPlayer;
import eu.tjenwellens.bss.server.actions.walkAction.WalkPlayer;
import eu.tjenwellens.bss.server.actions.attackAction.AttackPlayer;
import eu.tjenwellens.bss.server.components.factions.FactionPlayer;
import eu.tjenwellens.bss.server.actions.decorateAction.DecoratePlayer;

/**
 *
 * @author tjen
 */
public interface StatePlayer extends WalkPlayer, AttackPlayer, DecoratePlayer, FactionPlayer, BankPlayer
{
    void setState(PlayerState state);

    void resetActions();

    void addActionHandler(ActionHandlerInterface actionHandler);
}
