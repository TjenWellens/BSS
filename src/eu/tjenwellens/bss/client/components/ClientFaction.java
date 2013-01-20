/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.client.components;

import eu.tjenwellens.bss.data.commands.dataToClient.DataFaction;

/**
 *
 * @author tjen
 */
public class ClientFaction
{
    public int id;
    public String name;

    public ClientFaction(DataFaction dataFaction)
    {
        this.id = dataFaction.getId();
        this.name = dataFaction.getName();
    }
}
