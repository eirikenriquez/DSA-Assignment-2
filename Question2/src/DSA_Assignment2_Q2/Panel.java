package DSA_Assignment2_Q2;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class extends JPanel and holds all the GUI components.
 *
 * @author eirik
 */
public class Panel extends JPanel {

    private JButton loadButton;
    private JButton cleanButton;
    private JButton saveButton;

    public Panel() {
        setFocusable(true);
        initButtons();
    }

    private void initButtons() {
        Controller controller = new Controller(this);

        this.loadButton = new JButton("Load");
        this.cleanButton = new JButton("Clean");
        this.saveButton = new JButton("Save");

        this.loadButton.addActionListener(controller);
        this.cleanButton.addActionListener(controller);
        this.saveButton.addActionListener(controller);

        add(loadButton);
        add(cleanButton);
        add(saveButton);
    }

}
