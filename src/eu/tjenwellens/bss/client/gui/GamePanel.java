package eu.tjenwellens.bss.client.gui;

import eu.tjenwellens.bss.client.communication.ClientMessager;
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.update.Updatable;
import java.awt.BorderLayout;

/**
 *
 * @author Tjen
 */
public class GamePanel extends javax.swing.JPanel implements Updatable
{
    private MapPanel map;
//    private ClientModel model;
//    private ClientMessager messenger;
//    private BssPanel parent;

    public GamePanel(ClientModel model, ClientMessager messenger, BssPanel parent)
    {
        map = new MapPanel(model, messenger, parent);
        setLayout(new BorderLayout());
        this.add(map, BorderLayout.CENTER);
//        this.model = model;
//        this.messenger = messenger;
//        this.parent = parent;
//        initComponents();
    }

    @Override
    public void update()
    {
        map.update();
    }
}
