/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol.GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author CCFLAB
 */
public class FigureShower extends javax.swing.JFrame {
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    double largerScale(int weight, int height){
        double screenProportion = screenSize.getWidth() / screenSize.getHeight();
        double figureProportion = (double)weight / height;
        
        if(screenProportion > figureProportion){
            return screenSize.getHeight() / height;
        }
        return screenSize.getWidth() / weight;
    }
    
    public void showFigure(ImageIcon figure) {
        //this.getContentPane().add(jLabelFigure, BorderLayout.CENTER);
        setTitle("Yahoo News!");
        
        int width = figure.getIconWidth();
        int height = figure.getIconHeight();
        double scale = largerScale(width, height) / 1.1;
        System.out.println(width+", "+ height);
        
        width = (int)(width * scale);
        height = (int)(height * scale);        
        System.out.println(width+", "+ height);
        
        
        setSize(width, height);
        //setSize(500, 1000);
        //this.setExtendedState(JFrame.MAXIMIZED_VERT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
        jLabelFigure.setIcon(new ScaleIcon(figure));
        Center();
        //frame.setVisible(true);
    }
  
    /**
     * Creates new form FigureShower
     */
    void Center(){
        Dimension frameSize = this.getSize();

        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;

        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    public FigureShower() {
        initComponents();
        Center();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelFigure = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelFigure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFigure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yahoonewsremotecontrol/GUI/default.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFigure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFigure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabelFigure.getAccessibleContext().setAccessibleDescription("");

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
            java.util.logging.Logger.getLogger(FigureShower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FigureShower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FigureShower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FigureShower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new FigureShower().setVisible(true); 
               FigureShower m = new FigureShower();
               m.setVisible(true);
               m.showFigure(new ImageIcon("C:\\Desert.jpg"));
               //m.showFigure(new ImageIcon("temp\\xiaohua.jpg"));
               //m.showFigure(new ImageIcon("C:\\2.jpg"));
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelFigure;
    // End of variables declaration//GEN-END:variables
}