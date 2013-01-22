package eu.tjenwellens.bss.server.actions.walkAction.walk;

import eu.tjenwellens.bss.GameConstants;
import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkObstaclePlayer;
import eu.tjenwellens.bss.server.actions.walkAction.WalkPlayer;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.map.GetTile;
import eu.tjenwellens.bss.server.components.map.MapHandlerInterface;
import eu.tjenwellens.bss.server.components.players.PlayerHandlerInterface;
import java.util.HashMap;
import java.util.Map.Entry;

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
    {// TODO: solve problem - 23/08/2012 what problem?
        Position p = getStepDestination();
        if (p == null || collides(p))
        {
            return null;
        }
        return p;
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
            if (wo.isGhost())
            {// nope
                continue;
            }
            if (!collideCheck2Circles(player.getPosition(), PLAYER_RADIUS, wo.getPosition(), PLAYER_RADIUS))
            {// nope
                continue;
            }
            return true;
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
            if (!entry.getKey().isWalled())
            {// nope
                continue;
            }
            if (!collisionCheckCircleVsRectangle(p, PLAYER_RADIUS, entry.getValue(), tileWidth, tileHeight))
            {// nope
                continue;
            }
            return true;
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
        HashMap<GetTile, Position> tiles = new HashMap<>();
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
        if (point.getX() < rect.getX())
        {
            return false;
        }
        if (point.getX() > rect.getX() + width)
        {
            return false;
        }
        if (point.getY() < rect.getY())
        {
            return false;
        }
        if (point.getY() > rect.getY() + height)
        {
            return false;
        }
        return true;
    }

    protected boolean pointInCircle(Position point, Position circ, int r)
    {
        return point.distance(circ) <= r;
    }
}
