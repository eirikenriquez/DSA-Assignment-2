package dsa.assignment.pkg2;

/**
 *
 * @author eirik
 */
public class BinaryTree<E extends Comparable> {

    public int size;
    private Node root;

    public BinaryTree() {
        this.size = 0;
        this.root = null;
    }

    /**
     * This method adds a new node to the binary tree. This method calls a
     * recursive private method if there is already a root Node. Otherwise, the
     * a new Node will be created with the data provided in the argument and
     * assigned as the root Node.
     *
     * @param data Comparable object that is stored in a Node.
     */
    public void add(E data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            add(newNode, root);
        }
    }

    /**
     * This method recursively traverses through the binary tree until there is
     * a spot to add a new Node.
     *
     * @param newNode Node object that is to be added to the binary tree.
     * @param current Node object that is the current key while traversing
     * through the binary tree.
     */
    private void add(Node newNode, Node current) {
        if (current.compareTo(newNode) > 0) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                add(newNode, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                add(newNode, current.right);
            }
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public E findNode(E data) {
        if (root.data.compareTo(data) == 0) {
            return data;
        } else {
            return findNode(data, root);
        }
    }

    private E findNode(E toFind, Node current) {
        if (current.data.compareTo(toFind) > 0) {
            findNode(toFind, current.left);
        } else if (current.data.compareTo(toFind) < 0) {

        } else {
            return toFind;
        }
    }

    /**
     *
     */
    public void reverseOrder() {

    }

    /**
     *
     */
    public void traversal() {

    }

}
