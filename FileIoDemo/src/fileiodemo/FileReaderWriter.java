/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileiodemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author xhu
 */
public class FileReaderWriter {
    
    public String name;
    public String[] lineData;
    public int lineNumber;
    
    public FileReaderWriter()
    {
        lineData = new String[1000];
    }
    
    public void readFile(String fileName)
    {
        File f = new File(fileName);        
        try
        {
            Scanner myScanner = new Scanner(f);
            lineNumber = 0;
            while(myScanner.hasNextLine())
            {
                String line = myScanner.nextLine();
                lineData[lineNumber] = line;
                lineNumber++;                
            }  
            myScanner.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot read the file "+e.getMessage());
        }
    }
    //System.getProperty("line.separator");
    public void writeFile(String fileName, String c)
    {
        File f = new File(fileName);
        
        try
        {
            f.createNewFile();
            FileWriter writer = new FileWriter(f); 
            writer.write(c);
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot write to the file"+e.getMessage());
        }
    }
}
