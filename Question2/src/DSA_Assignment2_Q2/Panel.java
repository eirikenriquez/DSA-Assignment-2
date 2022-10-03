package DSA_Assignment2_Q2;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class extends JPanel and holds all the GUI components.
 *
 * @author eirik
 */
public class Panel extends JPanel {

    public JButton loadButton;
    public JButton cleanButton;
    public JButton saveButton;

    public Panel() {
        setFocusable(true);
        initButtons();
    }

    private void initButtons() {
        Controller controller = new Controller(this);

        // initialise buttons
        this.loadButton = new JButton("Load");
        this.cleanButton = new JButton("Clean");
        this.cleanButton.setEnabled(false);
        this.saveButton = new JButton("Save");
        this.saveButton.setEnabled(false);

        // add listeners
        this.loadButton.addActionListener(controller);
        this.cleanButton.addActionListener(controller);
        this.saveButton.addActionListener(controller);

        // add buttons to panel
        add(loadButton);
        add(cleanButton);
        add(saveButton);
    }

    /**
     * This method loads a file and returns a String object containing the path
     * of the file.
     *
     * @return String that contains the path of the file chosen by the user.
     */
    public String loadFile() {
        JFileChooser chooser = new JFileChooser(new File("."));
        int option = chooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        }

        return null;
    }

    /**
     * This pops up a message that notifies the user when the program is done
     * cleaning the image.
     */
    public void doneCleaning() {
        JOptionPane.showMessageDialog(this, "Done Cleaning!");
    }

    /**
     * This method pops up a message that notifies the user the file name of the
     * image saved.
     */
    public void fileSaved(String fileName) {
        JOptionPane.showMessageDialog(this, "Saved as \"" + fileName + "\"!");
    }
}
