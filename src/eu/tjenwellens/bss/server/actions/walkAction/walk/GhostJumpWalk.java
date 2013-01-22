package eu.tjenwellens.bss.server.actions.walkAction.walk;

import eu.tjenwellens.bss.server.actions.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.server.actions.walkAction.WalkPlayer;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.factions.Faction;
import eu.tjenwellens.bss.server.components.map.MapHandlerInterface;

/**
 *
 * @author tjen
 */
public class GhostJumpWalk extends JumpWalk
{
    private MapHandlerInterface mapHandler;

    public GhostJumpWalk(WalkHandlerInterface walkHandler, WalkPlayer player, Position destination, MapHandlerInterface mapHandler)
    {
        super(walkHandler, player, destination);
        this.mapHandler = mapHandler;
    }

    @Override
    protected Position step()
    {// TODO: solve problem - 23/08/2012 what problem?
        Position p = super.step();
        if (p == null)
        {
            return p;
        }
        int row = (int) (p.getX() / (MAP_WIDTH / MAP_ROWS));
        int col = (int) (p.getY() / (MAP_HEIGHT / MAP_COLUMNS));
        Faction faction = mapHandler.getTile(row, col).getFaction();
        if (!faction.containsPlayer(player))
        {
            return p;
        }
        // player is on one of his own tiles -> revive
        player.revive();
        return p;
    }
}
