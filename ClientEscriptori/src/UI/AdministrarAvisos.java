/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import clientescriptori.Connection;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import objectes.Avis;

/**
 *
 * @author Gonzalo
 */
public class AdministrarAvisos extends javax.swing.JPanel {

    private Avis avis;
    private List<Avis> listaAvisos;
    private Connection connexio;

    /**
     * Creates new form ComentarisLlibre
     */
    public AdministrarAvisos() {
        initComponents();
    }

    public AdministrarAvisos(List<Avis> listaAvisos) throws ClassNotFoundException {
        initComponents();
        this.listaAvisos = listaAvisos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitol = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNouAvis = new javax.swing.JTextArea();
        jLabelEscriureAvis = new javax.swing.JLabel();
        jButtonEnviarAvis = new javax.swing.JButton();
        jLabelId = new javax.swing.JLabel();
        jTextFieldTitol = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIdUsuari = new javax.swing.JTextField();
        jButtonEsborrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));

        jLabelTitol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTitol.setText("Enviar avís:");

        jTextAreaNouAvis.setColumns(20);
        jTextAreaNouAvis.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNouAvis);

        jLabelEscriureAvis.setText("Escriure avís:");

        jButtonEnviarAvis.setText("Enviar avís");
        jButtonEnviarAvis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarAvisActionPerformed(evt);
            }
        });

        jLabelId.setText("ID d'usuari:");

        jLabel1.setText("Títol:");

        jButtonEsborrar.setText("Esborrar tot");
        jButtonEsborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEsborrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEscriureAvis)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTitol)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelId)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldIdUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 109, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButtonEsborrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEnviarAvis)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldIdUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabelEscriureAvis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEnviarAvis)
                    .addComponent(jButtonEsborrar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarAvisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarAvisActionPerformed
        String idUsuari = jTextFieldIdUsuari.getText();
        String titol = jTextFieldTitol.getText();
        String avisText = jTextAreaNouAvis.getText();

        String nomUsuari = connexio.buscaNomPerID(idUsuari);
        
        if(idUsuari.isBlank() || avisText.isBlank() || titol.isBlank()){
            JOptionPane.showMessageDialog(this, "Introdueixi un ID d'usuari i un avís.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!esNumero(idUsuari)){
            JOptionPane.showMessageDialog(this, "ID d'usuari invàlid. Ha de ser un número.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldIdUsuari.setText("");
        }else{
            int idUsuariInt = Integer.valueOf(idUsuari);
            Avis avis = new Avis(idUsuariInt, titol, avisText, new Date(), false, 0);
            try {
                connexio.creaAvis(avis);
                
            } catch (IOException ex) {
                Logger.getLogger(AdministrarAvisos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonEnviarAvisActionPerformed

    private void jButtonEsborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEsborrarActionPerformed
        jTextFieldIdUsuari.setText("");
        jTextFieldTitol.setText("");
        jTextAreaNouAvis.setText("");
    }//GEN-LAST:event_jButtonEsborrarActionPerformed


    public void setConnection(Connection con){
        this.connexio = con;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviarAvis;
    private javax.swing.JButton jButtonEsborrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelEscriureAvis;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelTitol;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNouAvis;
    private javax.swing.JTextField jTextFieldIdUsuari;
    private javax.swing.JTextField jTextFieldTitol;
    // End of variables declaration//GEN-END:variables

    
    private boolean esNumero(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
}
