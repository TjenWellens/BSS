package eu.tjenwellens.bss.server.actions.attackAction;

import eu.tjenwellens.bss.server.components.items.Weapon;

/**
 *
 * @author tjen
 */
public class AttackResult
{
    private boolean draw;
    private AttackPlayer winner;
    private AttackPlayer loser;
    private Weapon winningWeapon;
    private Weapon losingWeapon;

    public AttackResult(boolean draw, AttackPlayer winner, AttackPlayer loser, Weapon winningWeapon, Weapon losingWeapon)
    {
        this.draw = draw;
        this.winner = winner;
        this.loser = loser;
        this.winningWeapon = winningWeapon;
        this.losingWeapon = losingWeapon;
    }

    @Override
    public String toString()
    {
        return "AttackResult{" + "draw=" + draw + ", winner=" + winner.getPlayerName() + ", loser=" + loser.getPlayerName() + ", winningWeapon=" + winningWeapon + ", losingWeapon=" + losingWeapon + '}';
    }

    public boolean isDraw()
    {
        return draw;
    }

    public AttackPlayer getLoser()
    {
        return loser;
    }

    public AttackPlayer getWinner()
    {
        return winner;
    }

    public Weapon getLosingWeapon()
    {
        return losingWeapon;
    }

    public Weapon getWinningWeapon()
    {
        return winningWeapon;
    }
}
