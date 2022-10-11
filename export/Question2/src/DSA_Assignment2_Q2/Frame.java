package DSA_Assignment2_Q2;

import javax.swing.JFrame;

/**
 * This classes extends JFrame and contains the JPanel that has all the GUI
 * components.
 *
 * @author eirik
 */
class Frame extends JFrame {

    public Frame() {
        super("De-Noise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        add(new Panel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
