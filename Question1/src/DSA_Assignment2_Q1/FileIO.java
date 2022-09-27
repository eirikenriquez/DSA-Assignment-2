package DSA_Assignment2_Q1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author eirik
 */
public class FileIO {

    public String readFile(File file) throws FileNotFoundException {
        String str = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                str += line;
                str += '\n';
                line = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }

        return str;
    }
}
