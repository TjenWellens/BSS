package eu.tjenwellens.bss.server.mvc.model.observers;

/**
 *
 * @author tjen
 */
public interface PlayerObservable
{
    void registerPlayersObserver(PlayerObserver o);

    void removePlayersObserver(PlayerObserver o);
}
