package DSA_Assignment2_Q1;

import javax.swing.JFrame;

/**
 *
 * @author eirik
 */
public class Frame extends JFrame {

    public Frame() {
        super("Student Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
        add(new Panel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
