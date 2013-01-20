package eu.tjenwellens.bss.server.mvc.controller;

import eu.tjenwellens.bss.server.server_commands.ServerCommand;

/**
 *
 * @author tjen
 */
public interface CommandInvokerInterface
{
    public void executeCommands();

    public void addCommand(ServerCommand command);
}
