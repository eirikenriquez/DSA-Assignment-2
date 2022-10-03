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

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        switch (action) {
            case "Load":
                panel.loadButton.setEnabled(false);
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
                panel.loadButton.setEnabled(true);
                break;
            default:
                break;
        }
    }

}
