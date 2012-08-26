package eu.tjenwellens.bss.serverVSclient;

/**
 *
 * @author tjen
 */
public interface Updater
{
    void addUpdatable(Updatable updatable);

    void removeUpdatable(Updatable updatable);
}
