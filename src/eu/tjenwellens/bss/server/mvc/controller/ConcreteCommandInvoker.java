package eu.tjenwellens.bss.server.mvc.controller;

import eu.tjenwellens.bss.server._debug.Output;
import eu.tjenwellens.bss.server.server_commands.ServerCommand;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author tjen
 */
public class ConcreteCommandInvoker implements CommandInvoker
{
    private Collection<ServerCommand> commands = new HashSet<>();

    @Override
    public void executeCommands()
    {
        LinkedList<ServerCommand> currentCommands;
        synchronized (this)
        {   // make shure no commands get added while cloning
            currentCommands = new LinkedList<>(commands);
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
    public boolean addCommand(ServerCommand command)
    {
        return commands.add(command);
    }
}
