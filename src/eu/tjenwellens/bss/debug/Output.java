/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.debug;

/**
 *
 * @author tjen
 */
public class Output
{
    public static void add(String place, String string)
    {
        System.out.print(place);
        System.out.println(" -> "+string);
    }
    public static void add(String place, Object object)
    {
        add(place, object.toString());
    }
}
