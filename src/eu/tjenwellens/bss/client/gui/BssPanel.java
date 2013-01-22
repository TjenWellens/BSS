package eu.tjenwellens.bss.client.gui;

import eu.tjenwellens.bss.client.communication.ClientMessager;
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.update.ConcreteUpdater;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Tjen
 */
public class BssPanel extends JPanel
{
    private ClientModel model;
    private ClientMessager messenger;
    private ConcreteUpdater updater;
    // init
    private InitPanel init;
    // game
    private GamePanel game;
    // state

    public BssPanel(String host)
    {
        updater=new ConcreteUpdater(10);
        model = new ClientModel();
        messenger = new ClientMessager(model, host);
        updater.registerUpdatable(messenger);
        updater.start();
        this.setPreferredSize(new Dimension(500, 500));
        // layout
        this.setLayout(new BorderLayout());
        // start init
        init();
    }
    
    public void stop(){
        updater.end();
    }

    private void init()
    {
        init = new InitPanel(model, messenger, this);
        model.registerUpdatable(init);
        this.add(init, BorderLayout.CENTER);
        validate();
    }

    private void removeInit()
    {
        model.removeUpdatable(init);
        this.removeAll();
        init = null;
        invalidate();
    }

    private void game()
    {
        game = new GamePanel(model, messenger, this);
        model.registerUpdatable(game);
        this.add(game, BorderLayout.CENTER);
        validate();
    }

    private void removeGame()
    {
        model.removeUpdatable(game);
        this.removeAll();
        game = null;
    }

    public void initDone()
    {
        if (isInitDone())
        {
            System.out.println("INIT done");
            removeInit();
            game();
        }
    }

    public boolean isInitDone()
    {
        return model.isInitDone();
    }
}
