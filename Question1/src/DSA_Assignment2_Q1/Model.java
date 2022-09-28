package DSA_Assignment2_Q1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author eirik
 */
public class Model {

    private BinaryTree nameList;
    private BinaryTree markList;

    /**
     * This method reads a text file and populates the binary tree.
     *
     * @param file This is a text file.
     * @return Returns a String object that contains the text contents.
     * @throws FileNotFoundException
     */
    public String readFile(File file) throws FileNotFoundException {
        this.nameList = new BinaryTree<Student>();
        this.markList = new BinaryTree<Student>();

        String str = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                str += line + '\n';
                addStudent(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }

        return str;
    }

    /**
     * This method adds a student to the binary tree depending on the line read
     * by the readFile method.
     *
     * @param line This is the current line being read from the file.
     */
    private void addStudent(String line) {
        // exit method if the line doesn't meet the format
        if (!line.contains(", ")) {
            return;
        }

        String[] studentInfo = line.split(", ", 2);
        String name = studentInfo[0];

        // check if mark is an integer and add student into the binary trees.
        try {
            int mark = Integer.parseInt(studentInfo[1]);

            Student nameAsKey = new Student(name, mark);
            nameAsKey.key = nameAsKey.name;
            this.nameList.add(nameAsKey);

            Student markAsKey = new Student(name, mark);
            markAsKey.key = markAsKey.mark;
            this.markList.add(markAsKey);

        } catch (NumberFormatException ex) {
            System.out.println("Error: " + ex.toString());
        }

    }

    public void writeFile(String toWrite, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
            bw.write(toWrite);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }

    public String getSelectedList(String sortOption) {
        if (sortOption.equals(SortOption.NAME.nameOfKey)) {
            return getSelectedList(nameList);
        } else if (sortOption.equals(SortOption.MARK.nameOfKey)) {
            return getSelectedList(markList);
        }

        return null;
    }

    private String getSelectedList(BinaryTree<Student> studentList) {
        // error handling
        if (studentList == null || studentList.size <= 0) {
            return "Error: Invalid Students List File!";
        }
        studentList.traversal();
        return studentList.fileText;
    }

    /**
     * This reverses the order of the trees.
     */
    public void sort() {
        if (nameList != null && markList != null) {
            this.nameList.reverseOrder();
            this.markList.reverseOrder();
        }
    }

    public String search(String searchQuery, String sortOption) {
        if (nameList == null && markList == null) {
            return null;
        }

        try {
            if (sortOption.equals(SortOption.NAME.nameOfKey)) {
                Student toFind = new Student(searchQuery, 0); // mark doesn't matter as it isn't the key..
                toFind.key = toFind.name;
                Student found = (Student) nameList.findNode(toFind);
                return found.toString();
            } else if (sortOption.equals(SortOption.MARK.nameOfKey)) {
                Student toFind = new Student("", Integer.parseInt(searchQuery));
                toFind.key = toFind.mark;
                Student found = (Student) markList.findNode(toFind);
                return found.toString();
            }
        } catch (NullPointerException | NumberFormatException ex) {
            return "Not found";
        }

        return null;
    }
}
