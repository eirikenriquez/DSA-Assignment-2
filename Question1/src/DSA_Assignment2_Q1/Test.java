package DSA_Assignment2_Q1;

/**
 * This class is only used to test the binary tree.
 *
 * @author eirik
 */
public class Test {

    public static void main(String[] args) {
        BinaryTree<Student> tree = new BinaryTree<>();

        Student s1 = new Student("bob", 1);
        Student s2 = new Student("jimbo", 2);
        Student s3 = new Student("julian", 3);
        Student s4 = new Student("sam", 4);
        Student s5 = new Student("prince", 5);

        s1.key = s1.mark;
        s2.key = s2.mark;
        s3.key = s3.mark;
        s4.key = s4.mark;
        s5.key = s5.mark;

        tree.add(s4);           //                   4
        tree.add(s1);           //           1           5
        tree.add(s3);           //                3
        tree.add(s2);           //             2
        tree.add(s5);           //

        tree.traversal();

        tree.reverseOrder();
        System.out.println("");
        tree.traversal();

    }
}
