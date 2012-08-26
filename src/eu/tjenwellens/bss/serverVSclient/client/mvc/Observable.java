package eu.tjenwellens.bss.serverVSclient.client.mvc;

/**
 *
 * @author tjen
 */
public interface Observable
{
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);
}
