package eu.tjenwellens.bss.client.action;

/**
 *
 * @author tjen
 */
public enum Transaction
{
    DEPOSIT, RETRIEVE, SHOP;

    public static Transaction fromName(String name)
    {
        if (DEPOSIT.name().equals(name))
        {
            return DEPOSIT;
        } else if (RETRIEVE.name().equals(name))
        {
            return RETRIEVE;
        } else if (SHOP.name().equals(name))
        {
            return SHOP;
        } else
        {
            return null;
        }
    }
}
