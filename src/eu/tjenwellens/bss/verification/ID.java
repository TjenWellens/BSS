/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
