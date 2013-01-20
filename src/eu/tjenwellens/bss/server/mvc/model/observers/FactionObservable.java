package eu.tjenwellens.bss.server.mvc.model.observers;

/**
 *
 * @author tjen
 */
public interface FactionObservable
{
    void registerFactionObserver(FactionObserver o);

    void removeFactionObserver(FactionObserver o);
}
