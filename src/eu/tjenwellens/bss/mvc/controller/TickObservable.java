package eu.tjenwellens.bss.mvc.controller;

import eu.tjenwellens.bss.mvc.TickObserver;

/**
 *
 * @author tjen
 */
interface TickObservable
{
    public void registerTickObserver(TickObserver o);

    public void removeTickObserver(TickObserver o);
}
