package eu.tjenwellens.bss.client.gui.images;


/**
 *
 * @author Tjen
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColorChanger
{
    public static final int ALPHA = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;
    public static final int HUE = 0;
    public static final int SATURATION = 1;
    public static final int BRIGHTNESS = 2;
    public static final int TRANSPARENT = 0;

    public static void main(String[] args)
    {
        String filename = "img/player.gif";
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(ColorChanger.class.getResource(filename).getPath()));
            image = new ColorChanger().changeColor(image, Color.white, Color.blue);
            JFrame frame = new JFrame();
            frame.setVisible(true);
            frame.setSize(380, 220);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new JLabel(new ImageIcon(image)));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static BufferedImage changeColor(Image image, Color mask,
            Color replacement)
    {
        BufferedImage destImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = destImage.createGraphics();
        g.drawImage(image,0,0, null);
        g.dispose();

        for (int i = 0; i < destImage.getWidth(); i++)
        {
            for (int j = 0; j < destImage.getHeight(); j++)
            {

                int destRGB = destImage.getRGB(i, j);

                if (matches(mask.getRGB(), destRGB))
                {
                    int rgbnew = getNewPixelRGB(replacement.getRGB(), destRGB);
                    destImage.setRGB(i, j, rgbnew);
                }
            }
        }

        return destImage;
    }

    private static int getNewPixelRGB(int replacement, int destRGB)
    {
        float[] destHSB = getHSBArray(destRGB);
        float[] replHSB = getHSBArray(replacement);

        int rgbnew = Color.HSBtoRGB(replHSB[HUE],
                replHSB[SATURATION], destHSB[BRIGHTNESS]);
        return rgbnew;
    }

    private static boolean matches(int maskRGB, int destRGB)
    {
        float[] hsbMask = getHSBArray(maskRGB);
        float[] hsbDest = getHSBArray(destRGB);

        if (hsbMask[HUE] == hsbDest[HUE]
                && hsbMask[SATURATION] == hsbDest[SATURATION]
                && getRGBArray(destRGB)[ALPHA] != TRANSPARENT)
        {

            return true;
        }
        return false;
    }

    private static int[] getRGBArray(int rgb)
    {
        return new int[]
                {
                    (rgb >> 24) & 0xff, (rgb >> 16) & 0xff,
                    (rgb >> 8) & 0xff, rgb & 0xff
                };
    }

    private static float[] getHSBArray(int rgb)
    {
        int[] rgbArr = getRGBArray(rgb);
        return Color.RGBtoHSB(rgbArr[RED], rgbArr[GREEN], rgbArr[BLUE], null);
    }
}