package eu.tjenwellens.bss.actionhandlers.walkAction.walk;

import eu.tjenwellens.bss.Position;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkHandlerInterface;
import eu.tjenwellens.bss.actionhandlers.walkAction.WalkPlayer;
import eu.tjenwellens.bss.factions.Faction;
import eu.tjenwellens.bss.map.MapHandlerInterface;

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
