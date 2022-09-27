package DSA_Assignment2_Q1;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author eirik
 */
public class Panel extends JPanel {

    private JButton loadButton;
    private JButton saveButton;
    private JButton sortButton;
    private JButton searchButton;

    public Panel() {
        setFocusable(true);
        initButtons();
    }

    private void initButtons() {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        sortButton = new JButton("Sort");
        searchButton = new JButton("Search");

        loadButton.addActionListener(new Controller(this));
        saveButton.addActionListener(new Controller(this));
        sortButton.addActionListener(new Controller(this));
        searchButton.addActionListener(new Controller(this));

        add(loadButton);
        add(saveButton);
        add(sortButton);
        add(searchButton);
    }

}
