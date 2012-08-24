package eu.tjenwellens.bss.players.playerstate.playerstates;

import eu.tjenwellens.bss.players.playerstate.PlayerStateType;

/**
 *
 * @author tjen
 */
public class PlayerWalkState extends PlayerGeneralState
{
    protected PlayerWalkState()
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
        return PlayerStateType.WALK;
    }
}
