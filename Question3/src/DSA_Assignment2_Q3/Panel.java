package DSA_Assignment2_Q3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
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
    private static final int POINTER_DELAY = 500;
    private final int panelWidth;
    private final int panelHeight;
    private final Model model;
    public Stack<Node> correctPath;
    public LinkedList<Node> visitHistory;
    public boolean exitFound;
    public boolean pointerAtExit;
    public int pointer;

    public Panel(Model model) {
        this.model = model;
        this.panelWidth = model.columns * SCALE;
        this.panelHeight = model.rows * SCALE;
        this.correctPath = new Stack<>();
        this.visitHistory = new LinkedList<>();
        this.exitFound = false;
        this.pointerAtExit = false;
        this.pointer = 0;
        setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setPreferredSize(new Dimension(panelWidth + BORDER, panelHeight + BORDER));
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNodes(g, model.start, 0);
        drawOtherPaths(g, model.start, 0);
        if (!exitFound) {
            search(g, model.start, new Stack<>());
        } else {
            drawPointer(g, visitHistory.get(pointer));
            if (pointerAtExit) {
                drawCorrectPath(g);
            }
        }
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

    private void drawPointer(Graphics g, Node current) {
        pointer++;
        g.setColor(Color.YELLOW);
        g.fillOval(convertPostion(current.x), convertPostion(current.y), NODE_DIAMETER, NODE_DIAMETER);
        try {
            Thread.sleep(POINTER_DELAY);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.toString());
        }

        if (!current.name.equals("EXIT")) {
            repaint();
        } else {
            pointerAtExit = true;
        }
    }

    public void search(Graphics g, Node current, Stack<Node> path) {
        System.out.println("cur" + current);

        current.visited = true;
        visitHistory.add(current);

        if (current.name.equals("EXIT")) {
            path.push(current);
            correctPath = path;
            exitFound = true;
        } else if (current.nextOne != null && !current.nextOne.visited) {
            path.push(current);
            search(g, current.nextOne, path);
        } else if (current.nextTwo != null && !current.nextTwo.visited) {
            path.push(current);
            search(g, current.nextTwo, path);
        } else {
            // go back if next is null
            search(g, path.pop(), path);
        }

    }

    private void drawCorrectPath(Graphics g) {
        Iterator<Node> iter = correctPath.iterator();

        // draw line
        if (iter.hasNext()) {
            Node current = iter.next();
            while (iter.hasNext()) {
                Node next = iter.next();
                g.setColor(Color.GREEN);
                g.drawLine(convertPostion(current.x) + NODE_DIAMETER / 2,
                        convertPostion(current.y) + NODE_DIAMETER / 2,
                        convertPostion(next.x) + NODE_DIAMETER / 2,
                        convertPostion(next.y) + NODE_DIAMETER / 2);
                current = next;
            }
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
