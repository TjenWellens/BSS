package eu.tjenwellens.bss.client.gui;

import eu.tjenwellens.bss.client.communication.ClientMessager;
import eu.tjenwellens.bss.client.components.ClientGamer;
import eu.tjenwellens.bss.client.components.items.Material;
import eu.tjenwellens.bss.client.components.items.Weapon;
import eu.tjenwellens.bss.client.components.items.WeaponType;
import eu.tjenwellens.bss.client.mvc.ClientModel;
import eu.tjenwellens.update.Updatable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Tjen
 */
public class GamePanel extends javax.swing.JPanel implements Updatable
{
    private MapPanel map;
    private ActionPanel action;
    private ClientModel model;
    private ClientMessager messenger;

    public GamePanel(ClientModel model, ClientMessager messenger, BssPanel parent)
    {
        this.model = model;
        this.messenger = messenger;
        map = new MapPanel(model, messenger, parent);
        action = new ActionPanel();
        doGUI();
    }
    private boolean dialogShowing = false;

    private void showDialog()
    {
        dialogShowing = true;
        ButtonGroup group = new ButtonGroup();
        JRadioButton paper = new JRadioButton("Paper");
        JRadioButton stone = new JRadioButton("Stone");
        JRadioButton scissors = new JRadioButton("Scissors");
        group.add(paper);
        group.add(stone);
        group.add(scissors);
        paper.setSelected(true);
        final JComponent[] inputs = new JComponent[]
        {
            paper, stone, scissors
        };
        JOptionPane.showMessageDialog(null, inputs, "Select an attack...", JOptionPane.PLAIN_MESSAGE);
        group.getSelection().getActionCommand();
        paper.isSelected();
        System.out.println("You entered:: paper: "
                + paper.isSelected() + ", stone: "
                + stone.isSelected() + ", scissors: "
                + scissors.isSelected());
        WeaponType wt;
        if (scissors.isSelected())
        {
            wt = WeaponType.SCHAAR;
        } else if (stone.isSelected())
        {
            wt = WeaponType.STEEN;
        } else
        {
            wt = WeaponType.BLAD;
        }
        Weapon w = new Weapon(wt, Material.IJZER);
        messenger.chooseWeapon(w);
        System.out.println("You choose: " + w);
        done = true;
        dialogShowing = false;
    }
    boolean done = false;

    @Override
    public void update()
    {
        map.update();
        ClientGamer gamer = model.getGamer();
        if (gamer != null)
        {
            action.updateInfo(gamer);
        }
        if (gamer.state.equalsIgnoreCase("ATTACK"))
        {
            if (!done && !dialogShowing)
            {
                showDialog();
            }
        } else
        {
            done = false;
        }
    }

    private void doGUI()
    {
        setLayout(new BorderLayout());
        this.add(map, BorderLayout.CENTER);
        this.add(action, BorderLayout.EAST);
        JButton b = new JButton("Choose Weapon");
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showDialog();
            }
        });
        this.add(b, BorderLayout.SOUTH);
    }
}
