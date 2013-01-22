package eu.tjenwellens.bss.server.mvc.controller;

import eu.tjenwellens.bss.server.server_commands.ServerCommand;

/**
 *
 * @author tjen
 */
public interface CommandInvoker
{
    public void executeCommands();

    public boolean addCommand(ServerCommand command);
}
