package eu.tjenwellens.bss.verification;

/**
 *
 * @author tjen
 */
public class ID
{
    String name;
    String password;

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof ID))
        {
            return false;
        }
        ID id = (ID) obj;
        if (!name.equalsIgnoreCase(id.name))
        {
            return false;
        }
        if (!password.equals(id.password))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 11 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
