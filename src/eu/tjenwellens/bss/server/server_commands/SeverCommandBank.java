package eu.tjenwellens.bss.server.server_commands;

import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.mvc.model.CommandReceiverInterface;

/**
 *
 * @author tjen
 */
public class SeverCommandBank extends ServerCommand
{
    private Transaction transaction;
    private int diamands;
    private Item item;

    public SeverCommandBank(CommandReceiverInterface cr, int playerID, Transaction transaction, int diamands, Item item)
    {
        super(cr, playerID);
        this.transaction = transaction;
        this.diamands = diamands;
        this.item = item;
    }

    @Override
    public void execute()
    {
        cr.bankCommand(playerID, transaction, diamands, item);
    }
}
