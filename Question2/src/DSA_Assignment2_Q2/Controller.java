package DSA_Assignment2_Q2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the conroller for the GUI buttons and is used for reading the button
 * press and calling methods for updating the GUI accordingly.
 *
 * @author eirik
 */
class Controller implements ActionListener {

    private Panel panel;
    private ImageProcess ip;

    public Controller(Panel panel) {
        this.panel = panel;
    }

    public void setModel(ImageProcess ip) {
        this.ip = ip;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        switch (action) {
            case "Load":

                break;
            case "Clean":

                break;
            case "Save":

                break;
            default:
                break;
        }
    }

}
