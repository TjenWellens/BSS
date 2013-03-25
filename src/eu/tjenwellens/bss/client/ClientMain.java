package eu.tjenwellens.bss.client;

import eu.tjenwellens.bss.client.gui.BssFrame;

/**
 *
 * @author Tjen
 */
public class ClientMain
{
    /**
     * @param args the command line arguments
     */

    public static void runClient(String host, int port)
    {
        BssFrame.createClientFrame(host, port);
    }
}
