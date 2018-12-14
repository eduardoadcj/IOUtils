package io.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class FilePicker extends javax.swing.JDialog {

    /**
     * Instruction to display only files.
     */
    public static final int FILES_ONLY = 0;

    /**
     * Instruction to display only directories.
     */
    public static final int DIRECTORIES_ONLY = 1;

    /**
     * Instruction to display both files and directories.
     */
    public static final int FILES_AND_DIRECTORIES = 2;

    private File selectedFile;

    private int fileSelectionMode;
    private File currentDirectory;
    private String filterByOneFileExtention;

    public FilePicker(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Toolkit thekit = this.getToolkit();
        Dimension dim = thekit.getScreenSize();
        int hor = (dim.width / 2) - 350;
        int ver = (dim.height / 2) - 250;
        this.setBounds(hor, ver, 750, 500);
        this.requestFocus();
        fileChooser.setMultiSelectionEnabled(false);
    }
    
    public FilePicker(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        Toolkit thekit = this.getToolkit();
        Dimension dim = thekit.getScreenSize();
        int hor = (dim.width / 2) - 350;
        int ver = (dim.height / 2) - 250;
        this.setBounds(hor, ver, 750, 500);
        this.requestFocus();
        fileChooser.setMultiSelectionEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser(){
            @Override
            public void approveSelection() {
                approve();
            }
            @Override
            public void cancelSelection() {
                cancel();
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FilePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilePicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FilePicker dialog = new FilePicker(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void approve() {
        selectedFile = fileChooser.getSelectedFile();
        if(selectedFile != null){
            if(filterByOneFileExtention != null && selectedFile.isFile()){
                if(selectedFile.getPath().endsWith(filterByOneFileExtention)){
                    this.dispose();
                }
            }else{
                this.dispose();
            }
        }
    }

    private void cancel() {
        selectedFile = null;
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public int getFileSelectionMode() {
        return fileSelectionMode;
    }

    public void setFileSelectionMode(int fileSelectionMode) {
        this.fileSelectionMode = fileSelectionMode;
        fileChooser.setFileSelectionMode(fileSelectionMode);
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
        fileChooser.setCurrentDirectory(currentDirectory);
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = new File(currentDirectory);
        fileChooser.setCurrentDirectory(this.currentDirectory);
    }

    public String getFilterByOneFileExtention() {
        return filterByOneFileExtention;
    }

    public void setFilterByOneFileExtention(String filterByOneFileExtention) {
        this.filterByOneFileExtention = filterByOneFileExtention;
        EasyFileFilter eff = new EasyFileFilter();
        eff.setExtention(filterByOneFileExtention);
        fileChooser.setFileFilter(eff);
    }
    
}
