/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.factions;

import eu.tjenwellens.bss.actionhandlers.ActionPlayer;

/**
 *
 * @author tjen
 */
public interface FactionPlayer extends ActionPlayer
{
    Faction getFaction();
    void setFaction(Faction faction);
}
