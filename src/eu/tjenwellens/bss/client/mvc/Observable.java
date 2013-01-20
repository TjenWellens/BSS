package eu.tjenwellens.bss.client.mvc;

/**
 *
 * @author tjen
 */
public interface Observable
{
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);
}
