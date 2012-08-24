package eu.tjenwellens.bss.mvc.observe;

/**
 *
 * @author tjen
 */
public interface FactionObservable
{
    void registerFactionObserver(FactionObserver o);

    void removeFactionObserver(FactionObserver o);
}
