package eu.tjenwellens.bss.client;

import eu.tjenwellens.bss.client.communication.ClientMessager;
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.bss.client.mvc.concretes.ConcreteFrameWithMap;
import eu.tjenwellens.bss.client.observer.Ticker;
import eu.tjenwellens.update.ConcreteUpdater;

/**
 *
 * @author Tjen
 */
public class ClientMain
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        runClient();
    }

    public static void runClient()
    {
        Ticker t = new Ticker(1);
        ConcreteUpdater updater = new ConcreteUpdater(2);
        ClientModel model = new ClientModel();
        ClientMessager communication = new ClientMessager(model, "127.0.0.1");
        updater.registerUpdatable(communication);
        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(communication, model);
        model.registerUpdatable(cfwm);
        t.registerTickObserver(cfwm);
        cfwm.setVisible(true);

        t.start();
        updater.start();
    }

    public static void runClient(String url)
    {
        if (url == null)
        {
            runClient();
            return;
        }
        Ticker t = new Ticker(1);
        ConcreteUpdater updater = new ConcreteUpdater(2);
        ClientModel model = new ClientModel();
        ClientMessager communication = new ClientMessager(model, url);
        updater.registerUpdatable(communication);
        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(communication, model);
        model.registerUpdatable(cfwm);
        t.registerTickObserver(cfwm);
        cfwm.setVisible(true);

        t.start();
        updater.start();
    }
}