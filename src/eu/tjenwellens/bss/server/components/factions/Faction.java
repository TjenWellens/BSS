package eu.tjenwellens.bss.server.components.factions;

import java.awt.Color;

/**
 *
 * @author tjen
 */
public class Faction
{
    private int factionId;
    private String factionName;
//    private HashSet<FactionPlayer> factionMembers = new HashSet<>();
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
        return equals(factionPlayer.getFaction());
    }

//    public boolean addMember(FactionPlayer factionPlayer)
//    {
//        System.out.println(this + "id: " + factionId + factionName + kleur);
//        if (factionMembers.add(factionPlayer))
//        {
//            System.out.println("success");
//            factionPlayer.setFaction(this);
//            return true;
//        }
//        return false;
//    }
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            System.out.println("WATCH OUT! Faction calling equals on null");
            return false;
        }
        if (!(obj instanceof Faction))
        {
            return false;
        }
        Faction f = (Faction) obj;
        if (factionId == f.factionId)
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + this.factionId;
        return hash;
    }
}
