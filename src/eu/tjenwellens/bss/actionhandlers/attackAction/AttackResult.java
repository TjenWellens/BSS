/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.attackAction;

import eu.tjenwellens.bss.players.inventory.items.Weapon;

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
        return "AttackResult{" + "draw=" + draw + ", winner=" + winner + ", loser=" + loser + ", winningWeapon=" + winningWeapon + ", losingWeapon=" + losingWeapon + '}';
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
