/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.factions;

import java.awt.Color;
import java.util.HashSet;

/**
 *
 * @author tjen
 */
public class Faction
{

    private int factionId;
    private String factionName;
    private HashSet<FactionPlayer> factionMembers = new HashSet<FactionPlayer>();
    private Kleur kleur;

    public Faction(int factionId, String factionName, Kleur color)
    {
        this.factionId = factionId;
        this.factionName = factionName;
        this.kleur = color;
    }

    @Override
    public String toString()
    {
        return "Faction{" + "factionId=" + factionId + ", factionName=" + factionName + ", kleur=" + kleur + '}';
    }

    public boolean containsPlayer(FactionPlayer factionPlayer)
    {
        return factionMembers.contains(factionPlayer);
    }

    public boolean addMember(FactionPlayer factionPlayer)
    {
        System.out.println(this + "id: " + factionId + factionName + kleur);
        if (factionMembers.add(factionPlayer))
        {
            System.out.println("success");
            factionPlayer.setFaction(this);
            return true;
        }
        return false;
    }

    public int getFactionId()
    {
        return factionId;
    }

    public String getFactionName()
    {
        return factionName;
    }

    public Kleur getKleur()
    {
        return kleur;
    }

    public Color getColor()
    {// TODO: remove function
        return kleur.getColor();
    }
}
