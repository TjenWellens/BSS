package eu.tjenwellens.bss.client.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Tjen
 */
public class BssFrame extends JFrame
{
    public BssFrame()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new BssPanel("127.0.0.1"), BorderLayout.CENTER);
        pack();
    }

    public static void main(String[] args)
    {
        new BssFrame().setVisible(true);
    }
}
