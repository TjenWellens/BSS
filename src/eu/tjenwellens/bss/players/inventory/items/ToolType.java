package eu.tjenwellens.bss.players.inventory.items;

import eu.tjenwellens.bss.GameConstants;

/**
 *
 * @author tjen
 */
public enum ToolType
{
    PENSEEL(GameConstants.MATERIALS * 0), TRUWEEL(GameConstants.MATERIALS * 1), HAMER(GameConstants.MATERIALS * 2);
    private int id;

    private ToolType(int id)
    {
        this.id = id;
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
            case PENSEEL:
                return "penseel";
            case TRUWEEL:
                return "truweel";
            case HAMER:
                return "hamer";
            default:
                return super.toString();
        }
    }
}
