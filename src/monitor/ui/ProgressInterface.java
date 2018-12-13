package monitor.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import monitor.utils.ProgressMonitor;

/**
 *
 * @author Eduardo A. Cruz Junior
 */
public class ProgressInterface extends javax.swing.JDialog {

    private ProgressMonitor progressMonitor;
    private String title;
    
    private int limitDisplayedInfoCharacters = 32;

    private boolean estimating = false;

    public ProgressMonitor getProgressMonitor() {
        return progressMonitor;
    }

    public void setProgressMonitor(ProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        labelTitle.setText(title);
    }

    public int getLimitDisplayedInfoCharacters() {
        return limitDisplayedInfoCharacters;
    }

    public void setLimitDisplayedInfoCharacters(int limitDisplayedInfoCharacters) {
        this.limitDisplayedInfoCharacters = limitDisplayedInfoCharacters;
    }
    
    public boolean isEstimating() {
        return estimating;
    }

    public void setEstimating(boolean estimating) {
        this.estimating = estimating;
    }

    public ProgressInterface(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Toolkit thekit = this.getToolkit();
        Dimension dim = thekit.getScreenSize();
        int hor = (dim.width / 2) - 150;
        int ver = (dim.height / 2) - 100;
        this.setBounds(hor, ver, 301, 151);
        this.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        labelInformationText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelTitle.setText("Title Information");

        progressBar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        progressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        progressBar.setStringPainted(true);

        labelInformationText.setText("Information Text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelInformationText)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInformationText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgressInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgressInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgressInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgressInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProgressInterface dialog = new ProgressInterface(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelInformationText;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    public void start() {
        while (progressMonitor.getProgress() <= 100.d) {
            update();
            if (progressMonitor.getProgress() == 100.d) {
                update();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        this.dispose();
    }

    private void update() {
        if (progressMonitor.isEstimating()) {
            progressBar.setIndeterminate(true);
            progressBar.setStringPainted(false);
            labelInformationText.setText(reduceChareacters(
                    progressMonitor.getProgressMessage()));
        } else {
            progressBar.setIndeterminate(false);
            progressBar.setStringPainted(true);
            labelInformationText.setText(reduceChareacters(
                    progressMonitor.getProgressMessage()));
            progressBar.setValue((int) progressMonitor.getProgress());
        }
    }

    private String reduceChareacters(String input){
        if(input == null){
            return "";
        }else if(input.isEmpty()){
            return "";
        }else if(input.length() <= limitDisplayedInfoCharacters){
            return input;
        }
        return input.substring(0,limitDisplayedInfoCharacters) + "...";
    }
    
}
