package suport.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import objectes.Usuari;

public class CrearUsuariFrame extends JFrame implements ActionListener {
    private JTextField idField, userField, passField, rolField, dataNaixementField, nomField, primerCognomField, segonCognomField, emailField, dataAltaField, dataBaixaField, multaField, suspensioField, dataFinalSuspensioField, ultimaActualitzacioField;
    private JLabel idLabel, userLabel, passLabel, rolLabel, dataNaixementLabel, nomLabel, primerCognomLabel, segonCognomLabel, emailLabel, dataAltaLabel, dataBaixaLabel, multaLabel, suspensioLabel, dataFinalSuspensioLabel, ultimaActualitzacioLabel, fotoLabel;
    private JButton crearButton, cancelarButton;
    private JFileChooser fotoChooser;

    public CrearUsuariFrame() {
        super("Crear usuari");

        idField = new JTextField(10);
        userField = new JTextField(10);
        passField = new JTextField(10);
        rolField = new JTextField(10);
        dataNaixementField = new JTextField(10);
        nomField = new JTextField(10);
        primerCognomField = new JTextField(10);
        segonCognomField = new JTextField(10);
        emailField = new JTextField(10);
        dataAltaField = new JTextField(10);
        dataBaixaField = new JTextField(10);
        multaField = new JTextField(10);
        suspensioField = new JTextField(10);
        dataFinalSuspensioField = new JTextField(10);
        ultimaActualitzacioField = new JTextField(10);
        fotoChooser = new JFileChooser();

        idLabel = new JLabel("ID:");
        userLabel = new JLabel("Nom d'usuari:");
        passLabel = new JLabel("Contrasenya:");
        rolLabel = new JLabel("Rol:");
        dataNaixementLabel = new JLabel("Data de naixement:");
        nomLabel = new JLabel("Nom:");
        primerCognomLabel = new JLabel("Primer cognom:");
        segonCognomLabel = new JLabel("Segon cognom:");
        emailLabel = new JLabel("Correu electrònic:");
        dataAltaLabel = new JLabel("Data d'alta:");
        dataBaixaLabel = new JLabel("Data de baixa:");
        multaLabel = new JLabel("Multa:");
        suspensioLabel = new JLabel("Suspensió:");
        dataFinalSuspensioLabel = new JLabel("Data final de suspensió:");
        ultimaActualitzacioLabel = new JLabel("Última actualització:");
        fotoLabel = new JLabel("Foto:");

        crearButton = new JButton("Crear");
        crearButton.addActionListener(this);
        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(17, 2));
        panel.add(idLabel);
        panel.add(idField);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(rolLabel);
        panel.add(rolField);
        panel.add(dataNaixementLabel);
        panel.add(dataNaixementField);
        panel.add(nomLabel);
        panel.add(nomField);
        panel.add(primerCognomLabel);
        panel.add(primerCognomField);
        panel.add(segonCognomLabel);
        panel.add(segonCognomField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(dataAltaLabel);
        panel.add(dataAltaField);
        panel.add(dataBaixaLabel);
        panel.add(dataBaixaField);
        panel.add(multaLabel);
        panel.add(multaField);
        panel.add(suspensioLabel);
        panel.add(suspensioField);
        panel.add(dataFinalSuspensioLabel);
        panel.add(dataFinalSuspensioField);
        panel.add(ultimaActualitzacioLabel);
        panel.add(ultimaActualitzacioField);
        panel.add(fotoLabel);
        panel.add(fotoChooser);

        panel.add(crearButton);
        panel.add(cancelarButton);

        add(panel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    }

    @Override    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crearButton) {
            // Recuperem els valors dels camps
            int id = Integer.parseInt(idField.getText());
            String user = userField.getText();
            String pass = passField.getText();
            String rol = rolField.getText();
            Date dataNaixement = null;
            // Convertim la data a tipus Date
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dataNaixement = formatter.parse(dataNaixementField.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de data incorrecte", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nom = nomField.getText();
            String primerCognom = primerCognomField.getText();
            String segonCognom = segonCognomField.getText();
            String email = emailField.getText();
            Date dataAlta = null;
            // Convertim la data d'alta a tipus Date
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dataAlta = formatter.parse(dataAltaField.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de data incorrecte", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date dataBaixa = null;
            // Convertim la data de baixa a tipus Date
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dataBaixa = formatter.parse(dataBaixaField.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de data incorrecte", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double multa = Double.parseDouble(multaField.getText());
            boolean suspensio = Boolean.parseBoolean(suspensioField.getText());
            Date dataFinalSuspensio = null;
            // Convertim la data final de suspensió a tipus Date
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dataFinalSuspensio = formatter.parse(dataFinalSuspensioField.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de data incorrecte", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long ultimaActualitzacio = Long.parseLong(ultimaActualitzacioField.getText());
            byte[] imageData = null;
            // Llegim la imatge del fitxer seleccionat
            int result = fotoChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fotoChooser.getSelectedFile();
                try {
                    imageData = Files.readAllBytes(file.toPath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error en llegir el fitxer de la imatge", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // Creem l'usuari amb les dades proporcionades
            Usuari usuari = new Usuari(id, user, rol, dataNaixement, nom, primerCognom, segonCognom, email, dataAlta, dataBaixa, multa, suspensio, dataFinalSuspensio, ultimaActualitzacio, imageData);
            // Afegim l'usuari a la base de dades

                // Aquí aniria el codi per afegir l'usuari a la base de dades
                // ...
                //JOptionPane.showMessageDialog(this, "Usuari creat correctament",);

                
            
        }          
    }
}

