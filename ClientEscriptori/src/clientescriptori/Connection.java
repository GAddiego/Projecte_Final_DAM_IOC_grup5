package clientescriptori;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import clientescriptori.Usuari;

public class Connection {

    final String codiInicial = "00000000";
    String idUsuari, contrassenya, codi, rol;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    JPanel panel;
    Usuari usuari;

    public Connection(String idUsuari, String contrassenya, JPanel panel) {
        this.idUsuari = idUsuari;
        this.contrassenya = contrassenya;
        this.panel = panel;
    }

    private boolean iniciarConnexio() {
        boolean connexioCorrecta = false;

        try {
            socket = new Socket("localhost", 12345);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            connexioCorrecta = true;

        } catch (IOException e) {
            System.out.println("error de connexio!!!!!");
            JOptionPane.showMessageDialog(panel, "No s'ha pogut connectar amb el servidor.\n"
                    + "Torni a provar passat un moment o contacti amb l'administrador del sistema.  ",
                    "Error de connexió", JOptionPane.ERROR_MESSAGE);
        }

        return connexioCorrecta;
    }

    private boolean logIn() {
        boolean loginCorrecte = false;

        try {

            out.writeUTF(codiInicial);
            out.writeUTF(idUsuari);
            out.writeUTF(contrassenya);

            boolean idUsuariValid = false;
            idUsuariValid = in.readBoolean();
            if (idUsuariValid) {
                loginCorrecte = true;
                codi = in.readUTF();
                rol = in.readUTF();
                usuari = new Usuari(idUsuari, contrassenya, rol);

            } else {
                JOptionPane.showMessageDialog(panel, "Usuari o contrassenya incorrectes.  ",
                        "Error de sessió", JOptionPane.ERROR_MESSAGE);
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("error de connexio!!!!!");
            JOptionPane.showMessageDialog(panel, "No s'ha pogut connectar amb el servidor.\n"
                    + "Torni a provar passat un moment o contacti amb l'administrador del sistema.  ",
                    "Error de connexió", JOptionPane.ERROR_MESSAGE);
        }

        return loginCorrecte;
    }

    public boolean iniciarSessio() {
        boolean connexio = iniciarConnexio();
        boolean logInSucces = false;
        if (connexio) {
            logInSucces = logIn();
        }
        return logInSucces;
    }

    public String getCodi() {
        return codi;
    }

    public String getRol() {
        return rol;
    }

    public Usuari getUsuari() {
        return usuari;
    }
    
    public void logOut() {
        boolean connexio = iniciarConnexio();
        if (connexio) {
            try {
                out.writeUTF(codi);
                out.writeUTF("sortir");
                idUsuari = "";
                contrassenya = "";
                codi = "";
                rol = "";
            } catch (IOException ex) {
                
            }
        }
    }

}
