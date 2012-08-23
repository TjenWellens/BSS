/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.playerstate.playerstates;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.attackAction.AttackPlayer;
import eu.tjenwellens.bss.actionhandlers.decorateAction.DecorateHanderInterface;
import eu.tjenwellens.bss.actionhandlers.decorateAction.Decoration;
import eu.tjenwellens.bss.actionhandlers.engageAction.EngageHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.players.inventory.items.Tool;
import eu.tjenwellens.bss.players.playerstate.PlayerStateType;
import eu.tjenwellens.bss.players.playerstate.StatePlayer;

/**
 *
 * @author tjen
 */
public class PlayerBankState extends PlayerGeneralState
{

    protected PlayerBankState()
    {
    }

    @Override
    public String toString()
    {
        return getPlayerStateType().toString();
    }

    @Override
    public boolean isAttackable()
    {
        return false;
    }

    @Override
    public PlayerStateType getPlayerStateType()
    {
        return PlayerStateType.BANK;
    }

    @Override
    public boolean attack(StatePlayer player, AttackPlayer opponent)
    {
        return false;
    }

    @Override
    public boolean decorate(StatePlayer player, Decoration decoration, Position location, Tool tool, DecorateHanderInterface decorationHander)
    {
        return false;
    }

    @Override
    public boolean engage(StatePlayer player, AttackPlayer opponent, EngageHandlerInterface engageHandler)
    {
        return false;
    }

    @Override
    public boolean walk(StatePlayer player, Position destination, WalkHandlerInterface walkHandler)
    {
        return false;
    }
}
