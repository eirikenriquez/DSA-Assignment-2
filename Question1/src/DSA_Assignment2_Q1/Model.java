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
     * This method reads a text file and returns a string containing the
     * contents of the file.
     *
     * @param file This is a text file.
     * @return The contents of the text file.
     * @throws FileNotFoundException
     */
    public String readFile(File file) throws FileNotFoundException {
        this.studentList = new BinaryTree<Student>();
        String str = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                str += line;
                addStudent(line);
                str += '\n';
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
            System.out.println("not add to tree");
            return;
        }

        String[] studentInfo = line.split(", ");

        // exit method if the line doesn't meet the format
        if (studentInfo.length != 2) {
            System.out.println("not add to tree");
            return;
        }

        String name = studentInfo[0];

        // check if mark is an integer.
        try {
            int mark = Integer.parseInt(studentInfo[1]);
            this.studentList.add(new Student(name, mark));
            System.out.println("added to tree");
        } catch (NumberFormatException ex) {
            System.out.println("not add to tree");
        }

    }

    public void writeFile(String toWrite, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
            bw.write(toWrite);
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERror: " + ex.toString());
        }
    }
}
