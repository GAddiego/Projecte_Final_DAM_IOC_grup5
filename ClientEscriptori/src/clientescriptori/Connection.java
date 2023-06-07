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
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objectes.Avis;
import objectes.Comentari;
import objectes.Llibre;
import objectes.Reserva;
import objectes.XifradorContrasenya;

public class Connection {

//    static final String HOST = "10.213.195.1";
    static final String HOST = "localhost";
    static final int PORT = 12345;

    final String codiInicial = "00000000";
    String idUsuari, contrassenya, codi, rol;
    static String codiStatic;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;
    JPanel panel;
    Usuari usuari;
    private List<Avis> listaAvisos;
    static Usuari usuariStatic = null;
    private static PublicKey publicKey;

    public Connection(String idUsuari, String contrassenya, JPanel panel) {
        this.idUsuari = idUsuari;
        this.contrassenya = contrassenya;
        this.panel = panel;
    }

    private boolean iniciarConnexio() {
        boolean connexioCorrecta = false;

        try {
            socket = new Socket(HOST, PORT);
            System.out.println("socket obtenido");

            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("outputstream");

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
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            publicKey = (PublicKey) ois.readObject();

            byte[] passXifrat = XifradorContrasenya.XifradorString(contrassenya, publicKey);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(idUsuari);

            oos.writeObject(passXifrat);
//            out.writeUTF(contrassenya);
            boolean idUsuariValid = false;

            DataInputStream din = new DataInputStream(socket.getInputStream());
            idUsuariValid = din.readBoolean();
            System.out.println("usuari valid: " + idUsuariValid);

            if (idUsuariValid) {
                loginCorrecte = true;
                codi = din.readUTF();
                codiStatic = codi;
                rol = din.readUTF();
                System.out.println("codi: " + codi + " rol: " + rol);

            } else {
                JOptionPane.showMessageDialog(panel, "Usuari o contrassenya incorrectes.  ",
                        "Error de sessió", JOptionPane.ERROR_MESSAGE);
            }

            out.writeUTF("adeu");
            din.close();
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
        System.out.println("rebreUsuariAcual");
        try {

            socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codi);
            out.writeUTF("dades_usuari");
            // Recibir el objeto Usuario enviado por el servidor
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            usuari = (Usuari) oin.readObject();
            usuari.setPass(contrassenya);
            usuariStatic = usuari;
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

    public List<Avis> rebreAvisos() {
        System.out.println("consultant avisos");
        try {
            Socket clientSocket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre avisos");
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF(codiStatic);
            dataOutputStream.writeUTF("buscar_avisos_usuari");

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            listaAvisos = (List<Avis>) objectInputStream.readObject();

            objectInputStream.close();

            objectInputStream.close();
            clientSocket.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaAvisos;
    }

    public void notificarAvisLlegit(Avis avis) throws IOException {
        System.out.println("notificant avis llegit");

        Socket clientSocket = new Socket(HOST, PORT);
        System.out.println("Conectat al servidor per notificar avis llegit");
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataOutputStream.writeUTF(codiStatic);
        dataOutputStream.writeUTF("avis_llegit");

        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        oos.writeObject(String.valueOf(avis.getId()));

        oos.close();
        dataOutputStream.close();
        clientSocket.close();
    }

    public static Usuari rebreUsuariAcualStatic(String codi) {
        System.out.println("rebreUsuariAcualStatic");
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari (static)");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codi);
            out.writeUTF("dades_usuari");
            // Recibir el objeto Usuario enviado por el servidor
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            usuariStatic = (Usuari) oin.readObject();

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
        return usuariStatic;
    }

    public static List<Usuari> buscarUsuaris(String[] arrayUsuari) throws IOException {
        List<Usuari> u = new ArrayList<>();

        try {

            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per buscar usuaris");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("llistar_usuaris");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(arrayUsuari);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            u = (List<Usuari>) ois.readObject();
            System.out.println("Hem trobat :" + u.size() + " usuaris");
            for (int i = 0; i < u.size(); i++) {
                System.out.println(u.get(i).toString());
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No s'ha pogut connectar amb el servidor.\n"
                    + "Torni a provar passat un moment o contacti amb l'administrador del sistema.  ",
                    "Error de connexió", JOptionPane.ERROR_MESSAGE);
        }

        return u;
    }

    public void esborrarUsuari(String nom_usuari) throws IOException {

        socket = new Socket(HOST, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF("borrar_usuari");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("enviant nom usuari..." + nom_usuari);

        oos.writeObject(nom_usuari);

        out.close();
        oos.close();
        socket.close();
    }

    public void esborrarLlibre(Llibre llibre) throws IOException {
        socket = new Socket(HOST, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF("borrar_llibre");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("enviant llibre per esborrar..." + llibre.getTitol());

        oos.writeObject(llibre);

        out.close();
        oos.close();
        socket.close();
    }

    public void crearUsuari(Usuari u) throws IOException {

        u.setPassX(XifradorContrasenya.XifradorString(u.getPass(), publicKey));
        socket = new Socket(HOST, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF("crear_usuari");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);
        JOptionPane.showMessageDialog(panel, "S'ha creat correctament l'usuari amb id " + u.getUser() + ".");

        out.close();
        oos.close();
        socket.close();
    }

    public static void modificarUsuari(Usuari usuari) { //falta arreglar
        try {
            Socket socket = new Socket(HOST, PORT);

            usuari.setPassX(XifradorContrasenya.XifradorString(usuari.getPass(), publicKey));

            System.out.println("Conectat al servidor per modificar usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("modificar_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(usuari);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void modificarUsuariSenseXifrar(Usuari usuari) { //falta arreglar
        try {
            Socket socket = new Socket(HOST, PORT);

//            usuari.setPassX(XifradorContrasenya.XifradorString(usuari.getPass(), publicKey));
            System.out.println("Conectat al servidor per modificar usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("modificar_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(usuari);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void crearReserva(int idLlibre) throws IOException {

        System.out.println("connectant el socket per crear reserva");
        Socket socket = new Socket(HOST, PORT);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codiStatic);
        out.writeUTF("crear_reserva");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(idLlibre);
        out.close();
        oos.close();
        socket.close();
    }

    public static List<Reserva> buscarReservaUsuari(int idUsuari) throws IOException, ClassNotFoundException {
        List<Reserva> listaReserves;

        System.out.println("connectant el socket per buscar reserves usuari");
        Socket socket = new Socket(HOST, PORT);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codiStatic);
        out.writeUTF("buscar_reserva_usuari");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(idUsuari);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        listaReserves = (List<Reserva>) ois.readObject();
        out.close();
        oos.close();
        socket.close();

        return listaReserves;
    }

    public static Llibre recuperarNomLlibrePerId(int id) throws IOException, ClassNotFoundException {

        System.out.println("connectant el socket per recuperar nom llibre");
        Socket socket = new Socket(HOST, PORT);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codiStatic);
        out.writeUTF("buscar_llibre_id");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(id);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Llibre llibre = (Llibre) ois.readObject();

        out.close();
        oos.close();
        socket.close();

        return llibre;
    }

    public void creaAvis(Avis avis) throws IOException {
        System.out.println("connectant el socket per enviar avis");
        socket = new Socket(HOST, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF("crear_avis");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(avis);
        JOptionPane.showMessageDialog(panel, "S'ha enviat l'avis correctament.");

        out.close();
        oos.close();
        socket.close();
    }

    public void creaLlibre(String[] arrayLlibre) throws IOException {
        Llibre llibre = utils.Utils.converteixArrayEnLlibre(arrayLlibre);
        socket = new Socket(HOST, PORT);
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(codi);
        out.writeUTF("crear_llibre");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(llibre);
        JOptionPane.showMessageDialog(panel, "S'ha creat correctament el llibre " + llibre.getTitol() + ".");

        out.close();
        oos.close();
        socket.close();
    }

    public List<Llibre> buscaLlibres(String[] llibre) {
        List<Llibre> listaLlibres = new ArrayList<>();
        Socket socket;
        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per buscar llibres");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("llistar_llibres");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibre);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            listaLlibres = (List<Llibre>) ois.readObject();

            ois.close();
            oos.close();
            out.close();
            socket.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaLlibres;
    }

    public static List<Comentari> buscaComentaris(int id) throws ClassNotFoundException {
        List<Comentari> listaComentaris = new ArrayList<>();

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per buscar comentaris");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("buscar_comentari_llibre");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(id);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            listaComentaris = (List<Comentari>) ois.readObject();
            System.out.println(listaComentaris.size());

            ois.close();
            oos.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex + "ois error");
        }
        return listaComentaris;
    }

    public boolean enviaComentari(String comentariString, int idLlibre) {

        rebreUsuariAcual(codiStatic);
        System.out.println("id usuari: " + usuari.getId());

        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per enviar comentari, " + codiStatic);
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("crear_comentari");

            Comentari comentari = new Comentari(usuari.getId(), idLlibre, new Date(), comentariString);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(comentari);
            System.out.println("comentario enviado....");
            System.out.println("cerrando...");
            oos.close();
            out.close();
            socket.close();

            return true;
        } catch (IOException ex) {
            System.out.println(ex + " error");
            return false;
        }

    }

    public String buscaNomPerID(String id) {
        System.out.println("buscaNomPerID");
        String nom = "desconegut";

        try {

            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(codiStatic);
            out.writeUTF("dades_usuari_altre");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(id);
            // Recibir el objeto Usuario enviado por el servidor
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Usuari usuari = (Usuari) oin.readObject();

            System.out.println("tancant streams...");
            out.close();
            in.close();
            System.out.println("tancant socket...");
            socket.close();

            if (usuari != null) {
                nom = usuari.getNom();
            } else {
                nom = null;
            }

        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nom;
    }

    public static Usuari getUsuariStatic() {
        return usuariStatic;
    }

}
