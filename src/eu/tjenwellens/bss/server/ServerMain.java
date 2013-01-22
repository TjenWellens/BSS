package eu.tjenwellens.bss.server;

import eu.tjenwellens.bss.server.communication.client.ClientListener;
import eu.tjenwellens.bss.server.communication.input.ConcreteInput;
import eu.tjenwellens.bss.server.communication.output.ConcreteOutput;
import eu.tjenwellens.bss.server.communication.input.Input;
import eu.tjenwellens.bss.server.communication.output.Output;
import eu.tjenwellens.bss.server.mvc.controller.Controller;
import eu.tjenwellens.bss.server.mvc.model.Model;
import eu.tjenwellens.bss.server.mvc.view.View;
import eu.tjenwellens.update.ConcreteUpdater;

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
//            java.util.logging.Logger.getLogger(ConcreteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        runServer();
    }

    public static void runServer()
    {
        // MVC
        Model model = Model.getInstance();
        View view = new View(model);
        Controller controller = new Controller();
        model.registerUpdatable(view);
        controller.registerUpdatable(model);
        // INPUT-OUTPUT
        Input input = new ConcreteInput(controller, model);
        Output output = new ConcreteOutput(view);
        // CLIENTS
        ConcreteUpdater updater = new ConcreteUpdater(2);
        ClientListener clientListener = new ClientListener(updater, input, output);

        clientListener.start();
        updater.start();
        controller.start();
        // quit server
        new Thread(new STDINquit()).start();
    }
}
