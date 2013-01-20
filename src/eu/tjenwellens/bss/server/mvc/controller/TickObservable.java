package eu.tjenwellens.bss.server.mvc.controller;

import eu.tjenwellens.bss.server.mvc.TickObserver;

/**
 *
 * @author tjen
 */
interface TickObservable
{
    public void registerTickObserver(TickObserver o);

    public void removeTickObserver(TickObserver o);
}
