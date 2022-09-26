package dsa.assignment.pkg2;

/**
 *
 * @author eirik
 */
public class Node<E extends Comparable> implements Comparable<Node> {

    public E data;
    public Node left;
    public Node right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(Node arg) {
        return this.data.compareTo(arg.data);
    }

}
