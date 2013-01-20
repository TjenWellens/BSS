package eu.tjenwellens.bss.client.observer;

/**
 *
 * @author tjen
 */
interface TickObservable
{
    public void registerTickObserver(TickObserver o);

    public void removeTickObserver(TickObserver o);
}
