/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.serverVSclient.client.components;

import eu.tjenwellens.bss.serverVSclient.communication.dataToClient.DataFaction;

/**
 *
 * @author tjen
 */
public class ClientFaction
{
    public String name;

    public ClientFaction(DataFaction dataFaction)
    {
        this.name = dataFaction.getName();
    }
}
