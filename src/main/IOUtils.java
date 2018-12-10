
package main;

import io.zip.ZipManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOUtils {

    public static void main(String[] args) {
        
        try {
            new ZipManager().zipFile(args[0], args[1]);
        } catch (IOException ex) {
            Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
