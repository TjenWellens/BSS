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
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.bss.client.mvc.Data;
import eu.tjenwellens.update.Updatable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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

    public MapPanel(ClientModel model, ClientMessager messenger, BssPanel parent)
    {
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

    @Override
    public void paintComponent(Graphics g)
    {
        if (map != null)
        {
            Color lineColor = Color.LIGHT_GRAY;
            int maprows = map.getRows();
            int mapcols = map.getCols();
            int tilew = (this.getWidth() / mapcols);
            int tileh = (this.getHeight() / maprows);
            for (int row = 0; row < maprows; row++)
            {
                // raster _-_-
                g.setColor(lineColor);
                g.drawLine(0, row * tileh, maprows * tilew, row * tileh);
                for (int col = 0; col < mapcols; col++)
                {
                    // raster | | |
                    g.setColor(lineColor);
                    g.drawLine(col * tilew, 0, col * tilew, mapcols * tileh);
                    // tile
                    ClientTile tile = map.getTile(row, col);
                    if (tile != null)
                    {
                        g.setColor(getColor(tile.factionId));
                        g.fillRect(col * tilew, row * tileh, tilew, tileh);

                        // wall
                        if (tile.isWalled)
                        {
                            g.setColor(Color.black);
                            g.drawLine(col * tilew, row * tileh, col * tilew + tilew, row * tileh + tileh);
                            g.drawLine(col * tilew + tilew, row * tileh, col * tilew, row * tileh + tileh);
                        }
                    } else
                    {
                        System.out.println("ERROR: tile is null");
                    }
                }
            }
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
        } else
        {
//            System.out.println("map is null");
        }
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
            int pSize = (int) ((double) GameConstants.PLAYER_RADIUS * 2 * (double) xFactor);
            int x = (int) ((xPosition - GameConstants.PLAYER_RADIUS) * xFactor);
            int y = (int) ((yPosition - GameConstants.PLAYER_RADIUS) * yFactor);
            g.setColor(Color.black);
            g.fillOval(x, y, pSize, pSize);
            g.setColor(getColor(gamer.factionId));
            g.fillOval(x+1, y+1, pSize-2, pSize-2);
        } else
        {
//            System.out.println("gamer is null");
        }
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
        paintComponent(getGraphics());
    }
}
