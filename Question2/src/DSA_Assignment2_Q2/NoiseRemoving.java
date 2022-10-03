package DSA_Assignment2_Q2;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author xhu
 */
public class NoiseRemoving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser imageFileChooser = new JFileChooser(new File("."));
        int stateImageFileChooser = imageFileChooser.showOpenDialog(null);

        if (stateImageFileChooser == JFileChooser.APPROVE_OPTION) {
            String fileName = imageFileChooser.getSelectedFile().getPath();
            ImageProcess ip = new ImageProcess(fileName);
            ip.cleanNoise();
            ip.save("noise_removed.jpg");
        }
    }

}
