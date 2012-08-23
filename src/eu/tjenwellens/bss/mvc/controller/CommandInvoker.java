/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.mvc.controller;

import java.util.ArrayList;
import eu.tjenwellens.bss.commands.command.Command;
import eu.tjenwellens.bss.debug.Output;

/**
 *
 * @author tjen
 */
public class CommandInvoker implements CommandInvokerInterface
{

    private ArrayList<Command> commands = new ArrayList<Command>();

    @Override
    public void executeCommands()
    {
        ArrayList<Command> currentCommands;
        synchronized (this)
        {   // make shure no commands get added while cloning
            currentCommands = (ArrayList<Command>) this.commands.clone();
            this.commands.clear();
        }
        for (Command command : currentCommands)
        {   // is fifo
            Output.add("CommandInvoker.executeCommands: ",command);
            command.execute();
        }
    }

    @Override
    public void addCommand(Command command)
    {
        commands.add(command);
    }
}
