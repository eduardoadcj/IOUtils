
package io.basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public abstract class BasicIOUtils {
    
    /**
     * Size of the buffer used on processes
     */
    public static final int BUFFER_SIZE = 8192;
    
    /**
     * For internal usage
     */
    private static int count = 0;
    
     /**
     * This method copy the input file to output file
     * @param from input file
     * @param to output file
     * @throws IOException
     */
    public static void copy(File from, File to) throws IOException {
        InputStream in = new FileInputStream(from);
        OutputStream out = new FileOutputStream(to);
        copy(in, out);
        in.close();
        out.close();
        to.setLastModified(from.lastModified());
    }

    /**
     * This method copy the content of input stream to output stream
     * @param from input stream
     * @param to output stream.
     * @throws IOException
     */
    public static void copy(InputStream from, OutputStream to) throws IOException {
        final int count = BUFFER_SIZE;
        byte[] bytes = new byte[count];
        for (int read = -1; (read = from.read(bytes, 0, count)) != -1; to.write(bytes, 0, read))
            ;
        to.flush();
    }
    
    public static int calcTotalFilesDirectory(File file){
        for(File f : file.listFiles()){
            if(f.isFile()){
                count++;
            }else{
                calcTotalFilesDirectory(f);
            }
        }
        return count;
    }
    
}
