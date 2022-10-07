package DSA_Assignment2_Q3;

import javax.swing.JFrame;

/**
 * This class is a JFrame that holds the Panel object which contains all the
 * components for the GUI.
 *
 * @author eirik
 */
public class Frame extends JFrame {

    public Frame(Panel panel) {
        super("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
