package eu.tjenwellens.bss.server.components.players.playerstate.playerstates;

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
