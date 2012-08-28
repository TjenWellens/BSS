package eu.tjenwellens.bss;

import eu.tjenwellens.bss.mvc.concretes.ConcreteFrameWithMap;
import eu.tjenwellens.bss.mvc.controller.Controller;
import eu.tjenwellens.bss.mvc.model.Model;
import eu.tjenwellens.bss.mvc.view.View;
import eu.tjenwellens.bss.serverVSclient.ClientMain;
import eu.tjenwellens.bss.serverVSclient.ServerMain;

/**
 *
 * @author tjen
 */
public class BssMain
{
    public static void main(String[] args)
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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

        ServerMain.runServer();
        ClientMain.runClient();
    }

//    public static void runMultiplayerGame()
//    {
//        Model m = Model.getInstance();
//        View v = new View(m);
//        m.registerGlobalObserver(v);
//        Controller c = new Controller();
//        c.registerTickObserver(m);
//
//        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(m, v, c);
//        c.registerTickObserver(cfwm);
//        cfwm.setVisible(true);
//        ConcreteFrameWithMap cfwm2 = new ConcreteFrameWithMap(m, v, c);
//        c.registerTickObserver(cfwm2);
//        cfwm2.setVisible(true);
//
//        c.start();
//    }
//
//    public static void runSingleplayerGame()
//    {
//        Model m = Model.getInstance();
//        View v = new View(m);
//        m.registerGlobalObserver(v);
//        Controller c = new Controller();
//        c.registerTickObserver(m);
//
//        ConcreteFrameWithMap cfwm = new ConcreteFrameWithMap(m, v, c);
//        c.registerTickObserver(cfwm);
//        cfwm.setVisible(true);
////        ConcreteFrameWithMap cfwm2 = new ConcreteFrameWithMap(m, v, c);
////        c.registerTickObserver(cfwm2);
////        cfwm2.setVisible(true);
//
//        c.start();
//    }
//
//    private static void runServer()
//    {
//        Model m = Model.getInstance();
//        View v = new View(m);
//        m.registerGlobalObserver(v);
//        Controller c = new Controller();
//        c.registerTickObserver(m);
//
//        c.start();
//    }
}
