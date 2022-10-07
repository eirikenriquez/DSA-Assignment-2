package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private static final int NODE_DIAMETER = 25;
    private static final int NAME_OFFSET = -15;
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
        drawNodes(g, model.root);
    }

    private void drawNodes(Graphics g, Node current) {
        if (current != null && !current.drawn) {
            g.setColor(Color.BLUE);
            g.fillOval(convertPostion(current.x), convertPostion(current.y), NODE_DIAMETER, NODE_DIAMETER);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(current.name, convertPostion(current.x) + NAME_OFFSET, convertPostion(current.y) + NAME_OFFSET);

            current.drawn = true;
            drawLink(g, current);
            drawNodes(g, current.nextOne);
            drawNodes(g, current.nextTwo);
        }
    }

    private void drawLink(Graphics g, Node current) {
        if (current.nextOne != null) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.nextOne.x) + NODE_DIAMETER / 2,
                    convertPostion(current.nextOne.y) + NODE_DIAMETER / 2);
        }

        if (current.nextTwo != null) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.nextTwo.x) + NODE_DIAMETER / 2,
                    convertPostion(current.nextTwo.y) + NODE_DIAMETER / 2);
        }
    }

    private int convertPostion(int position) {
        return position * SCALE + BORDER;
    }
}
