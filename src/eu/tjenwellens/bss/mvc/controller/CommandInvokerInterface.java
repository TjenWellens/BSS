/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
