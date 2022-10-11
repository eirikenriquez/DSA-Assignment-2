package DSA_Assignment2_Q2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the controller for the GUI buttons and is used for reading the button
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

    /**
     * This reads the button pressed and calls methods accordingly.
     *
     * @param ae The button pressed.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        switch (action) {
            case "Load":
                String fileName = panel.loadFile();
                if (fileName != null) {
                    this.ip = new ImageProcess(fileName);
                    panel.cleanButton.setEnabled(true);
                }
                break;
            case "Clean":
                ip.cleanNoise();
                panel.doneCleaning();
                panel.saveButton.setEnabled(true);
                break;
            case "Save":
                String cleanedName = "noise_removed.jpg";
                ip.save(cleanedName);
                panel.fileSaved(cleanedName);
                break;
            default:
                break;
        }
    }

}
