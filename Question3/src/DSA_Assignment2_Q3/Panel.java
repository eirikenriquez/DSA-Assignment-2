package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
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
    private int pointer;

    public Panel(Model model) {
        this.model = model;
        this.panelWidth = model.columns * SCALE;
        this.panelHeight = model.rows * SCALE;
        this.pointer = 0;
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setPreferredSize(new Dimension(panelWidth + BORDER, panelHeight + BORDER));
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawOtherPaths(g, model.start, 0);
        drawNodes(g, model.start, 0);
        drawPointer(g);
    }

    private void drawNodes(Graphics g, Node current, int iterator) {
        if (current != null && iterator <= model.nodesSize) {
            // actual node
            g.setColor(Color.BLUE);
            g.fillOval(convertPostion(current.x), convertPostion(current.y), NODE_DIAMETER, NODE_DIAMETER);

            // title
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 16));
            g.drawString(current.name, convertPostion(current.x) + NAME_OFFSET, convertPostion(current.y) + NAME_OFFSET);

            drawNodes(g, current.nextOne, iterator + 1);
            drawNodes(g, current.nextTwo, iterator + 1);
        }
    }

    private void drawPointer(Graphics g) {
        if (model.correctPath[pointer] != null) {
            // pointer moving
            g.setColor(Color.YELLOW);
            Node current = model.correctPath[pointer];
            g.fillOval(convertPostion(current.x), convertPostion(current.y), NODE_DIAMETER, NODE_DIAMETER);

            pointer++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex.toString());
            }

            // draw path highlight when pointer is at exit
            if (model.correctPath[pointer] != null) {
                repaint();
            } else {
                drawCorrectPath(g);
            }
        }
    }

    private void drawCorrectPath(Graphics g) {
        for (int i = 0; model.correctPath[i + 1] != null; i++) {
            // draw line
            g.setColor(Color.GREEN);
            g.drawLine(convertPostion(model.correctPath[i].x) + NODE_DIAMETER / 2,
                    convertPostion(model.correctPath[i].y) + NODE_DIAMETER / 2,
                    convertPostion(model.correctPath[i + 1].x) + NODE_DIAMETER / 2,
                    convertPostion(model.correctPath[i + 1].y) + NODE_DIAMETER / 2);
        }
    }

    private void drawOtherPaths(Graphics g, Node current, int iterator) {
        if (iterator > model.linkers) {
            return;
        }

        g.setColor(Color.RED);

        if (current.nextOne != null) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.nextOne.x) + NODE_DIAMETER / 2,
                    convertPostion(current.nextOne.y) + NODE_DIAMETER / 2);
            drawOtherPaths(g, current.nextOne, iterator + 1);
        }

        if (current.nextTwo != null) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.nextTwo.x) + NODE_DIAMETER / 2,
                    convertPostion(current.nextTwo.y) + NODE_DIAMETER / 2);
            drawOtherPaths(g, current.nextTwo, iterator + 1);
        }
    }

    private int convertPostion(int position) {
        return position * SCALE + BORDER;
    }
}
