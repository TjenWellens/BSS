package eu.tjenwellens.bss.server.components.map;

import java.util.List;

/**
 *
 * @author tjen
 */
public interface GetMap
{
    List<GetTile> getTiles();

    int getRows();

    int getCols();
}
