package eu.tjenwellens.bss.server._debug;

/**
 *
 * @author tjen
 */
public class Output
{
    public static void add(String place, String string)
    {
        System.out.print(place);
        System.out.println(" -> " + string);
    }

    public static void add(String place, Object object)
    {
        add(place, object.toString());
    }
}
