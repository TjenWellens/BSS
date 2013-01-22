package eu.tjenwellens.update;

/**
 *
 * @author tjen
 */
public interface Updater
{
    boolean registerUpdatable(Updatable u);

    boolean removeUpdatable(Updatable u);
}
