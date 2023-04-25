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
import objectes.Usuari;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Connection {

    final String codiInicial = "00000000";
    String idUsuari, contrassenya, codi, rol;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;
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
            System.out.println("error de connexio!!!!! (iniciarConnexio)");
            JOptionPane.showMessageDialog(panel, "No s'ha pogut connectar amb el servidor.\n"
                    + "Torni a provar passat un moment o contacti amb l'administrador del sistema.  ",
                    "Error de connexió", JOptionPane.ERROR_MESSAGE);
        }

        return connexioCorrecta;
    }

    //Un cop tenim connexio amb el socket del servidor, enviem codi inicial,
    //usuari i contrassenya. 
    //Rebem booleà indicant si l'usuari existeix, i, si és true, rebem codi i rol.
    private boolean logIn() throws ClassNotFoundException {
        boolean loginCorrecte = false;

        try {
            System.out.println("enviando codigo inicial: (00000000)");
            out.writeUTF(codiInicial);
            out.writeUTF(idUsuari);
            out.writeUTF(contrassenya);

            boolean idUsuariValid = false;
            idUsuariValid = in.readBoolean();

            if (idUsuariValid) {
                loginCorrecte = true;
                codi = in.readUTF();
                rol = in.readUTF();
                System.out.println("codi: " + codi + " rol: " + rol);

            } else {
                JOptionPane.showMessageDialog(panel, "Usuari o contrassenya incorrectes.  ",
                        "Error de sessió", JOptionPane.ERROR_MESSAGE);
            }

            out.writeUTF("adeu");
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("error de connexio!!!!! (login)");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(panel, "No s'ha pogut connectar amb el servidor.\n"
                    + "Torni a provar passat un moment o contacti amb l'administrador del sistema.  ",
                    "Error de connexió", JOptionPane.ERROR_MESSAGE);
        }

        return loginCorrecte;
    }

    public boolean iniciarSessio() throws ClassNotFoundException {
        boolean connexio = iniciarConnexio();

        System.out.println("conexion iniciada");

        boolean logInSucces = false;
        if (connexio) {

            System.out.println("intentando iniciar sesion");
            logInSucces = logIn();

            System.out.println("sesion iniciada");

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

    public void rebreUsuariAcual(String codi) {
        try {
            Socket socket = new Socket("localhost", 12345);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codi);

            out.writeUTF("dades_usuari");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            System.out.println("fet");
            // Recibir el objeto Usuario enviado por el servidor
            usuari = (Usuari) oin.readObject();
            System.out.println(usuari.toString());

            System.out.println("tancant streams...");
            out.close();
            in.close();
            System.out.println("tancant socket...");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuari> buscarUsuaris(String[] arrayUsuari) throws IOException {
        List<Usuari> usuaris = new ArrayList<>();

        try {

            establirConnexio("llistar_usuaris");
            System.out.println("conexion establecida, enviando arrayUsuario...");
            objectOut.writeObject(arrayUsuari);
            objectIn = new ObjectInputStream(socket.getInputStream());
            usuaris = (List<Usuari>) objectIn.readObject();

            tancarSocket();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuaris;
    }

    private void establirConnexio(String peticio) throws IOException {
        socket = new Socket("localhost", 12345);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF(peticio);
        in = new DataInputStream(socket.getInputStream());
        objectOut = new ObjectOutputStream(socket.getOutputStream());
        // objectIn = new ObjectInputStream(socket.getInputStream());
    }

    private void tancarSocket() throws IOException {
        in.close();
        out.close();
        if (objectOut != null) {
            objectOut.close();
        }
        if (objectIn != null) {
            objectIn.close();
        }
        socket.close();
    }

    public void esborrarUsuari(String nom_usuari) throws IOException {
        establirConnexio("borrar_usuari");
        System.out.println("enviant nom usuari..." + nom_usuari);
        objectOut.writeObject(nom_usuari);
        tancarSocket();
    }

    public void crearUsuari(Usuari u) throws IOException {
        establirConnexio("crear_usuari");
        objectOut.writeObject(u);
        JOptionPane.showMessageDialog(panel, "S'ha creat correctament l'usuari amb id " + u.getUser() + ". La contrassenya per defecte és 'password1'. Recomanem que l'usuari la canvii en el seu primer inici de sessió.");
        tancarSocket();
    }

}
