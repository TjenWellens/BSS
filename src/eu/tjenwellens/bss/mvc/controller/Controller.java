/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.mvc.controller;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.commands.command.Command;
import eu.tjenwellens.bss.mvc.TickObserver;

/**
 *
 * @author tjen
 */
public class Controller implements TickObserver, TickObservable, CommandInvokerInterface
{

    private Ticker updater = new Ticker(GameConstants.TICKS_PER_SECOND);
    private CommandInvokerInterface ci = new CommandInvoker();

    public Controller()
    {
    }

    public void start()
    {
        if (updater.isRunning() != true)
        {
            updater.registerTickObserver(this);
            updater.start();
        } else
        {
            System.out.println("Controller.start: Allready running");
        }
    }

    public void stop()
    {
        if (updater.isRunning() == true)
        {
            updater.removeTickObserver(this);
            updater.end();
        } else
        {
            System.out.println("Controller.stop: Not active");
        }
    }

    @Override
    public void tick()
    {
        ci.executeCommands();
    }

    @Override
    public void registerTickObserver(TickObserver o)
    {
        updater.registerTickObserver(o);
    }

    @Override
    public void removeTickObserver(TickObserver o)
    {
        updater.removeTickObserver(o);
    }

    @Override
    public void executeCommands()
    {
        ci.executeCommands();
    }

    @Override
    public void addCommand(Command command)
    {
        ci.addCommand(command);
    }
}
