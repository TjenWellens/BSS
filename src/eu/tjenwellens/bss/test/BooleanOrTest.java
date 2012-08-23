/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
