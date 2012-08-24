package eu.tjenwellens.bss.mvc.observe;

/**
 *
 * @author tjen
 */
public interface MapObservable
{
    void registerMapObserver(MapObserver o);

    void removeMapObserver(MapObserver o);
}
