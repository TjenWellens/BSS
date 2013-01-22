package eu.tjenwellens.bss.client.gui;

import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;

/**
 *
 * @author Tjen
 */
public class BssApplet extends JApplet
{
    BssPanel bss;
    private static final String DEFAULT_HOST = "127.0.0.1";

    @Override
    public void init()
    {
        this.getContentPane().setLayout(new BorderLayout());
        String host = this.getCodeBase().getHost();
        if (host == null || host.isEmpty() || host.length() < 4)
        {
            host = DEFAULT_HOST;
            this.add(new JLabel("using default host: " + host), BorderLayout.SOUTH);
        }
        this.getContentPane().add(bss = new BssPanel(host), BorderLayout.CENTER);
    }

    @Override
    public void stop()
    {
        super.stop();
        bss.stop();
    }

    @Override
    public void destroy()
    {
        super.destroy();
        bss.stop();
    }
}
