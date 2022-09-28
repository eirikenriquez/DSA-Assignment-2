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

    public BinaryTree studentList;

    /**
     * This method reads a text file and populates the binary tree.
     *
     * @param file This is a text file.
     * @throws FileNotFoundException
     */
    public void readFile(File file) throws FileNotFoundException {
        this.studentList = new BinaryTree<Student>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                addStudent(line);
                line = br.readLine();
            }

            br.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
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

        // check if mark is an integer and add student into the binary tree.
        try {
            int mark = Integer.parseInt(studentInfo[1]);
            Student student = new Student(name, mark);
            studentList.add(student);
        } catch (NumberFormatException ex) {
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

    public String getStudentListString() {
        // error handling
        if (studentList.size <= 0) {
            return "Error: Invalid Students List File!";
        }
        this.studentList.traversal();
        return studentList.treeString;
    }

    /**
     * This reverses the binary tree and updates the file.
     */
    public void sort(String fileName) {

    }
}
