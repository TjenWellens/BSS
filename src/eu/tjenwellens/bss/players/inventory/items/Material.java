/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.players.inventory.items;

/**
 *
 * @author tjen
 */
public enum Material
{
// laatste waarde groter dan 2*de grootste

    HOUT(0), STEEN(1), IJZER(2), DIAMAND(3);
    private int id;

    private Material(int id)
    {
        this.id = id;
    }

    public static void main(String[] args)
    {
        System.out.println("" + HOUT.compareTo(STEEN));
    }

    public int compare(Material material)
    {
        return this.compareTo(material);
    }

    public static Material getDefaultMaterial()
    {
        return Material.IJZER;
    }

    public boolean isDisposableMaterial()
    {
        return this != IJZER && this != DIAMAND;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case HOUT:
                return "hout";
            case STEEN:
                return "steen";
            case IJZER:
                return "ijzer";
            case DIAMAND:
                return "diamand";
            default:
                return super.toString();
        }
    }

    public boolean isDefaultMaterial()
    {
        if (this == getDefaultMaterial())
        {
            return true;
        } else
        {
            return false;
        }
    }
}
