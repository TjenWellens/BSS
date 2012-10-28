package eu.tjenwellens.bss.serverVSclient;

import eu.tjenwellens.bss.mvc.controller.Controller;
import eu.tjenwellens.bss.mvc.controller.Ticker;
import eu.tjenwellens.bss.serverVSclient.client.ClientMessager;
import eu.tjenwellens.bss.serverVSclient.client.mvc.ClientModel;
import eu.tjenwellens.bss.serverVSclient.client.mvc.concretes.ConcreteFrameWithMap;

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
        // TODO code application logic here
        runClient();
    }

    public static void runClient()
    {
        Ticker t = new Ticker(1);
        ConcreteUpdater updater = new ConcreteUpdater(2);
        ClientModel model = new ClientModel();
        ClientMessager communication = new ClientMessager(model, "127.0.0.1");
        updater.addUpdatable(communication);
        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(communication, model);
        model.registerObserver(cfwm);
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
        updater.addUpdatable(communication);
        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(communication, model);
        model.registerObserver(cfwm);
        t.registerTickObserver(cfwm);
        cfwm.setVisible(true);

        t.start();
        updater.start();
    }
}
