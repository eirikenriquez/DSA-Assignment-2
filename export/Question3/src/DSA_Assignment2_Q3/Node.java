package DSA_Assignment2_Q3;

/**
 *
 * @author eirik
 */
public class Node {

    public String name;
    public int x;
    public int y;
    public Node nextOne;
    public Node nextTwo;
    public String nextOneName;
    public String nextTwoName;
    public boolean drawnNode;
    public boolean visited;

    public Node(String name, int x, int y, String nextOneName, String nextTwoName) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.nextOneName = nextOneName;
        this.nextTwoName = nextTwoName;
        this.drawnNode = false;
        this.visited = false;
    }

    @Override
    public String toString() {
        return "Node{" + "name=" + name
                + ", x=" + x
                + ", y=" + y
                + ", nextOne=" + nextOneName + ", nextTwo=" + nextTwoName + '}';
    }

}
