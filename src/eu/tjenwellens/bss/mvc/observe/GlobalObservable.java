package eu.tjenwellens.bss.mvc.observe;

/**
 *
 * @author tjen
 */
public interface GlobalObservable
{
    void registerGlobalObserver(GlobalObserver o);

    void removeGlobalObserver(GlobalObserver o);
}
