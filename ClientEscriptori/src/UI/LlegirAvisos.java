/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import clientescriptori.Connection;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Avis;

/**
 *
 * @author Gonzalo
 */
public class LlegirAvisos extends javax.swing.JFrame {

    String idUsuari;
    List<Avis> listaAvisos;
    int quantitatAvisos, avisActual;
    LoginScreen loginScreen;
    private Connection connexio;

    /**
     * Creates new form LlegirAvisos
     */
    public LlegirAvisos() {
        initComponents();
    }

    public LlegirAvisos(List<Avis> listaAvisos, String idUsuari, LoginScreen loginScreen, Connection connexio) throws IOException {
        initComponents();
        this.listaAvisos = listaAvisos;
        this.idUsuari = idUsuari;
        this.loginScreen = loginScreen;
        this.connexio = connexio;
        avisActual = 1;
        quantitatAvisos = listaAvisos.size();
        jLabelCuenta.setText("1 / " + quantitatAvisos);

        jButtonAnterior.setEnabled(false);

        jLabelNomUsuari.setText("Avisos del usuari: " + idUsuari);
        jLabelTitolText.setText(listaAvisos.get(avisActual-1).getTitol());
        jTextAreaMissatge.setText(listaAvisos.get(avisActual-1).getMissatge());
        loginScreen.restaJLabelAvisos();
        connexio.notificarAvisLlegit(listaAvisos.get(avisActual-1));
        

        if (quantitatAvisos == 1) {
            jButtonSeguent.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelNomUsuari = new javax.swing.JLabel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonSeguent = new javax.swing.JButton();
        jLabelCuenta = new javax.swing.JLabel();
        jLabelTitol = new javax.swing.JLabel();
        jLabelTitolText = new javax.swing.JLabel();
        jLabelMissatge = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMissatge = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabelNomUsuari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNomUsuari.setText("Avisos de l'usuari: ?");

        jButtonAnterior.setText("<-");
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });

        jButtonSeguent.setText("->");
        jButtonSeguent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeguentActionPerformed(evt);
            }
        });

        jLabelCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelCuenta.setText("?/?");

        jLabelTitol.setText("Títol:");

        jLabelTitolText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTitolText.setText("jLabel3");

        jLabelMissatge.setText("Missatge:");

        jTextAreaMissatge.setEditable(false);
        jTextAreaMissatge.setBackground(new java.awt.Color(255, 255, 255));
        jTextAreaMissatge.setColumns(20);
        jTextAreaMissatge.setLineWrap(true);
        jTextAreaMissatge.setRows(5);
        jTextAreaMissatge.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaMissatge);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabelCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSeguent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelNomUsuari))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMissatge)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelTitol)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTitolText))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomUsuari)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitol)
                    .addComponent(jLabelTitolText))
                .addGap(18, 18, 18)
                .addComponent(jLabelMissatge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeguent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCuenta))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSeguentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeguentActionPerformed
        avisActual++;
        System.out.println("avis actual: " + avisActual + " de " + quantitatAvisos);
        Avis avis = listaAvisos.get(avisActual-1);
        if (avisActual > quantitatAvisos) {
            jButtonSeguent.setEnabled(false);
        }
        jLabelCuenta.setText(avisActual + " / " + quantitatAvisos);
        jButtonAnterior.setEnabled(true);
        jLabelTitolText.setText(avis.getTitol());
        jTextAreaMissatge.setText(avis.getMissatge());
        try {
            connexio.notificarAvisLlegit(listaAvisos.get(avisActual-1));
            loginScreen.restaJLabelAvisos();
        } catch (IOException ex) {
            Logger.getLogger(LlegirAvisos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonSeguentActionPerformed

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        if(avisActual>1){
            avisActual--;
        }else{
            avisActual=1;
        }
        System.out.println("avis actual: " + avisActual + " de " + quantitatAvisos);
        Avis avis = listaAvisos.get(avisActual-1);
        if (avisActual > quantitatAvisos) {
            jButtonSeguent.setEnabled(false);
        }
        jLabelCuenta.setText(avisActual + " / " + quantitatAvisos);
        jButtonAnterior.setEnabled(true);
        jLabelTitolText.setText(avis.getTitol());
        jLabelMissatge.setText(avis.getMissatge());
        try {
            connexio.notificarAvisLlegit(listaAvisos.get(avisActual-1));
            loginScreen.restaJLabelAvisos();
        } catch (IOException ex) {
            Logger.getLogger(LlegirAvisos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAnteriorActionPerformed

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
            java.util.logging.Logger.getLogger(LlegirAvisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LlegirAvisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LlegirAvisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LlegirAvisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LlegirAvisos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonSeguent;
    private javax.swing.JLabel jLabelCuenta;
    private javax.swing.JLabel jLabelMissatge;
    private javax.swing.JLabel jLabelNomUsuari;
    private javax.swing.JLabel jLabelTitol;
    private javax.swing.JLabel jLabelTitolText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMissatge;
    // End of variables declaration//GEN-END:variables
}