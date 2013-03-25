package eu.tjenwellens.bss.client.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Tjen
 */
public class BssFrame extends JFrame
{
    public BssFrame(String host, int port)
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new BssPanel(host, port), BorderLayout.CENTER);
        pack();
    }

    public static void createClientFrame(String host, int port)
    {
        SwingUtilities.invokeLater(new Creator(host, port));
    }

    private static void createAndShowGUI(String host, int port)
    {
        new BssFrame(host, port).setVisible(true);
    }

    private static class Creator implements Runnable
    {
        private String host;
        private int port;

        public Creator(String host, int port)
        {
            this.host = host;
            this.port = port;
        }

        @Override
        public void run()
        {
            createAndShowGUI(host, port);
        }
    }
}
