package suport.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import objectes.Eines;
import objectes.Usuari;

public class UsuariProfileFrame extends JFrame {
    Eines eines = new Eines();
    private JLabel nameLabel, surnameLabel, emailLabel, dobLabel, roleLabel, idLabel, finesLabel, suspensionLabel, suspensionEndLabel, imageLabel;
    private JTextField nameField, surnameField, emailField, dobField, roleField, idField, finesField, suspensionField, suspensionEndField;

    public UsuariProfileFrame(Usuari user) {
        
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getImageData());
            Image imatge = ImageIO.read(inputStream);
            // set window title
            setTitle("User Profile");
            // create labels and fields for user info
            nameLabel = new JLabel("Name:");
            nameField = new JTextField(user.getNom());
            surnameLabel = new JLabel("Surname:");
            surnameField = new JTextField(user.getPrimerCognom() + " " + user.getSegonCognom());
            emailLabel = new JLabel("Email:");
            emailField = new JTextField(user.getEmail());
            dobLabel = new JLabel("Date of Birth:");
            dobField = new JTextField(eines.convertirStringData(user.getDataNaixement()));
            roleLabel = new JLabel("Role:");
            roleField = new JTextField(user.getRol());
            idLabel = new JLabel("ID:");
            idField = new JTextField(Integer.toString(user.getId()));
            finesLabel = new JLabel("Fines:");
            finesField = new JTextField(Double.toString(user.getMulta()));
            suspensionLabel = new JLabel("Suspension:");
            suspensionField = new JTextField(Boolean.toString(user.isSuspensio()));
            suspensionEndLabel = new JLabel("Suspension End Date:");
            suspensionEndField = new JTextField(eines.convertirStringData(user.getDataFinalSuspensio()));
            imageLabel = new JLabel(new ImageIcon(imatge.getScaledInstance(50, 50,Image.SCALE_SMOOTH)));
            
            // disable text fields to prevent user input
            nameField.setEditable(false);
            surnameField.setEditable(false);
            emailField.setEditable(false);
            dobField.setEditable(false);
            roleField.setEditable(false);
            idField.setEditable(false);
            finesField.setEditable(false);
            suspensionField.setEditable(false);
            suspensionEndField.setEditable(false);
            // create panel and add components to it
            JPanel panel = new JPanel(new GridLayout(9, 1));
            //panel.add(nameLabel);
            panel.add(imageLabel);
            panel.add(nameField);
            panel.add(surnameLabel);
            panel.add(surnameField);
            panel.add(emailLabel);
            panel.add(emailField);
            panel.add(dobLabel);
            panel.add(dobField);
            panel.add(roleLabel);
            panel.add(roleField);
            panel.add(idLabel);
            panel.add(idField);
            panel.add(finesLabel);
            panel.add(finesField);
            panel.add(suspensionLabel);
            panel.add(suspensionField);
            panel.add(suspensionEndLabel);
            panel.add(suspensionEndField);
            //panel.add(imageLabel);
            
            // add panel to frame and set window properties
            add(panel);
            setSize(400, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
            
        } catch (ParseException ex) {
            Logger.getLogger(UsuariProfileFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariProfileFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}










