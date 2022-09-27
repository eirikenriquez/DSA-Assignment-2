package DSA_Assignment2_Q1;

/**
 * This is the binary tree class.Some of the methods in this class uses the name
 * "current" instead of "root" for recursion. This is to differentiate the
 * sub-roots from the main root.
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
     * This method calls a private method that uses recursion to find the Node
     * that contains the argument data.
     *
     * @param data This is a comparable object that is stored in a Node.
     * @return Returns the data object if it finds the Node. Otherwise, returns
     * null.
     */
    public E findNode(E data) {
        return findNode(data, root);
    }

    /**
     * This method recursively searches the binary tree and returns the data if
     * a Node contains the same data. Otherwise, returns null.
     *
     * @param toFind Comparable object that this method searches for.
     * @param current Node object that is the current key for traversing through
     * the binary tree.
     * @return
     */
    private E findNode(E toFind, Node current) {
        if (current == null) {
            return null;
        }

        if (current.data.compareTo(toFind) > 0) {
            return findNode(toFind, current.left);
        } else if (current.data.compareTo(toFind) < 0) {
            return findNode(toFind, current.right);
        } else {
            return toFind;
        }
    }

    /**
     * This method reverses the order of the tree with a time complexity of
     * O(n).
     */
    public void reverseOrder() {
        reverseOrder(root);
    }

    /**
     * This method uses recursion to reverse the order of the binary tree. This
     * method swaps the left and right nodes for each Node. The time complexity
     * is O(n) as it traverses through each Node only once.
     *
     * @param current
     */
    private void reverseOrder(Node current) {
        if (current == null) {
            return;
        }

        // swap
        Node temp = current.left;
        current.left = current.right;
        current.right = temp;

        reverseOrder(current.left);
        reverseOrder(current.right);

    }

    /**
     * This calls the private method to traverse and print the binary search
     * tree.
     */
    public void traversal() {
        traversal(root);
    }

    /**
     * This method uses recursion to traverse through the binary search tree and
     * prints the details of each Node. This traverses in-order:
     * left-root-right.
     *
     * @param current This is the current Node that is used as the key while
     * traversing through the tree.
     */
    private void traversal(Node current) {
        if (current.left != null) {
            traversal(current.left);
        }

        System.out.println(current.data);

        if (current.right != null) {
            traversal(current.right);
        }
    }

}
