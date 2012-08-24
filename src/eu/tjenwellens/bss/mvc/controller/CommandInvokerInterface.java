package eu.tjenwellens.bss.mvc.controller;

import eu.tjenwellens.bss.commands.command.Command;

/**
 *
 * @author tjen
 */
public interface CommandInvokerInterface
{
    public void executeCommands();

    public void addCommand(Command command);
}
