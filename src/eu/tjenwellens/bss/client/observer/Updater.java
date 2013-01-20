package eu.tjenwellens.bss.client.observer;

/**
 *
 * @author tjen
 */
public interface Updater
{
    void addUpdatable(Updatable updatable);

    void removeUpdatable(Updatable updatable);
}
