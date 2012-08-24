package eu.tjenwellens.bss.players;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackResult;
import eu.tjenwellens.bss.actionhandlers.bankAction.GetBankAccount;
import eu.tjenwellens.bss.actionhandlers.bankAction.shop.GetStore;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.players.inventory.GetInventory;
import eu.tjenwellens.bss.players.playerstate.PlayerStateType;

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
