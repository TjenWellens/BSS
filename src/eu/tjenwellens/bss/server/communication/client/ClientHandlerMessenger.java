package eu.tjenwellens.bss.server.communication.client;

import eu.tjenwellens.bss.data.commands.Command;
import eu.tjenwellens.bss.data.commands.dataToClient.inventory.DataItem;
import eu.tjenwellens.bss.data.commands.exit.Logout;
import eu.tjenwellens.bss.data.commands.init.to_client.Err;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrLogin;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrSelectFaction;
import eu.tjenwellens.bss.data.commands.init.to_client.ErrSelectPosition;
import eu.tjenwellens.bss.data.commands.init.to_client.OkPlay;
import eu.tjenwellens.bss.data.commands.init.to_server.DoInit;
import eu.tjenwellens.bss.data.commands.init.to_server.DoLogin;
import eu.tjenwellens.bss.data.commands.init.to_server.DoPlay;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSelectFaction;
import eu.tjenwellens.bss.data.commands.init.to_server.DoSelectPosition;
import eu.tjenwellens.bss.data.commands.init.to_server.IOHandler;
import eu.tjenwellens.bss.data.commands.play.DataForClient;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Bank;
import eu.tjenwellens.bss.data.commands.play.dataToServer.ChooseWeapon;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Decorate;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Engage;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Idle;
import eu.tjenwellens.bss.data.commands.play.dataToServer.Walk;
import eu.tjenwellens.bss.server.actions.bankAction.Transaction;
import eu.tjenwellens.bss.server.actions.decorateAction.Decoration;
import eu.tjenwellens.bss.server.communication.init.InitPlayer;
import eu.tjenwellens.bss.server.communication.input.Input;
import eu.tjenwellens.bss.server.communication.output.Output;
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
    // init done
    private IOHandler play_init = new InitHandler();

    public ClientHandlerMessenger(Socket clientSocket, String clientAddress, ClientListener clientListener, Input input, Output output)
    {
        super(clientSocket, clientAddress, clientListener);
        this.output = output;
        this.input = input;
    }

    @Override
    protected void manageOutput()
    {
        play_init.manageOutput();
    }

    private class PlayHandler implements IOHandler
    {
        private int playerId;

        public PlayHandler(int playerId)
        {
            this.playerId = playerId;
        }

        @Override
        public void manageOutput()
        {
            DataForClient dfc = output.getData(playerId);
            if (dfc != null)
            {
                send(dfc);
            }
        }

        @Override
        public void manageInput(Command command)
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
            } //        else if (command instanceof DoSave)
            //        {
            //            // quickplay -> save progress, cf signup, continue with a saveable account
            //            DoSave save = (DoSave) command;
            //            int success = input.save(playerId, save.getName(), save.getPass(), save.getPlayerName());
            //            if (success >= 0)
            //            {
            //                send(new OkSignUp());
            //                playerId = success;
            //            } else
            //            {
            //                send(new ErrSignUp("err"));
            //            }
            //        } 
            else if (command instanceof Logout)
            {
                input.logout(playerId);
                end();
            } else
            {
                System.out.println("ERROR: need to choose an action" + command);
            }
        }

        @Override
        public void exit()
        {
            input.logout(playerId);
        }
    }

    private enum InitProgress
    {
        NONE, LOGIN, FACTION, POSITION, PLAY
    }

    private class InitHandler implements IOHandler
    {
        private InitPlayer ip = new InitPlayer();

        @Override
        public void manageOutput()
        {
            if (ip.isPlayDone())
            {
                done();
            }
            if (ip.isPending())
            {
                return;
            }
            DataForClient dfc = null;
            if (!ip.isLoginDone())
            {
                // skip
            } else if (!ip.isFactionDone())
            {
                // send factions
                output.getFactions();
            } else if (!ip.isPositionDone())
            {
                // send map
                output.getMiniMap();
            }
            if (dfc != null)
            {
                send(dfc);
            }
        }

        private void done()
        {
            ClientHandlerMessenger.this.play_init = new PlayHandler(ip.getId());
            send(new OkPlay());
        }

        @Override
        public void manageInput(Command command)
        {
            if (ip.isPlayDone())
            {
                done();
                ClientHandlerMessenger.this.play_init.manageInput(command);
            }

            String error = null;
            if (!(command instanceof DoInit))
            {
                error = "Error, need to init first";
            } else if (ip.isPending())
            {
                // wait
                error = "Waiting for server to finish request";
            } else if (!ip.isLoginDone())
            {
                // Login
                if (command instanceof DoLogin)
                {
                    DoLogin cmd = (DoLogin) command;
                    ip = input.login(cmd.getName(), cmd.getPassword());
                } else
                {
                    send(new ErrLogin("login failed"));
                }
            } else if (!ip.isFactionDone())
            {
                // Select Faction
                if (command instanceof DoSelectFaction)
                {
                    DoSelectFaction cmd = (DoSelectFaction) command;
                    input.selectFaction(ip.getId(), cmd.getFactionName());
                } else
                {
                    send(new ErrSelectFaction("Select a faction"));
                }
            } else if (!ip.isPositionDone())
            {
                // Select Position
                if (command instanceof DoSelectPosition)
                {
                    DoSelectPosition cmd = (DoSelectPosition) command;
                    input.selectPosition(ip.getId(), cmd.getXPosition(), cmd.getYPosition());
                } else
                {
                    send(new ErrSelectPosition("Select a position"));
                }
            } else
            {
                // Play
                if (command instanceof DoPlay)
                {
                    input.play(ip.getId());
                } else
                {
                    send(new ErrSelectPosition("Play?"));
                }
            }
            if (error != null)
            {
                send(new Err(error));
            }
        }

        @Override
        public void exit()
        {
            if (ip != null && ip.getId() != 0)
            {
                input.logout(ip.getId());
            }
        }
    }

    @Override
    protected void manageInput(Command command)
    {
        play_init.manageInput(command);
    }

    @Override
    protected void exit()
    {
        play_init.exit();
    }
}
