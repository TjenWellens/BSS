package eu.tjenwellens.bss.server.components.players;

import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.actions.attackAction.AttackResult;
import eu.tjenwellens.bss.server.actions.bankAction.GetBankAccount;
import eu.tjenwellens.bss.server.actions.bankAction.shop.GetStore;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.players.inventory.GetInventory;
import eu.tjenwellens.bss.server.components.players.playerstate.playerstates.PlayerStateType;

/**
 *
 * @author tjen
 */
public interface GetPlayer
{
    int getPlayerID();

    String getPlayerName();

    Position getPosition();

    Faction getFaction();

    PlayerStateType getState();
    // walk

    Position getDestination();
    // battleresult

    int getWinns();

    int getLosses();

    AttackResult getPreviousDuelResult();
    // bank

    GetInventory getInventory();

    GetBankAccount getBankAccount();

    GetStore getStore();
    // battle

    OpponentPlayer getOpponent();
}
