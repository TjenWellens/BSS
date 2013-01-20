package eu.tjenwellens.bss;

import eu.tjenwellens.bss.client.ClientMain;
import eu.tjenwellens.bss.server.ServerMain;

/**
 *
 * @author Tjen
 */
public class Main {
    public static void main(String[] args)
    {
        ServerMain.runServer();
        ClientMain.runClient();
    }
}
