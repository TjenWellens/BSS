package eu.tjenwellens.bss.players.playerstate.playerstates;

import eu.tjenwellens.bss.players.playerstate.PlayerStateType;

/**
 *
 * @author tjen
 */
public class PlayerEngageState extends PlayerGeneralState
{

    protected PlayerEngageState()
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
        return PlayerStateType.ENGAGE;
    }
}
