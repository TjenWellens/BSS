package eu.tjenwellens.bss.client.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tjen
 */
public class Ticker extends Thread implements TickObservable
{
    private volatile List<TickObserver> observers;
    private volatile boolean running = false;
    private volatile int ticsPerSecond = 1;

    public Ticker(int ticsPerSecond)
    {
        this.initTicsPerSecond(ticsPerSecond);
        this.observers = new ArrayList<>();
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
    public void registerTickObserver(TickObserver o)
    {
        observers.add(o);
    }

    @Override
    public void removeTickObserver(TickObserver o)
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
            for (TickObserver observer : this.observers)
            {
                observer.tick();
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
            } catch (InterruptedException e)
            {
                System.out.println(e);
            }
            beforeTime = System.currentTimeMillis();
        }
        System.exit(0);
    }
}
