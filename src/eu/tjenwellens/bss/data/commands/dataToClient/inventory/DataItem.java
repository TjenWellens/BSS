package eu.tjenwellens.bss.data.commands.dataToClient.inventory;

import java.io.Serializable;

/**
 *
 * @author Tjen
 */
public interface DataItem extends Serializable
{
    String getMaterial();

    String getToolType();

    String getWeaponType();
}
