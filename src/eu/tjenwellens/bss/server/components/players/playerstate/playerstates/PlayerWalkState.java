package eu.tjenwellens.bss.server.components.players.playerstate.playerstates;

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
