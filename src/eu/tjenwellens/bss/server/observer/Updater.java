package eu.tjenwellens.bss.server.observer;

/**
 *
 * @author tjen
 */
public interface Updater
{
    void addUpdatable(Updatable updatable);

    void removeUpdatable(Updatable updatable);
}
