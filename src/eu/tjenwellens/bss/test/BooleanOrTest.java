package eu.tjenwellens.bss.test;

/**
 *
 * @author tjen
 */
public class BooleanOrTest
{
    public static void main(String[] args)
    {
        boolean te = true;
        te = false || te;
        System.out.println("" + te);
    }
}
