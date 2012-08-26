package eu.tjenwellens.bss.serverVSclient;

import eu.tjenwellens.bss.mvc.controller.CommandInvoker;
import eu.tjenwellens.bss.mvc.controller.CommandInvokerInterface;
import eu.tjenwellens.bss.mvc.controller.Controller;
import eu.tjenwellens.bss.mvc.model.CommandReceiverInterface;
import eu.tjenwellens.bss.mvc.model.Model;
import eu.tjenwellens.bss.mvc.view.View;
import eu.tjenwellens.bss.serverVSclient.client.ClientMessager;
import eu.tjenwellens.bss.serverVSclient.client.Communication;
import eu.tjenwellens.bss.serverVSclient.client.mvc.ServerToModel;
import eu.tjenwellens.bss.serverVSclient.client.mvc.concretes.ConcreteFrameWithMap;
import eu.tjenwellens.bss.serverVSclient.parse.SimpleViewToData;
import eu.tjenwellens.bss.serverVSclient.parse.ViewToData;
import eu.tjenwellens.bss.serverVSclient.server.ClientListener;
import eu.tjenwellens.bss.serverVSclient.server.Input;
import eu.tjenwellens.bss.serverVSclient.server.InputInterface;
import eu.tjenwellens.bss.serverVSclient.server.Output;
import eu.tjenwellens.bss.serverVSclient.server.OutputInterface;

/**
 *
 * @author Tjen
 */
public class ServerMain
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
//            java.util.logging.Logger.getLogger(ConcreteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
//            java.util.logging.Logger.getLogger(ConcreteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
//            java.util.logging.Logger.getLogger(ConcreteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
//            java.util.logging.Logger.getLogger(ConcreteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        runServer();
        runClient();
    }

    private static void runServer()
    {
        //server
        Model model = Model.getInstance();
        View view = new View(model);
        model.registerGlobalObserver(view);
        Controller controller = new Controller();
        controller.registerTickObserver(model);
        ConcreteUpdater updater = new ConcreteUpdater(2);
        CommandInvokerInterface commandInvokerInterface = new CommandInvoker();
        CommandReceiverInterface commandReceiverInterface = Model.getInstance();
        InputInterface inputInterface = new Input(commandInvokerInterface, commandReceiverInterface);
        ViewToData viewToData = new SimpleViewToData();
        OutputInterface outputInterface = new Output(viewToData, view);
        ClientListener clientListener = new ClientListener(updater, inputInterface, outputInterface);

        clientListener.start();
        updater.start();
        controller.start();
    }

    private static void runClient()
    {
        Controller c = new Controller();
        //TODO: 
        ServerToModel serverToModel = null;
        Communication communication = new ClientMessager(serverToModel, "127.0.0.1");

        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(communication);
        c.registerTickObserver(cfwm);
        cfwm.setVisible(true);

        c.start();
    }
}
