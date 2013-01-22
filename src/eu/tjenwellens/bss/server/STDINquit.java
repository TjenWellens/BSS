package eu.tjenwellens.bss.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Tjen
 */
public class STDINquit implements Runnable
{
    public void run()
    {
        try
        {
            String input = ""; // Line read from standard in

            System.out.println("Type 'quit' to exit: ");
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);


            while (!(input.equalsIgnoreCase("quit")))
            {
                input = in.readLine();

                if (!(input.equals("quit")))
                {
                    System.out.print("You typed: " + input);
                    System.out.println(", Type 'quit' to exit: ");
                } else
                {
                    System.exit(0);
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
