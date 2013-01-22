package eu.tjenwellens.bss.server.mvc.controller;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.server.server_commands.ServerCommand;
import eu.tjenwellens.update.ConcreteUpdater;
import eu.tjenwellens.update.Updatable;
import eu.tjenwellens.update.Updater;

/**
 *
 * @author tjen
 */
public class Controller implements Updater, Updatable, CommandInvoker
{
    private ConcreteUpdater updater;
    private CommandInvoker ci = new ConcreteCommandInvoker();

    public Controller()
    {
        this.updater = new ConcreteUpdater(GameConstants.TICKS_PER_SECOND);
        this.ci = new ConcreteCommandInvoker();
    }

    public Controller(ConcreteUpdater updater)
    {
        this.updater = updater;
        this.ci = new ConcreteCommandInvoker();
    }

    public void start()
    {
        if (updater.isRunning() != true)
        {
            updater.registerUpdatable(this);
            updater.start();
        } else
        {
            System.out.println("Controller.start: Allready running");
        }
    }

    public void end()
    {
        if (updater.isRunning() == true)
        {
            updater.removeUpdatable(this);
            updater.end();
        } else
        {
            System.out.println("Controller.stop: Not active");
        }
    }

    @Override
    public void update()
    {
        ci.executeCommands();
    }

    @Override
    public void executeCommands()
    {
        ci.executeCommands();
    }

    @Override
    public boolean addCommand(ServerCommand command)
    {
        return ci.addCommand(command);
    }

    @Override
    public boolean registerUpdatable(Updatable u)
    {
        return updater.registerUpdatable(u);
    }

    @Override
    public boolean removeUpdatable(Updatable u)
    {
        return updater.removeUpdatable(u);
    }

    public boolean isRunning()
    {
        return updater.isRunning();
    }
}
