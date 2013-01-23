package eu.tjenwellens.bss.client.gui;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.client.action.Decoration;
import eu.tjenwellens.bss.client.communication.ClientMessager;
import eu.tjenwellens.bss.client.components.ClientFaction;
import eu.tjenwellens.bss.client.components.ClientGamer;
import eu.tjenwellens.bss.client.components.ClientMap;
import eu.tjenwellens.bss.client.components.ClientPlayer;
import eu.tjenwellens.bss.client.components.ClientTile;
import eu.tjenwellens.bss.client.components.items.Material;
import eu.tjenwellens.bss.client.components.items.ToolFactory;
import eu.tjenwellens.bss.client.components.items.ToolType;
import eu.tjenwellens.bss.client.gui.images.ColorChanger;
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.bss.client.mvc.Data;
import eu.tjenwellens.update.Updatable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Tjen
 */
public class MapPanel extends JPanel implements Updatable
{
    //
    private ClientModel model;
    private ClientMessager communication;
    private BssPanel parent;
    //
    private List<ClientFaction> factions;
    private ClientMap map;
    private List<ClientPlayer> players;
    private ClientGamer gamer;
    //
    private BufferedImage imgEmpty = null;
    private BufferedImage imgPlayer = null;
    private BufferedImage imgWall = null;
    private String error = "";
//    private URL baseUrl;

