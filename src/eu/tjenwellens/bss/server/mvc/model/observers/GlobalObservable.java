package eu.tjenwellens.bss.server.mvc.model.observers;

/**
 *
 * @author tjen
 */
public interface GlobalObservable
{
    void registerGlobalObserver(GlobalObserver o);

    void removeGlobalObserver(GlobalObserver o);
}
