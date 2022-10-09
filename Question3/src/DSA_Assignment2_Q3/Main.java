package DSA_Assignment2_Q3;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class contains the main method that instantiates the Frame object which
 * is the GUI for the program.
 *
 * @author eirik
 */
public class Main {

    public static void main(String[] args) {
        Model model = askForFile(new Model());
        model.setNextNodes();
        model.printNodes();
        model.search(model.start, new Node[99], 0);

        Panel panel = new Panel(model);
        Frame frame = new Frame(panel);
    }

    private static Model askForFile(Model model) {
        while (model.nodesSize == 0) {
            JOptionPane.showMessageDialog(null, "Please select a maze text file!");

            try {
                model.readFile(fileChooser());
            } catch (FileNotFoundException | NullPointerException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return model;
    }

    private static File fileChooser() {
        JFileChooser chooser = new JFileChooser(new File("./res"));
        chooser.setFileFilter(new FileNameExtensionFilter("Text File (\".txt\")", "txt"));
        int option = chooser.showOpenDialog(null);

        // if user chooses a file
        if (option == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else if (option == JFileChooser.CANCEL_OPTION) {
            System.exit(0);
        }

        return null;
    }
}