    public MapPanel(ClientModel model, ClientMessager messenger, BssPanel parent)
    {
//        this.baseUrl = imageURL;
        this.model = model;
        this.communication = messenger;
        this.parent = parent;
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                clicked(e);
            }
        });
        loadImages();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        paintMap(g);
        paintPlayers(g);
        paintGamer(g);
        if (error != null)
        {
            g.setColor(Color.BLACK);
            g.drawString(error, 100, 100);
        }
    }

    private void loadImages()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                URL url;
                Class<?> c = MapPanel.this.getClass();
                try
                {
                    url = c.getResource("images/player.png");
                    if (url != null)
                    {
                        imgPlayer = ImageIO.read(new File(url.getPath()));
                    } else
                    {
                        error += ("Error PLAYER url not found");
                    }
                } catch (IOException e)
                {
                    error += (e);
                }
                try
                {
                    url = c.getResource("images/empty.png");
                    if (url != null)
                    {
                        imgEmpty = ImageIO.read(new File(url.getPath()));
                    } else
                    {
                        error += ("Error EMTY url not found");
                    }
                } catch (IOException e)
                {
                    error += (e);
                }
                try
                {
                    url = c.getResource("images/wall.png");
                    if (url != null)
                    {
                        imgWall = ImageIO.read(new File(url.getPath()));
                    } else
                    {
                        error += ("Error WALL url not found");
                    }
                } catch (IOException e)
                {
                    error += (e);
                }
                if (imgPlayer == null)
                {
                    error += ("Error player is null");
                }
                if (imgEmpty == null)
                {
                    error += ("Error empty is null");
                }
                if (imgWall == null)
                {
                    error += ("Error wall is null");
                }
            }
        }).start();
    }

    private void clicked(java.awt.event.MouseEvent evt)
    {
        // 
        int mapw = GameConstants.MAP_WIDTH;
        int maph = GameConstants.MAP_HEIGHT;
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        double xFactor = (double) mapw / (double) panelWidth;
        double yFactor = (double) maph / (double) panelHeight;
        int x = (int) ((evt.getX()) * xFactor);
        int y = (int) ((evt.getY()) * yFactor);
        Point p = new Point(x, y);
        if (evt.getButton() == MouseEvent.BUTTON1)
        {// Move ya ass!
//            System.out.println("Move ya ass!");
            communication.walk(x, y);
        } else if (evt.getButton() == MouseEvent.BUTTON3)
        {// CHAARGEEEE!!!!
            System.out.println("CHAARGEEEE!!!!");
            ClientGamer me = gamer;
            ClientPlayer opponent = null;
            for (ClientPlayer getPlayer : players)
            {
                if (opponent == null)
                {
                    int xpos = getPlayer.xPosition;
                    int ypos = getPlayer.yPosition;
                    if (new Point(xpos, ypos).distance(p) <= 20)
                    {
                        opponent = getPlayer;
                    }
                }
                if (me != null && opponent != null)
                {
                    Point myPos = new Point(me.getXPosition(), me.getYPosition());
                    Point opPos = new Point(opponent.xPosition, opponent.yPosition);

                    if (myPos.distance(opPos) <= GameConstants.ATTACK_RANGE)
                    {
                        communication.engage(opponent.getPlayerName());
                    }

                    break;
                }
            }
        } else if (evt.getButton() == MouseEvent.BUTTON2)
        {// graffity
            System.out.println("graffity");
            if (evt.isShiftDown())
            {
                System.out.println("Build");
                communication.decorate(Decoration.BUILD, x, y, ToolFactory.createTool(ToolType.TRUWEEL, Material.HOUT));
            } else if (evt.isControlDown())
            {
                System.out.println("Destroy");
                communication.decorate(Decoration.DESTROY, x, y, ToolFactory.createTool(ToolType.HAMER, Material.HOUT));
            } else
            {
                System.out.println("Paint");
                communication.decorate(Decoration.PAINT, x, y, ToolFactory.createTool(ToolType.PENSEEL, Material.HOUT));
            }
        }
    }

    private Color getColor(int factionId)
    {
        String colorName = null;
        if (factions != null)
        {
            for (ClientFaction clientFaction : factions)
            {
                if (clientFaction.id == factionId)
                {
                    colorName = clientFaction.name;
                }
            }
        }
        if ("rood".equalsIgnoreCase(colorName))
        {
            return Color.red;
        } else if ("blauw".equalsIgnoreCase(colorName))
        {
            return (Color.blue);
        } else if ("geel".equalsIgnoreCase(colorName))
        {
            return (Color.yellow);
        } else
        {
            return (Color.gray);
        }
    }

    private void paintMap(Graphics g)
    {
        if (map != null)
        {
            Color lineColor = Color.RED;
            boolean raster = false;
            int maprows = map.getRows();
            int mapcols = map.getCols();
            int tilew = (this.getWidth() / mapcols);
            int tileh = (this.getHeight() / maprows);
            for (int row = 0; row < maprows; row++)
            {
                if (raster)
                {
                    // raster _-_-
                    g.setColor(lineColor);
                    g.drawLine(0, row * tileh, maprows * tilew, row * tileh);
                }
                for (int col = 0; col < mapcols; col++)
                {
                    if (raster)
                    {
                        // raster | | |
                        g.setColor(lineColor);
                        g.drawLine(col * tilew, 0, col * tilew, mapcols * tileh);
                    }
                    // tile
                    ClientTile tile = map.getTile(row, col);
                    if (tile != null)
                    {
                        g.setColor(getColor(tile.factionId));
                        g.fillRect(col * tilew, row * tileh, tilew, tileh);

//                        // wall
//                        if (tile.isWalled)
//                        {
//                            g.setColor(Color.black);
//                            g.drawLine(col * tilew, row * tileh, col * tilew + tilew, row * tileh + tileh);
//                            g.drawLine(col * tilew + tilew, row * tileh, col * tilew, row * tileh + tileh);
//                        }

                        // images
                        if (tile.isWalled)
                        {
                            if (imgWall != null)
                            {
                                g.drawImage(imgWall, col * tilew, row * tileh, tilew, tileh, null);
                            } else
                            {
                                g.setColor(Color.black);
                                g.drawLine(col * tilew, row * tileh, col * tilew + tilew, row * tileh + tileh);
                                g.drawLine(col * tilew + tilew, row * tileh, col * tilew, row * tileh + tileh);
                            }
                        } else
                        {
                            if (imgEmpty != null)
                            {
                                g.drawImage(imgEmpty, col * tilew, row * tileh, tilew, tileh, null);
                            }
                        }
                    } else
                    {
                        System.out.println("ERROR: tile is null");
                    }
                }
            }
            if (raster)
            {
                g.setColor(lineColor);
                for (int x = 0; x < maprows; x++)
                {
                    // raster _-_-
                    g.drawLine(x * tilew, 0, x * tilew, mapcols * tileh);
                    for (int y = 0; y < mapcols; y++)
                    {
                        // raster | | |
                        g.drawLine(0, y * tileh, maprows * tilew, y * tileh);
                    }
                }
            }
        } else
        {
//            System.out.println("map is null");
        }
    }

    private void paintPlayers(Graphics g)
    {
        if (players != null)
        {
            for (ClientPlayer getPlayer : players)
            {
                int xPosition = getPlayer.xPosition;
                int yPosition = getPlayer.yPosition;
                int mapw = GameConstants.MAP_WIDTH;
                int maph = GameConstants.MAP_HEIGHT;
                int panelWidth = this.getWidth();
                int panelHeight = this.getHeight();
                double xFactor = (double) panelWidth / (double) mapw;
                double yFactor = (double) panelHeight / (double) maph;
                int pSize = (int) ((double) GameConstants.PLAYER_RADIUS * 2 * (double) xFactor);
                int x = (int) ((xPosition - GameConstants.PLAYER_RADIUS) * xFactor);
                int y = (int) ((yPosition - GameConstants.PLAYER_RADIUS) * yFactor);
                g.setColor(getColor(getPlayer.factionId));
                g.fillOval(x, y, pSize, pSize);
            }
        } else
        {
//            System.out.println("players are null");
        }
    }

    private void paintGamer(Graphics g)
    {
        if (gamer != null)
        {
            int xPosition = gamer.getXPosition();
            int yPosition = gamer.getYPosition();
            int mapw = GameConstants.MAP_WIDTH;
            int maph = GameConstants.MAP_HEIGHT;
            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            double xFactor = (double) panelWidth / (double) mapw;
            double yFactor = (double) panelHeight / (double) maph;
            int pWidth = (int) ((double) GameConstants.PLAYER_RADIUS * 2 * (double) xFactor);
            int pHeight = (int) ((double) GameConstants.PLAYER_RADIUS * 2 * (double) yFactor);
            int x = (int) ((xPosition - GameConstants.PLAYER_RADIUS) * xFactor);
            int y = (int) ((yPosition - GameConstants.PLAYER_RADIUS) * yFactor);
            if (imgPlayer != null)
            {
                if (!playerColorChanged)
                {
                    imgPlayer = ColorChanger.changeColor(imgPlayer, Color.white, getColor(gamer.factionId));
                }
                g.drawImage(imgPlayer, x, y, pWidth, pHeight, null);
            } else
            {
                g.setColor(Color.black);
                g.fillOval(x, y, pWidth, pHeight);
                g.setColor(getColor(gamer.factionId));
                g.fillOval(x + 1, y + 1, pWidth - 2, pHeight - 2);
            }
        } else
        {
//            System.out.println("gamer is null");
        }
    }
    boolean playerColorChanged = false;

    private static ColorModel createColorModel(Color c)
    {

// Create a simple color model with all values mapping to
// a single shade of gray
// nb. this could be improved by reusing the byte arrays

        byte[] newR = new byte[256];
        byte[] newG = new byte[256];
        byte[] newB = new byte[256];

//        for (int i = 0; i < r.length; i++)
//        {
//            r[i] = (byte) n;
//            g[i] = (byte) n;
//            b[i] = (byte) n;
//        }
        newR[255] = (byte) c.getRed();
        newR[255] = (byte) c.getGreen();
        newR[255] = (byte) c.getBlue();

        return new IndexColorModel(4, 256, newR, newG, newB);
    }

    private void update(Data data)
    {
        if (data != null)
        {
            this.gamer = data.getGamer();
            this.players = data.getPlayers();
            this.map = data.getMap();
            this.factions = data.getFactions();
        }
    }

    @Override
    public void update()
    {
        update(model);
        repaint();
    }
}
