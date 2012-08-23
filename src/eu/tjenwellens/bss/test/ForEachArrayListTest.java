/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.test;

import java.util.ArrayList;

/**
 *
 * @author tjen
 */
public class ForEachArrayListTest
{

    public static void main(String[] args)
    {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        for (Integer integer : ints)
        {
            System.out.println(""+integer);
        }
    }
}
