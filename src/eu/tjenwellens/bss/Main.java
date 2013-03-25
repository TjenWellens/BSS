package eu.tjenwellens.bss;

import eu.tjenwellens.bss.client.ClientMain;
import eu.tjenwellens.bss.server.ServerMain;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tjen
 */
public class Main
{
    private static final Set<String> helpCommands = new HashSet<>();
    private static final String info = "java -jar BSS.jar [server|ip] [port]\n(default: client, ip=127.0.0.1, port=1234)";

    private static void initHelp()
    {
        helpCommands.add("-help");
        helpCommands.add("-h");
        helpCommands.add("?");
        helpCommands.add("/help");
        helpCommands.add("/?");
        helpCommands.add("-info");
        helpCommands.add("help");
    }

    public static void main(String[] args)
    {
        initHelp();
        if (args != null && args.length > 0)
        {
            int port = -1;
            if (args.length > 1)
            {
                try
                {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e)
                {
                    System.err.println(e);
                }
            }
            if (args[0].equalsIgnoreCase("server"))
            {
                ServerMain.runServer(port);
            } else if (args[0].equalsIgnoreCase("client"))
            {
                ClientMain.runClient(args[0], port);
            } else if (helpCommands.contains(args[0]))
            {
                System.out.println(info);
            } else
            {
                ClientMain.runClient(args[0], port);
            }
        } else
        {
            ClientMain.runClient(null, -1);
        }
    }
}
