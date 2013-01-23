package eu.tjenwellens.bss.data.commands.init.to_server;

import eu.tjenwellens.bss.data.commands.Command;

/**
 *
 * @author Tjen
 */
public interface IOHandler
{
    void manageOutput();

    void manageInput(Command command);
    
    void exit();
}
