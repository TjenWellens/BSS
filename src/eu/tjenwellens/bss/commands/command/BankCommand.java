package eu.tjenwellens.bss.commands.command;

import eu.tjenwellens.bss.actionhandlers.bankAction.Transaction;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import eu.tjenwellens.bss.players.inventory.items.Item;

/**
 *
 * @author tjen
 */
public class BankCommand extends Command
{
    private Transaction transaction;
    private int diamands;
    private Item item;

    public BankCommand(CommandReceiverInterface cr, int playerID, Transaction transaction, int diamands, Item item)
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
