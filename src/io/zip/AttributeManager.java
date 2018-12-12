package io.zip;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class AttributeManager {

    public static final Charset utf8 = StandardCharsets.UTF_8;
    
    /**
     * This method add attributes for an specific file
     *
     * @param file target
     * @param attrs list of attributes
     * @return file with changes
     * @throws IOException
     */
    public static File defineAtributes(File file, HashMap<String, String> attrs) throws IOException {

        for (Map.Entry<String, String> pair : attrs.entrySet()) {
            Files.setAttribute(file.toPath(),
                    "user:" + pair.getKey(), pair.getValue().getBytes(utf8));
        }

        return file;

    }

    /**
     * This method add attributes for an specific file
     *
     * @param file the path of the target
     * @param attrs list of attributes
     * @return file with changes
     * @throws IOException
     */
    public static File defineAtributes(String filePath, HashMap<String, String> attrs) throws IOException {
        return defineAtributes(new File(filePath), attrs);
    }

    /**
     * This method returns value of an attribute of a file 
     * @param file file target
     * @param attr attribute required
     * @return String with the value
     * @throws IOException
     */
    public static String getAtribute(File file, String attr) throws IOException {
        byte[] value = (byte[]) Files.readAttributes(file.toPath(), "user:"+attr).get(attr);
        return new String(value, utf8);
    }
    
    /**
     * This method returns value of an attribute of a file 
     * @param file file target
     * @param attr attribute required
     * @return String with the value
     * @throws IOException
     */
    public static String getAtribute(String filePath, String attr) throws IOException {
        return getAtribute(new File(filePath), attr);
    }

}
