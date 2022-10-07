package DSA_Assignment2_Q3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public Node root;

    public Model() {
        this.linkers = 0;
        this.columns = 0;
        this.rows = 0;
        this.nodes = null;
        this.nodesSize = 0;
        this.root = null;
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
                    this.root = nodes[0];
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
                if (nodes[i].nextOneName.equals(nodes[j].name)) {
                    nodes[i].nextOne = nodes[j];
                } else if (nodes[i].nextTwoName.equals(nodes[j].name)) {
                    nodes[i].nextTwo = nodes[j];
                } else if (nodes[i].nextOneName.equals("A")) {
                    nodes[i].nextOne = null;
                } else if (nodes[i].nextOneName.equals("W") && nodes[j].name.equals("EXIT")) {
                    nodes[i].nextOne = nodes[j];
                } else if (nodes[i].nextTwoName.equals("A")) {
                    nodes[i].nextTwo = null;
                } else if (nodes[i].nextTwoName.equals("W") && nodes[j].name.equals("EXIT")) {
                    nodes[i].nextTwo = nodes[j];
                }
            }
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
