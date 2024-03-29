package DSA_Assignment2_Q1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author eirik
 */
public class Controller implements ActionListener {

    public Panel panel;

    public Controller(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        switch (action) {
            case "Load":
                panel.loadFile();
                break;
            case "Save":
                panel.saveFile();
                break;
            case "Set Key":
                panel.setKey();
                break;
            case "Sort":
                panel.sort();
                break;
            case "Search":
                panel.search();
                break;
            default:
                break;
        }
    }

}
