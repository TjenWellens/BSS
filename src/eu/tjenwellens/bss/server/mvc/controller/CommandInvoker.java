package eu.tjenwellens.bss.server.mvc.controller;

import java.util.ArrayList;
import eu.tjenwellens.bss.server.server_commands.ServerCommand;
import eu.tjenwellens.bss.server._debug.Output;

/**
 *
 * @author tjen
 */
public class CommandInvoker implements CommandInvokerInterface
{
    private ArrayList<ServerCommand> commands = new ArrayList<ServerCommand>();

    @Override
    public void executeCommands()
    {
        ArrayList<ServerCommand> currentCommands;
        synchronized (this)
        {   // make shure no commands get added while cloning
            currentCommands = (ArrayList<ServerCommand>) this.commands.clone();
            this.commands.clear();
        }
        for (ServerCommand command : currentCommands)
        {   // is fifo
            Output.add("CommandInvoker.executeCommands: ", command);
//            System.out.println("CommandInvoker.executeCommands: "+ command);
            command.execute();
        }
    }

    @Override
    public void addCommand(ServerCommand command)
    {
        commands.add(command);
    }
}
