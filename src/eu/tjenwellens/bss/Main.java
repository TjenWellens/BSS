package eu.tjenwellens.bss;

import eu.tjenwellens.bss.client.ClientMain;
import eu.tjenwellens.bss.server.ServerMain;

/**
 *
 * @author Tjen
 */
public class Main
{
    public static void main(String[] args)
    {
        if (args != null && args.length > 0 && args[0].equalsIgnoreCase("server"))
        {
            ServerMain.runServer();
        } else
        {
            ClientMain.runClient();
        }
    }
}
