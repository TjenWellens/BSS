/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.mvc.observe;

/**
 *
 * @author tjen
 */
public interface PlayerObservable
{

    void registerPlayersObserver(PlayerObserver o);

    void removePlayersObserver(PlayerObserver o);
}
