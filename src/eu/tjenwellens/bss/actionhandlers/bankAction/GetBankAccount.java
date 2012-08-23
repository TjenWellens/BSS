/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.tjenwellens.bss.actionhandlers.bankAction;

import java.util.List;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public interface GetBankAccount
{
    
    int getDiamonds();
    List<Item> getItemsCopy();
}
