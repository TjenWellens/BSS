package eu.tjenwellens.bss.serverVSclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tjen
 */
public class ConcreteUpdater extends Thread implements Updater
{
    private volatile List<Updatable> observers;
    private volatile boolean running = false;
    private volatile int ticsPerSecond = 1;

    public ConcreteUpdater(int ticsPerSecond)
    {
        this.initTicsPerSecond(ticsPerSecond);
        this.observers = new ArrayList<Updatable>();
        running = false;
        this.setName("updater");
    }

    public void end()
    {
        running = false;
    }

    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void addUpdatable(Updatable o)
    {
        observers.add(o);
    }

    @Override
    public void removeUpdatable(Updatable o)
    {
        observers.remove(o);
    }

    private void initTicsPerSecond(int tps)
    {
        this.ticsPerSecond = tps;
    }

    public void setTicsPerSecond(int ticsPerSecond)
    {
        this.initTicsPerSecond(ticsPerSecond);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run()
    {
        long beforeTime, timeDiff, sleepTime;
        beforeTime = System.currentTimeMillis();

        running = true;
        while (running)
        {
            for (Iterator<Updatable> it = this.observers.iterator(); it.hasNext();)
            {
                Updatable observer = it.next();
                observer.update();
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleepTime = ((long) (1000 / ticsPerSecond)) - timeDiff;   // time left in this loop

            if (sleepTime <= 0)  // update/render took longer than period
            {
                sleepTime = 5;    // sleep a bit anyway
            }

            try
            {
                Thread.sleep(sleepTime);  // sleep a bit
            } catch (InterruptedException ex)
            {
                System.out.println(ex);
            }
            beforeTime = System.currentTimeMillis();
        }
        System.exit(0);
    }
}
