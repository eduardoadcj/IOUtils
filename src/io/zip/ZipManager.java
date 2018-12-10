
package io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOException;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class ZipManager {
    
    private static int BUFFER_SIZE = 1024;
    
    /**
     * This method compress any file or directory on Zip format
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @throws IOException 
     */
    public static void zipFile(String inputDirectory, String outputDirectory) throws IOException{
        
        String internalDir = "";
        File file = new File(inputDirectory);
        if(!file.exists()){
            throw new IIOException("file not found");
        }
        
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(outputDirectory)));
        
        if(file.isFile()){
            zip(file, outputDirectory, zipOutputStream);
        }else{
            internalDir = file.getName();
            File[] files = file.listFiles();
            for(File f : files){
                zip(f, internalDir, zipOutputStream);
            }
        }
        zipOutputStream.close();
        
    }
    
    /**
     * This method compress any file or directory on Zip format
     * @param inputDirectory directory or file that will be compressed
     * @param outputDirectory directory where the compressed file will be saved
     * @param attributes list of attributes to add on final archive
     * @throws IOException 
     */
    public static void zipFile(String inputDirectory, String outputDirectory, HashMap<String,String> attributes) throws IOException{
        
        String internalDir = "";
        File file = new File(inputDirectory);
        if(!file.exists()){
            throw new IIOException("file not found");
        }
        
        File outputFile = new File(outputDirectory);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));
        
        if(file.isFile()){
            zip(file, outputDirectory, zipOutputStream);
        }else{
            internalDir = file.getName();
            File[] files = file.listFiles();
            for(File f : files){
                zip(f, internalDir, zipOutputStream);
            }
        }
        zipOutputStream.close();
        AttributeDefiner.defineAtributes(outputFile, attributes);
        
    }
    
    private static void zip(File file, String outputDirectory, ZipOutputStream zipOutputStream) throws IOException{
        
        byte data[] = new byte[BUFFER_SIZE];
        
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                zip(f, outputDirectory + File.separator + file.getName(), zipOutputStream);
            }
        }else{
            
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            ZipEntry entry = new ZipEntry(outputDirectory + File.separator + file.getName());
            zipOutputStream.putNextEntry(entry);
            
            int count;
            while((count = fileInputStream.read(data)) > 0){
                zipOutputStream.write(data, 0, count);
            }
            
            zipOutputStream.closeEntry();
            fileInputStream.close();
            
        }
        
    }

    
}
