package eu.tjenwellens.bss.server.communication;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.DataItem;
import eu.tjenwellens.bss.data.commands.exit.Logout;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrFullInit;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrQuickplay;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrSignUp;
import eu.tjenwellens.bss.data.commands.init.to_client.OKFullInit;
import eu.tjenwellens.bss.data.commands.init.to_client.OkQuickplay;
import eu.tjenwellens.bss.data.commands.init.to_client.OkSignUp;
import eu.tjenwellens.bss.data.commands.init.to_server.DoFullInit;
import eu.tjenwellens.bss.data.commands.init.to_server.DoQuickplay;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSave;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSignup;
import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Bank;
import eu.tjenwellens.bss.data.commands.play.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Decorate;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Engage;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Idle;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Walk;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.components.Position;
import eu.tjenwellens.bss.server.components.items.Item;
import eu.tjenwellens.bss.server.components.items.Material;
import eu.tjenwellens.bss.server.components.items.Tool;
import eu.tjenwellens.bss.server.components.items.ToolFactory;
import eu.tjenwellens.bss.server.components.items.ToolType;
import eu.tjenwellens.bss.server.components.items.Weapon;
import eu.tjenwellens.bss.server.components.items.WeaponFactory;
import eu.tjenwellens.bss.server.components.items.WeaponType;
import java.net.Socket;

/**
 *
 * @author Tjen
 */
public class ClientHandlerMessenger extends ClientHandler
{
    private Output output;
    private Input input;
    // player information
    private int playerId = 0;
    // init done
    private boolean initDone = false;

    public ClientHandlerMessenger(Socket clientSocket, String clientAddress, ClientListener clientListener, Input input, Output output)
    {
        super(clientSocket, clientAddress, clientListener);
        this.output = output;
        this.input = input;
    }

    @Override
    protected void manageOutput()
    {
        if (initDone)
        {
            DataForClient dfc = output.getData(playerId);
            if (dfc != null)
            {
                send(dfc);
            }
        } else
        {
            // TODO: handle init
        }
    }

    private void handleInit(Command command)
    {
        if (command instanceof DoFullInit)
        {
            DoFullInit login = (DoFullInit) command;
            playerId = input.login(login.getName(), login.getPass(), login.getPlayerName(), login.getFactionName(), new Position(login.getXPosition(), login.getYPosition()));
            if (playerId >= 0)
            {
                send(new OKFullInit());
                initDone = true;
            } else
            {
                send(new ErrFullInit("err"));
            }
        } else if (command instanceof DoSignup)
        {
            DoSignup signup = (DoSignup) command;
            boolean success = input.signup(signup.getName(), signup.getPass(), signup.getPlayerName());
            if (success)
            {
                send(new OkSignUp());
            } else
            {
                send(new ErrSignUp("err"));
            }
        } else if (command instanceof DoQuickplay)
        {
            System.out.println("Quickplay received");
            DoQuickplay quickplay = (DoQuickplay) command;
            playerId = input.quickplay(quickplay.getPlayerName(), quickplay.getFactionName(), new Position(quickplay.getXPosition(), quickplay.getYPosition()));
            if (playerId >= 0)
            {
                send(new OkQuickplay());
                initDone = true;
            } else
            {
                send(new ErrQuickplay("err"));
            }
        } else
        {
            System.out.println("ERROR: need to login" + command);
        }
    }

    private void handlePlay(Command command)
    {
        if (command instanceof Bank)
        {
            Bank bank = (Bank) command;
            DataItem di = bank.getItem();
            Item i;
            Material m = Material.fromName(di.getMaterial());
            i = WeaponFactory.createWeapon(WeaponType.fromName(di.getWeaponType()), m);
            if (i == null)
            {
                i = ToolFactory.createTool(ToolType.fromName(di.getToolType()), m);
            }
            input.bank(playerId, Transaction.fromName(bank.getTransaction()), bank.getDiamonds(), i);
        } else if (command instanceof ChooseWeapon)
        {
            DataItem di = ((ChooseWeapon) command).getWeapon();
            Weapon w = WeaponFactory.createWeapon(WeaponType.fromName(di.getWeaponType()), Material.fromName(di.getMaterial()));
            input.chooseWeapon(playerId, w);
        } else if (command instanceof Decorate)
        {
            Decorate decorate = (Decorate) command;
            DataItem di = decorate.getTool();
            Tool t = ToolFactory.createTool(ToolType.fromName(di.getToolType()), Material.fromName(di.getMaterial()));
            input.decorate(playerId, Decoration.fromName(decorate.getDecoration()), decorate.getRow(), decorate.getCol(), t);
        } else if (command instanceof Engage)
        {
            Engage engage = (Engage) command;
            input.engage(playerId, engage.getOpponentName());
        } else if (command instanceof Idle)
        {
            input.idle(playerId);
        } else if (command instanceof Walk)
        {
            Walk walk = (Walk) command;
            input.walk(playerId, new Position(walk.getXPosition(), walk.getYPosition()));
        } else if (command instanceof DoSave)
        {
            // quickplay -> save progress, cf signup, continue with a saveable account
            DoSave save = (DoSave) command;
            int success = input.save(playerId, save.getName(), save.getPass(), save.getPlayerName());
            if (success >= 0)
            {
                send(new OkSignUp());
                playerId = success;
            } else
            {
                send(new ErrSignUp("err"));
            }
        } else if (command instanceof Logout)
        {
            input.logout(playerId);
            end();
        } else
        {
            System.out.println("ERROR: need to choose an action" + command);
        }
    }

    @Override
    protected void manageInput(Command command)
    {
        if (initDone)
        {
            handlePlay(command);
        } else
        {
            handleInit(command);
        }
    }

    @Override
    protected void exit()
    {
        input.logout(playerId);
    }
}
