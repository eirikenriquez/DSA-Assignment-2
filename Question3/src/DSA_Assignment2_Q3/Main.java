package DSA_Assignment2_Q3;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class contains the main method that instantiates the Frame object which
 * is the GUI for the program.
 *
 * @author eirik
 */
public class Main {

    public static void main(String[] args) {
        Model model = new Model();

        try {
            model.readFile(fileChooser());
        } catch (FileNotFoundException | NullPointerException ex) {
            System.out.println("Error: " + ex.toString());
            System.exit(0);
        }

        model.setNextNodes();
        model.printNodes();

        Panel panel = new Panel(model);
        Frame frame = new Frame(panel);
    }

    private static File fileChooser() {
        JFileChooser chooser = new JFileChooser(new File("./res"));
        chooser.setFileFilter(new FileNameExtensionFilter("Text File (\".txt\")", "txt"));
        int option = chooser.showOpenDialog(null);

        // if user chooses a file
        if (option == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null;
    }
}
