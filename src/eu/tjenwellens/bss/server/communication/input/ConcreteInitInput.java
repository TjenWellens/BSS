package eu.tjenwellens.bss.server.communication.input;

import eu.tjenwellens.bss.server.communication.NewAccountHandler;
import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiver;
import eu.tjenwellens.bss.server.server_commands.ServerCommandLogin;
import eu.tjenwellens.bss.server.server_commands.ServerCommandPlay;
import eu.tjenwellens.bss.server.server_commands.ServerCommandSelectFaction;
import eu.tjenwellens.bss.server.server_commands.ServerCommandSelectPosition;

/**
 *
 * @author Tjen
 */
public class ConcreteInitInput implements InitInput
{
    private CommandInvoker ci;
    private CommandReceiver cr;
    private NewAccountHandler accountHandler = new NewAccountHandler();

    public ConcreteInitInput(CommandInvoker ci, CommandReceiver cr)
    {
        this.ci = ci;
        this.cr = cr;
    }
    
    @Override
    public InitPlayer login(String name, String password)
    {
        InitPlayer ip = accountHandler.login(name, password);
        if (ip == null || ip.getId() == 0)
        {
            return null;
        }
        ci.addCommand(new ServerCommandLogin(cr, ip.getId(), ip));
        return ip;
    }

    @Override
    public void selectFaction(int id, String factionName)
    {
        ci.addCommand(new ServerCommandSelectFaction(cr, id, factionName));
    }

    @Override
    public void selectPosition(int id, int x, int y)
    {
        ci.addCommand(new ServerCommandSelectPosition(cr, id, x, y));
    }

    @Override
    public void play(int id)
    {
        ci.addCommand(new ServerCommandPlay(cr, id));
    }
}
