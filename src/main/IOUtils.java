
package main;

import io.zip.ZipManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IOUtils {

    public static void main(String[] args) {
        
        try {

            if(args[0].equals("zip")){
                ZipManager.zipFile(args[1], args[2]);
            }else if(args[0].equals("unzip")){
                ZipManager.unzipFile(args[1], args[2]);
            }else if(args[0].equals("help")){
                System.out.println("Suported functions: ");
                System.out.println("-- zip -> to zip an directory");
                System.out.println("-- unzip -> to unzip a file");
            }else{
                throw new IllegalArgumentException("Unsuported function");
            }
            
        }catch(Exception ex) {
            Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
