package DSA_Assignment2_Q3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This holds all the actual Maze information.
 *
 * @author eirik
 */
public class Model {

    public int linkers;
    public int columns;
    public int rows;
    public Node[] nodes;
    public int nodesSize;
    public Node start;
    public Node end;
    public Stack<Node> correctPath;
    public ArrayList<Node> visitHistory;
    public boolean exitFound;

    public Model() {
        // file reading
        this.linkers = 0;
        this.columns = 0;
        this.rows = 0;
        this.nodes = null;
        this.nodesSize = 0;
        this.end = null;

        // search method
        this.correctPath = new Stack<>();
        this.visitHistory = new ArrayList<>();
        this.exitFound = false;
    }

    public void readFile(File file) throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            int lineIndex = 0;

            while (line != null) {
                if (lineIndex == 0) {
                    // get maze info
                    initMaze(line);
                } else {
                    // get Node info
                    addNode(line, lineIndex - 1);
                    this.start = nodes[0];
                    this.end = nodes[nodesSize - 1];
                }

                line = br.readLine();
                lineIndex++;
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }

    }

    private void initMaze(String line) {
        String[] mazeInfo = line.split(",");
        this.linkers = Integer.parseInt(mazeInfo[0]);
        this.columns = Integer.parseInt(mazeInfo[1]);
        this.rows = Integer.parseInt(mazeInfo[2]);
        this.nodes = new Node[columns * rows];
    }

    private void addNode(String line, int nodesIndex) {
        String[] nodeInfo = line.split(",");
        String name = nodeInfo[0];
        int x = Integer.parseInt(nodeInfo[1]);
        int y = Integer.parseInt(nodeInfo[2]);
        String nextOneName = nodeInfo[3];
        String nextTwoName = nodeInfo[4];

        Node node = new Node(name, x, y, nextOneName, nextTwoName);
        nodes[nodesIndex] = node;
        nodesSize++;
    }

    /**
     * This sets the next Nodes of each Node in the nodes array.
     */
    public void setNextNodes() {
        for (int i = 0; i < nodesSize; i++) {
            for (int j = 1; j < nodesSize; j++) {
                // check nextOne
                if (nodes[i].nextOneName.equals(nodes[j].name)) {
                    // current nextOne
                    nodes[i].nextOne = nodes[j];
                } else if (nodes[i].nextOneName.equals("A")) {
                    // current nextOne is null
                    nodes[i].nextOne = null;
                } else if (nodes[i].nextOneName.equals("W") && nodes[j].name.equals("EXIT")) {
                    // current nextOne is end
                    nodes[i].nextOne = nodes[j];
                }

                // check nextTwo
                if (nodes[i].nextTwoName.equals(nodes[j].name)) {
                    // current nextTwo
                    nodes[i].nextTwo = nodes[j];
                } else if (nodes[i].nextTwoName.equals("A")) {
                    // current nextTwo is null
                    nodes[i].nextTwo = null;
                } else if (nodes[i].nextTwoName.equals("W") && nodes[j].name.equals("EXIT")) {
                    // current nextTwo is end
                    nodes[i].nextTwo = nodes[j];
                }
            }
        }
    }

    public void search() {
        search(start, new Stack<>());
    }

    private void search(Node current, Stack<Node> path) {
        current.visited = true;
        visitHistory.add(current);

        if (current.name.equals("EXIT")) {
            path.push(current);
            correctPath = path;
            exitFound = true;
        } else if (current.nextOne != null && !current.nextOne.visited) {
            path.push(current);
            search(current.nextOne, path);
        } else if (current.nextTwo != null && !current.nextTwo.visited) {
            path.push(current);
            search(current.nextTwo, path);
        } else {
            // go back if next is null
            search(path.pop(), path);
        }
    }

    public void printNodes() {
        System.out.println(this.toString());
        int i = 0;
        while (nodes[i] != null) {
            System.out.println(nodes[i]);
            i++;
        }
    }

    @Override
    public String toString() {
        return "Model{" + "linkers=" + linkers + ", columns=" + columns + ", rows=" + rows + '}';
    }
}
