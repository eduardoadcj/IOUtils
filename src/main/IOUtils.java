
package main;

import io.zip.ZipManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOUtils {

    public static void main(String[] args) {
        
        try {
            HashMap<String, String> atributos = new HashMap();
            atributos.put("database-version", "4.5");
            atributos.put("type", "backup-archive");
            atributos.put("software", "myContab");
            new ZipManager().zipFile("data", "arquivo.zip", atributos);
        
            
            Path p = new File("arquivo.zip").toPath();
            
            final Charset utf8 = StandardCharsets.UTF_8;
            
            byte[] attr = (byte[]) Files.readAttributes(p, "user:database-version").get("database-version");
            String atributo = new String(attr, utf8);
            System.out.println(atributo);
            
            
        } catch (IOException ex) {
            Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
