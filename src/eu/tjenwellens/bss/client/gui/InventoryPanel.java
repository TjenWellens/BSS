package eu.tjenwellens.bss.client.gui;

import eu.tjenwellens.bss.client.gui.images.ColorChanger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Tjen
 */
public class InventoryPanel extends JPanel
{
    private int cols = 3;
    private int rows = 4;
    private Image imgPaint = null;
    private Image imgBuild = null;
    private Image imgDestroy = null;

    public InventoryPanel()
    {
        loadImages();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Dimension d = this.getSize();
        int width = d.width / cols;
        int height = d.height / rows;
        for (int col = 0; col < cols; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                paintItem(g, col * width, row * height, width, height, (col + row) % 4);
            }
        }

        // draw raster
        // ||||
        for (int col = 0; col < cols; col++)
        {
            g.drawLine(col * width, 0, col * width, d.height);
        }
        // ----
        for (int row = 0; row < rows; row++)
        {
            g.drawLine(0, row * height, d.width, row * height);
        }
            g.drawLine(0, d.height-1, d.width, d.height-1);
            g.drawLine(d.width-1, 0, d.width-1, d.height);
    }

    private void loadImages()
    {
        imgPaint = loadImage("paint.png");
        imgBuild = loadImage("build.png");
        imgDestroy = loadImage("destroy.png");
    }

    private Image loadImage(String filename)
    {
        return Toolkit.getDefaultToolkit().getImage(ColorChanger.class.getResource(filename));
    }

    private void paintItem(Graphics g, int x, int y, int width, int height, int item)
    {
        Color bcp = g.getColor();
        switch (item)
        {
            case 0:
                g.drawImage(imgPaint, x, y, width, height, this);
                break;
            case 1:
                g.drawImage(imgBuild, x, y, width, height, this);
                break;
            case 2:
                g.drawImage(imgDestroy, x, y, width, height, this);
                break;
            default:
                g.drawLine(x, y, x + width, y + height);
                g.drawLine(x + width, y, x, y + height);
                break;
        }
    }
}
