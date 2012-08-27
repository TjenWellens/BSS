package eu.tjenwellens.bss.map;

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
