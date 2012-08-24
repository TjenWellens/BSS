package eu.tjenwellens.bss.players.playerstate.playerstates;

import eu.tjenwellens.bss.players.playerstate.PlayerStateType;
import eu.tjenwellens.bss.players.playerstate.StatePlayer;

/**
 *
 * @author tjen
 */
public class PlayerIdleState extends PlayerGeneralState
{
    protected PlayerIdleState()
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
        return true;
    }

    @Override
    public PlayerStateType getPlayerStateType()
    {
        return PlayerStateType.IDLE;
    }

    @Override
    public boolean idle(StatePlayer player)
    {
        return true;
    }
}
