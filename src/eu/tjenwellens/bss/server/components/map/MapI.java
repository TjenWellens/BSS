package eu.tjenwellens.bss.server.components.map;

import java.util.List;

/**
 *
 * @author Tjen
 */
public interface MapI extends GetMap
{
    @Override
    List<GetTile> getTiles();

    @Override
    int getRows();

    @Override
    int getCols();

    void setTile(GetTile getTile);

    boolean isPositionInMap(int row, int col);

    GetTile getTile(int row, int col);

    GetMap getCopy();

}
