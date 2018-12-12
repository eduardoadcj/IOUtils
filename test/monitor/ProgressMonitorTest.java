
package monitor;

import monitor.utils.ProgressMonitor;
import io.zip.ZipManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.ui.ProgressInterface;

/**
 *
 * @author eduar
 */
public class ProgressMonitorTest {

    public static void main(String[] args) {
        unzipTest();
    }
    
    public static void unzipTest(){
        
        long initTime = System.currentTimeMillis();
        final ProgressMonitor pm = new ProgressMonitor();
        
        new Thread(){
            @Override
            public void run() {
                ProgressInterface pi = new ProgressInterface(null, false);
                pi.setProgressMonitor(pm);
                pi.setTitle("Descompactando arquivos...");
                pi.setVisible(true);
                pi.start();
            }
        }.start();
        
        try{
            ZipManager.unzipFile("C:\\Users\\eduar\\Documents\\NetBeansProjects\\IOUtils\\arquivo.zip", 
                    "C:\\Users\\eduar\\Desktop", pm);
        }catch(Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        
        System.out.println((double) (endTime-initTime)/1000);
        
    }
    
    public static void zipTest(){
        
        final ProgressMonitor pm = new ProgressMonitor();

        new Thread() {
            @Override
            public void run() {
                ProgressInterface pi = new ProgressInterface(null,false);
                pi.setProgressMonitor(pm);
                pi.setVisible(true);
                pi.setTitle("Compactando arquivos...");
                pi.start();
            }

        }.start();
        
        HashMap<String, String> attr = new HashMap();
        attr.put("database-version", "4.5");

        try {
            ZipManager.zipFile("data", "arquivo.zip", attr, pm);
        } catch (IOException ex) {
            Logger.getLogger(ProgressMonitorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
