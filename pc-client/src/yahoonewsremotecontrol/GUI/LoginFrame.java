/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol.GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import yahoonewsremotecontrol.net.ServerConnector;

/**
 *
 * @author CCFLAB
 */
public class LoginFrame extends javax.swing.JFrame {

    ServerConnector sc = new ServerConnector();
    
    public ServerConnector getServerConnector(){
        //该函数会被阻塞
        while(!sc.isConnected){
            try{
                Thread.sleep(200);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, e.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        return sc;
    }
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        this.setTitle("Yahoo News Client Login");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;

        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelIP = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jTextFieldIP = new javax.swing.JTextField();
        jTextFieldPort = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jLabelPort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelIP.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jLabelIP.setText("IP:");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yahoonewsremotecontrol/GUI/yahooNews.jpg"))); // NOI18N

        jTextFieldIP.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jTextFieldIP.setText("192.168.137.1");

        jTextFieldPort.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jTextFieldPort.setText("10001");

        jButtonConnect.setFont(new java.awt.Font("微软雅黑", 0, 24)); // NOI18N
        jButtonConnect.setText("Connect");
        jButtonConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonConnectMouseClicked(evt);
            }
        });

        jLabelPort.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jLabelPort.setText("Port:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPort)
                            .addComponent(jLabelIP))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPort)
                            .addComponent(jTextFieldIP)))
                    .addComponent(jButtonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelIP)
                            .addComponent(jTextFieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPort))
                        .addGap(95, 95, 95)
                        .addComponent(jButtonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelLogo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonConnectMouseClicked
        // TODO add your handling code here:
         if(!sc.connect(jTextFieldIP.getText(), Integer.valueOf(jTextFieldPort.getText()))){
            JOptionPane.showMessageDialog(this, "Cann't connect to server!", "ERROR", JOptionPane.WARNING_MESSAGE);
            //m.dispose();
            return;
        }
    }//GEN-LAST:event_jButtonConnectMouseClicked

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JLabel jLabelIP;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JTextField jTextFieldIP;
    private javax.swing.JTextField jTextFieldPort;
    // End of variables declaration//GEN-END:variables
}