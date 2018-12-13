
package io.ui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class EasyFileFilter extends FileFilter{
    
    private String extention;

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getPath().endsWith(extention);
    }

    @Override
    public String getDescription() {
        return extention;
    }
    
}
