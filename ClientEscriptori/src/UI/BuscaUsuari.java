/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import clientescriptori.Connection;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objectes.Usuari;

/**
 *
 * @author Gonzalo
 */
public class BuscaUsuari extends javax.swing.JPanel {

    /**
     * Creates new form BuscaUsuari
     */
    Connection connexio;
    private LoginScreen loginScreen;
    String[] arrayUsuari = {"", null, null, null, null, null, null};

    public BuscaUsuari() {
        initComponents();
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
        jLabelUsuari = new javax.swing.JLabel();
        jLabelRol = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelPrimerCognom = new javax.swing.JLabel();
        jLabelDataNaixement = new javax.swing.JLabel();
        jLabelSegonCognom = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldUsuari = new javax.swing.JTextField();
        jTextFieldDataNaixement = new javax.swing.JTextField();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrimerCognom = new javax.swing.JTextField();
        jTextFieldSegonCognom = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButtonEsborrarTot = new javax.swing.JButton();
        jComboBoxRol = new javax.swing.JComboBox<>();
        jButtonCrearUsuari = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        jLabelTitol.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabelTitol.setText("Trobar usuaris:");

        jLabelUsuari.setText("Usuari:");

        jLabelRol.setText("Rol:");

        jLabelNom.setText("Nom:");

        jLabelPrimerCognom.setText("Primer cognom:");

        jLabelDataNaixement.setText("Data naixement:");

        jLabelSegonCognom.setText("Segon cognom:");

        jLabelEmail.setText("Email:");

        jTextFieldPrimerCognom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrimerCognomActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonEsborrarTot.setText("Esborrar tot");
        jButtonEsborrarTot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEsborrarTotActionPerformed(evt);
            }
        });

        jComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "alumne", "bibliotecaria", "admin" }));
        jComboBoxRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRolActionPerformed(evt);
            }
        });

        jButtonCrearUsuari.setBackground(new java.awt.Color(102, 255, 0));
        jButtonCrearUsuari.setText("Crear usuari nou");
        jButtonCrearUsuari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearUsuariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitol)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNom))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDataNaixement)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDataNaixement, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPrimerCognom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPrimerCognom))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldEmail))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSegonCognom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSegonCognom))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelUsuari)
                                    .addComponent(jLabelRol))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldUsuari, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                    .addComponent(jComboBoxRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButtonEsborrarTot)
                .addGap(31, 31, 31)
                .addComponent(jButtonCrearUsuari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButtonBuscar)
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuari)
                    .addComponent(jTextFieldUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRol)
                    .addComponent(jComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataNaixement)
                    .addComponent(jTextFieldDataNaixement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNom)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrimerCognom)
                    .addComponent(jTextFieldPrimerCognom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSegonCognom)
                    .addComponent(jTextFieldSegonCognom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonEsborrarTot)
                    .addComponent(jButtonCrearUsuari))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEsborrarTotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEsborrarTotActionPerformed
        jTextFieldNom.setText("");
        jTextFieldPrimerCognom.setText("");
        jTextFieldSegonCognom.setText("");

        jTextFieldEmail.setText("");
        jTextFieldUsuari.setText("");
        jTextFieldDataNaixement.setText("");
    }//GEN-LAST:event_jButtonEsborrarTotActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        ArrayList<Usuari> usuaris = new ArrayList<>();

        arrayUsuari[0] = jTextFieldUsuari.getText();
        arrayUsuari[1] = jTextFieldNom.getText();
        arrayUsuari[2] = jTextFieldPrimerCognom.getText();
        arrayUsuari[3] = jTextFieldSegonCognom.getText();
        arrayUsuari[4] = (String) jComboBoxRol.getSelectedItem();
        arrayUsuari[5] = jTextFieldDataNaixement.getText();
        arrayUsuari[6] = jTextFieldEmail.getText();

        if (arrayUsuari[0].isEmpty() && arrayUsuari[1].isEmpty()
                && arrayUsuari[2].isEmpty() && arrayUsuari[3].isEmpty()
                && arrayUsuari[4].isEmpty() && arrayUsuari[5].isEmpty()
                && arrayUsuari[6].isEmpty()) {
            arrayUsuari[0] = "%";
        }

        try {
            usuaris = (ArrayList<Usuari>) Connection.buscarUsuaris(arrayUsuari);
        } catch (IOException ex) {
            Logger.getLogger(BuscaUsuari.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginScreen.showUsersList(usuaris, arrayUsuari);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jComboBoxRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRolActionPerformed

    private void jTextFieldPrimerCognomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrimerCognomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrimerCognomActionPerformed

    private void jButtonCrearUsuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearUsuariActionPerformed
        if (!jTextFieldUsuari.getText().isEmpty()) {

            try {
                //comprovem que l'usuari no existeixi a la bbdd
                String[] arrayNouUsuari = {jTextFieldUsuari.getText(), null, null, null, null, null, null};

                List<Usuari> llista = connexio.buscarUsuaris(arrayNouUsuari);
                boolean existeix = false;
                for(Usuari u : llista){
                    if(u.getNom().equals(arrayNouUsuari[0])) existeix=true;
                }
                if (!existeix) {
                    Usuari u = new Usuari(
                            jTextFieldUsuari.getText(),
                            "",
                            (String) jComboBoxRol.getSelectedItem(),
                            jTextFieldNom.getText(),
                            jTextFieldPrimerCognom.getText(),
                            jTextFieldSegonCognom.getText(),
                            jTextFieldEmail.getText()
                    );

                    IntrodueixNouPass introdueixNouPass = new IntrodueixNouPass(this, u);
                    introdueixNouPass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    introdueixNouPass.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(loginScreen, "L'usuari ja existeix.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(BuscaUsuari.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonCrearUsuariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCrearUsuari;
    private javax.swing.JButton jButtonEsborrarTot;
    private javax.swing.JComboBox<String> jComboBoxRol;
    private javax.swing.JLabel jLabelDataNaixement;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPrimerCognom;
    private javax.swing.JLabel jLabelRol;
    private javax.swing.JLabel jLabelSegonCognom;
    private javax.swing.JLabel jLabelTitol;
    private javax.swing.JLabel jLabelUsuari;
    private javax.swing.JTextField jTextFieldDataNaixement;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrimerCognom;
    private javax.swing.JTextField jTextFieldSegonCognom;
    private javax.swing.JTextField jTextFieldUsuari;
    // End of variables declaration//GEN-END:variables

    public void setConnexio(Connection con) {
        this.connexio = con;
    }

    public void setLoginScreen(LoginScreen login) {
        this.loginScreen = login;
    }

    void setText(String[] arrayUsuaris) {
        jTextFieldUsuari.setText(arrayUsuaris[0]);
        if (jTextFieldUsuari.getText().equals("%")) {
            jTextFieldUsuari.setText("");
        }
        if (arrayUsuaris[4].equals("Usuari")) {
            jComboBoxRol.setSelectedIndex(0);
        }
        if (arrayUsuaris[4].equals("Bibliotecari")) {
            jComboBoxRol.setSelectedIndex(1);
        }
        if (arrayUsuaris[4].equals("Admin")) {
            jComboBoxRol.setSelectedIndex(2);
        }
        jTextFieldDataNaixement.setText(arrayUsuaris[5]);
        jTextFieldNom.setText(arrayUsuaris[3]);
        jTextFieldPrimerCognom.setText(arrayUsuaris[2]);
        jTextFieldSegonCognom.setText(arrayUsuaris[3]);
        jTextFieldEmail.setText(arrayUsuaris[6]);

    }

}
