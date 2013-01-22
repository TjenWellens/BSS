package eu.tjenwellens.update;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjen
 */
public class ConcreteUpdater extends Thread implements Updater
{
    private List<Updatable> observers = new ArrayList<>();
    private volatile boolean running = false;
    private int ticsPerSecond = 1;

    public ConcreteUpdater(int ticsPerSecond)
    {
        this.initTicsPerSecond(ticsPerSecond);
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
    public boolean registerUpdatable(Updatable u)
    {
        return this.getObservers().add(u);
    }

    @Override
    public boolean removeUpdatable(Updatable u)
    {
        return this.getObservers().remove(u);
    }

    private void initTicsPerSecond(int tps)
    {
        this.ticsPerSecond = tps;
    }

    public void setTicsPerSecond(int ticsPerSecond)
    {
        this.initTicsPerSecond(ticsPerSecond);
    }

    synchronized private List<Updatable> getObservers()
    {
        return observers;
    }

    private List<Updatable> getObserversCopy()
    {
        return new ArrayList<>(getObservers());
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run()
    {
        long beforeTime, timeDiff, sleepTime;
        beforeTime = System.currentTimeMillis();

        running = true;
//        System.out.println("Run concrete updater");
        while (running)
        {
            for (Updatable observer : this.getObserversCopy())
            {
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
            } catch (InterruptedException e)
            {
                System.out.println(e);
            }
            beforeTime = System.currentTimeMillis();
        }
        System.exit(0);
    }
}
