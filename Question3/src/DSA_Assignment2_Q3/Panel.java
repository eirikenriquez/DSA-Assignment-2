package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * This is the JPanel that holds all the components for the GUI.
 *
 * @author eirik
 */
class Panel extends JPanel {

    private static final int BORDER = 50;
    private static final int SCALE = 100;
    private final int panelWidth;
    private final int panelHeight;
    private final Model model;

    public Panel(Model model) {
        this.model = model;
        this.panelWidth = model.columns * SCALE;
        this.panelHeight = model.rows * SCALE;
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setPreferredSize(new Dimension(panelWidth + BORDER, panelHeight + BORDER));
        setFocusable(true);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNodes(g);
    }

    private void drawNodes(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < model.nodesSize; i++) {
            Node currentNode = model.nodes[i];
            g.fillOval(convertPostion(currentNode.x) + BORDER, convertPostion(currentNode.y) + BORDER, 25, 25);
        }
    }

    private int convertPostion(int position) {
        return position * SCALE;
    }
}
