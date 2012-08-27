/*
 * MapPanel.java
 *
 * Created on Jan 18, 2012, 2:25:23 PM
 */
package eu.tjenwellens.bss.serverVSclient.client.mvc.concretes;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientFaction;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientMap;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientPlayer;
import eu.tjenwellens.bss.serverVSclient.client.components.ClientTile;
import eu.tjenwellens.bss.serverVSclient.client.mvc.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author tjen
 */
public class MapPanel extends javax.swing.JPanel implements Observer
{
    public List<ClientFaction> factions;
    public ClientMap map;
    public List<ClientPlayer> players;

    /**
     * Creates new form MapPanel
     */
    public MapPanel()
    {
        initComponents();
    }

    private ClientTile getTile(int row, int col)
    {
        ClientTile ret = map.tiles.get(row * map.cols + col);
        if (ret.col == col && ret.row == row)
        {
            return ret;
        }
        System.out.println("ClientTile not at assumed position");
        for (ClientTile clientTile : map.tiles)
        {
            if (clientTile.col == col && clientTile.row == row)
            {
                return clientTile;
            }
        }
        return null;
    }

    private Color getColor(String colorName)
    {
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
        {
            if (map != null)
            {
                Color lineColor = Color.LIGHT_GRAY;
                int maprows = map.rows;
                int mapcols = map.cols;
                int tilew = (this.getWidth() / mapcols);
                int tileh = (this.getHeight() / maprows);
                for (int y = 0; y < maprows; y++)
                {
                    // raster _-_-
                    g.setColor(lineColor);
                    g.drawLine(0, y * tileh, maprows * tilew, y * tileh);
                    for (int x = 0; x < mapcols; x++)
                    {
                        // raster | | |
                        g.setColor(lineColor);
                        g.drawLine(x * tilew, 0, x * tilew, mapcols * tileh);
                        // tile
                        ClientTile tile = getTile(y, x);
                        g.setColor(getColor(tile.factionName));
                        g.fillRect(x * tilew, y * tileh, tilew, tileh);

                        // wall
                        if (tile.isWalled)
                        {
                            g.setColor(Color.black);
                            g.drawLine(x * tilew, y * tileh, x * tilew + tilew, y * tileh + tileh);
                            g.drawLine(x * tilew + tilew, y * tileh, x * tilew, y * tileh + tileh);
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
                    int pSize = (int) ((double) GameConstants.PLAYER_RADIUS * (double) xFactor);
                    int x = (int) ((xPosition + ((double) GameConstants.PLAYER_RADIUS / (double) 2)) * xFactor);
                    int y = (int) ((yPosition + ((double) GameConstants.PLAYER_RADIUS / (double) 2)) * yFactor);
                    g.setColor(getColor(getPlayer.factionName));
                    g.fillOval(x, y, pSize, pSize);
                }
            }
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

    @Override
    public void update()
    {
        //ViewToModel .getUpdates();
    }
}
