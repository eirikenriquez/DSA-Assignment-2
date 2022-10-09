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
    private Node[] correctPath;

    public Panel(Model model) {
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
//        drawCorrectPath(g, model.end);

        search(model.start, new Node[99], 0);
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

    /*private void drawCorrectPath(Graphics g, Node current) {
        g.setColor(Color.GREEN);

        if (current.parentOne != null && !current.parentOne.visited) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.parentOne.x) + NODE_DIAMETER / 2,
                    convertPostion(current.parentOne.y) + NODE_DIAMETER / 2);
            current.parentOne.visited = true;
            drawCorrectPath(g, current.parentOne);
        } else if (current.parentTwo != null && !current.parentTwo.visited) {
            g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                    convertPostion(current.y) + NODE_DIAMETER / 2,
                    convertPostion(current.parentTwo.x) + NODE_DIAMETER / 2,
                    convertPostion(current.parentTwo.y) + NODE_DIAMETER / 2);
            current.parentTwo.visited = true;
            drawCorrectPath(g, current.parentTwo);
        }
    }*/
    private void search(Node current, Node[] path, int iterator) {
        if (current.name.equals("EXIT")) {
            path[iterator] = current;
            correctPath = path;
        }

        if (current.nextOne != null && !current.nextOne.visited) {
            current.visited = true;
            path[iterator] = current;
            search(current.nextOne, path, iterator + 1);
        }

        if (current.nextTwo != null && !current.nextTwo.visited) {
            current.visited = true;
            path[iterator] = current;
            search(current.nextTwo, path, iterator + 1);
        }
    }

    private void drawCorrectPath(Graphics g) {
        g.setColor(Color.GREEN);
        for (int i = 0; correctPath[i + 1] != null; i++) {
            g.drawLine(convertPostion(correctPath[i].x) + NODE_DIAMETER / 2,
                    convertPostion(correctPath[i].y) + NODE_DIAMETER / 2,
                    convertPostion(correctPath[i + 1].x) + NODE_DIAMETER / 2,
                    convertPostion(correctPath[i + 1].y) + NODE_DIAMETER / 2);
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
