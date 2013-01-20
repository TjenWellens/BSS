package eu.tjenwellens.bss.server.mvc.model.observers;

/**
 *
 * @author tjen
 */
public interface MapObservable
{
    void registerMapObserver(MapObserver o);

    void removeMapObserver(MapObserver o);
}
