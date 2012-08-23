/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.walkAction.walk;

import java.util.HashMap;
import java.util.Map.Entry;
import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;
import eu.tjenwellens.bss.map.GetTile;
import eu.tjenwellens.bss.map.MapHandlerInterface;
import eu.tjenwellens.bss.players.PlayerHandlerInterface;

/**
 *
 * @author tjen
 */
public class CollisionJumpWalk extends JumpWalk implements GameConstants
{

    protected MapHandlerInterface mapHandler;
    protected PlayerHandlerInterface playerHandler;

    public CollisionJumpWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination, MapHandlerInterface mapHandler, PlayerHandlerInterface playerHandler)
    {
        super(walkHandler, player, destination);
        this.mapHandler = mapHandler;
        this.playerHandler = playerHandler;
    }

    @Override
    protected Position step()
    {// todo solve problem
        Position p = getStepDestination();
        if (p == null || collides(p))
        {
            return null;
        } else
        {
            return p;
        }
    }

    protected boolean collides(Position p)
    {
        return collidesWithWalls(p) || collidesWithPlayers(p);
    }

    protected boolean collidesWithPlayers(Position p)
    {
        HashMap<Integer, WalkObstaclePlayer> players = playerHandler.getWalkObstaclesCopy();
        players.remove(player.getPlayerID());
        for (WalkObstaclePlayer wo : players.values())
        {
            if ((!wo.isGhost()) && collideCheck2Circles(player.getPosition(), PLAYER_RADIUS, wo.getPosition(), PLAYER_RADIUS))
            {
                return true;
            }
        }
        return false;
    }

    protected boolean collidesWithWalls(Position p)
    {
        final int tileWidth = (int) ((double) MAP_WIDTH / (double) MAP_ROWS);
        final int tileHeight = (int) ((double) MAP_HEIGHT / (double) MAP_COLUMNS);
        // overloop alle naburige tiles op walled && collide
        for (Entry<GetTile, Position> entry : getNeabyTiles(p).entrySet())
        {
            if (entry.getKey().isWalled())
            {
                if (collisionCheckCircleVsRectangle(p, PLAYER_RADIUS, entry.getValue(), tileWidth, tileHeight))
                {
                    return true;
                }
            }
        }
        return false;
    }

    protected HashMap<GetTile, Position> getNeabyTiles(Position p)
    {
        final int tileWidth = (int) ((double) MAP_WIDTH / (double) MAP_ROWS);
        final int tileHeight = (int) ((double) MAP_HEIGHT / (double) MAP_COLUMNS);
        // hoeveel tegels beslaat een speler
        int w = 1 + (int) ((double) PLAYER_RADIUS / (double) tileWidth);
        int h = 1 + (int) ((double) PLAYER_RADIUS / (double) tileHeight);
        HashMap<GetTile, Position> tiles = new HashMap<GetTile, Position>();
        int x = (int) ((double) player.getPosition().getX() / tileWidth);
        int y = (int) ((double) player.getPosition().getY() / tileHeight);
        for (int row = y - h; row <= y + h; row++)
        {
            for (int col = x - w; col <= x + w; col++)
            {
                tiles.put(mapHandler.getTile(row, col), new Position(col * tileWidth, row * tileHeight));
            }
        }
        tiles.remove(null);
        return tiles;
    }

    protected boolean collideCheck2Circles(Position p1, int r1, Position p2, int r2)
    {
        return p1.distance(p2) <= (r1 + r2);
    }

    protected boolean collisionCheckCircleVsRectangle(Position point, int r, Position rect, int width, int height)
    {
        if (pointInCircle(point, rect, r))
        {
            return true;
        }
        if (pointInCircle(point, new Position(rect.getX() + width, rect.getY()), r))
        {
            return true;
        }
        if (pointInCircle(point, new Position(rect.getX(), rect.getY() + height), r))
        {
            return true;
        }
        if (pointInCircle(point, new Position(rect.getX() + width, rect.getY() + height), r))
        {
            return true;
        }
        if (pointInRectangle(point, new Position(rect.getX() - r, rect.getY()), width + r + r, height))
        {
            return true;
        }
        if (pointInRectangle(point, new Position(rect.getX(), rect.getY() - r), width, height + r + r))
        {
            return true;
        }
        return false;
    }

    protected boolean pointInRectangle(Position point, Position rect, int width, int height)
    {
        return (point.getX() >= rect.getX()) && (point.getX() <= rect.getX() + width) && (point.getY() >= rect.getY()) && (point.getY() <= rect.getY() + height);
    }

    protected boolean pointInCircle(Position point, Position circ, int r)
    {
        return point.distance(circ) <= r;
    }
}
