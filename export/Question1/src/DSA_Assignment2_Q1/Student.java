package DSA_Assignment2_Q1;

/**
 *
 * @author eirik
 */
public class Student<E extends Comparable> implements Comparable<Student> {

    public E key;
    public String name;
    public int mark;

    public Student(String name, int mark) {
        this.key = null;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public int compareTo(Student arg) {
        return this.key.compareTo(arg.key);
    }

    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Mark: " + mark;
    }

}
