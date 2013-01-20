/*
 * MapPanel.java
 *
 * Created on Jan 18, 2012, 2:25:23 PM
 */
package eu.tjenwellens.bss.client.mvc.concretes;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.client.components.ClientFaction;
import eu.tjenwellens.bss.client.components.ClientGamer;
import eu.tjenwellens.bss.client.components.ClientMap;
import eu.tjenwellens.bss.client.components.ClientPlayer;
import eu.tjenwellens.bss.client.components.ClientTile;
import eu.tjenwellens.bss.client.mvc.Data;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author tjen
 */
public class MapPanel extends javax.swing.JPanel
{
    private List<ClientFaction> factions;
    private ClientMap map;
    private List<ClientPlayer> players;
    private ClientGamer gamer;

    /**
     * Creates new form MapPanel
     */
    public MapPanel()
    {
        initComponents();
    }

    private Color getColor(int factionId)
    {
        String colorName = null;
        for (ClientFaction clientFaction : factions)
        {
            if (clientFaction.id == factionId)
            {
                colorName = clientFaction.name;
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
//        Graphics g=null;
//        super.paintComponent(g);
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
            g.setColor(getColor(gamer.factionId));
            g.fillOval(x, y, pSize, pSize);
            g.setColor(Color.black);
            g.drawOval(x, y, pSize, pSize);
        } else
        {
//            System.out.println("gamer is null");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void update(Data data)
    {
//        System.out.println("gamer:" + Boolean.toString(data.getGamer() != null)+ " - players:" + Boolean.toString(data.getPlayers() != null)+ " - map:" + Boolean.toString(data.getMap() != null)+ " - factions" + Boolean.toString(data.getFactions() != null));
        this.gamer = data.getGamer();
        this.players = data.getPlayers();
        this.map = data.getMap();
        this.factions = data.getFactions();
    }
}
