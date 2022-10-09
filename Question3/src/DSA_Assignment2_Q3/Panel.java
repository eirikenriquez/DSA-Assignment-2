package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final int POINTER_OFFSET = 15;
    private final int panelWidth;
    private final int panelHeight;
    private final Model model;

    public Panel(Model model) {
        // gui stuff
        this.model = model;
        this.panelWidth = model.columns * SCALE;
        this.panelHeight = model.rows * SCALE;
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setPreferredSize(new Dimension(panelWidth + BORDER, panelHeight + BORDER));
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawOtherPaths(g, model.start, 0);
        drawCorrectPath(g);
        drawNodes(g, model.start);
    }

    private void drawNodes(Graphics g, Node current) {
        if (current != null && !current.drawnNode) {
            // actual node
            g.setColor(Color.BLUE);
            g.fillOval(convertPostion(current.x), convertPostion(current.y), NODE_DIAMETER, NODE_DIAMETER);

            // title
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 16));
            g.drawString(current.name, convertPostion(current.x) + NAME_OFFSET, convertPostion(current.y) + NAME_OFFSET);

            current.drawnNode = true;
            drawNodes(g, current.nextOne);
            drawNodes(g, current.nextTwo);
        }
    }

    private void drawCorrectPath(Graphics g) {
        for (int i = 0; model.correctPath[i + 1] != null; i++) {
            /*// draw pointer
            int pointerX = convertPostion(model.correctPath[i].x) + POINTER_OFFSET;
            int pointerY = convertPostion(model.correctPath[i].y) + POINTER_OFFSET;
            int targetX = convertPostion(model.correctPath[i + 1].x) + POINTER_OFFSET;
            int targetY = convertPostion(model.correctPath[i + 1].y) + POINTER_OFFSET;
            g.setColor(Color.YELLOW);

            while (pointerX != targetX || pointerY != targetY) {
                g.fillRect(pointerX, pointerY, 5, 10);
                g.fillRect(pointerX, pointerY, 5, 10);
                g.drawString("Pointer", pointerX + 5, pointerY + 5);

                // move x
                if (pointerX < targetX) {
                    pointerX++;
                } else {
                    pointerX--;
                }

                // move y
                if (pointerY < targetY) {
                    pointerY++;
                } else {
                    pointerY--;
                }

                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    System.out.println("Error: " + ex);
                }
            }
             */
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
