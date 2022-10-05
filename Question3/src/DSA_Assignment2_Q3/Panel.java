package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author eirik
 */
class Panel extends JPanel {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 800;
    private JButton loadButton;

    public Panel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setFocusable(true);
        setBackground(Color.WHITE);

        // init loadButton
        this.loadButton = new JButton("Load");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
