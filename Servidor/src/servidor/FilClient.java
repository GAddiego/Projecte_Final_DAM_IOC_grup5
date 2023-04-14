package servidor;

import BBDD.SqlManager;
import objectes.Usuari;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;
import objectes.Llibre;

/**
 * Classe que representa un fil de client per gestionar la connexió amb el servidor.
 * Aquest fil s'encarrega d'establir una connexió amb el client i retornar al usuari 
 * les dades que sol·liciti.
 * 
 * @author Aleix
 */
public class FilClient extends Thread {
    Eines eines = new Eines();
    
    private final Socket clientSocket;
    private final SqlManager sqlManager;
    private final BufferUsuaris bufferUsuaris;
    private final Usuari usuari;
    private final String codiEntrada;

    /**
     * Constructor de la classe FilClient que rep un objecte Socket del client, un objecte BufferUsuaris
     * per a gestionar el buffer d'usuaris, i el codi d'entrada de l'usuari.
     * 
     * @param clientSocket  Objecte Socket del client per a gestionar la connexió.
     * @param bufferUsuaris Objecte BufferUsuaris per a gestionar el buffer d'usuaris.
     * @param codiEntrada   Codi d'entrada de l'usuari associat a aquesta connexió.
     */
    public FilClient(Socket clientSocket, BufferUsuaris bufferUsuaris, String codiEntrada) {
        this.clientSocket = clientSocket;
        this.bufferUsuaris = bufferUsuaris;
        this.codiEntrada = codiEntrada;
        this.usuari = bufferUsuaris.recuperarUsuari(codiEntrada);
        this.sqlManager = new SqlManager();
    }

    /**
    * Mètode que s'executa quan es comença a executar el fil. 
    */
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            String opcio;
            while (true) {
                opcio = in.readUTF();

                switch (opcio) {
                    case "sortir":
                        System.out.println("L'usuari " + usuari.getUser() + " amb codi " + codiEntrada + " ha sortit");
                        bufferUsuaris.borrar(codiEntrada);
                        clientSocket.close();
                        return;
                    case "dades_usuari":
                        enviarDadesUsuari();
                        break;
                    case "dades_usuari_foto":
                        enviarDadesUsuariFoto();
                        break;
                    case "crear_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearUsuari();
                        } else {
                            out.writeUTF("no tens permisos");
                        }
                        break;
                    case "borrar_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            borrarUsuari();
                        }
                        break;
                    case "modificar_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            modificarUsuari();
                        }
                        break;
                    case "llistar_usuaris":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            consultarLlistaUsuaris();
                        }                       
                        break;
                    case "crear_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearLlibre();
                        }                       
                        break;
                    case "modificar_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            modificarllibre();
                        }                       
                        break;
                    case "borrar_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            borrarLlibre();
                        }                       
                        break;
                    default:
                        System.out.println("Esperant ordres");
                }
            }
        } catch (IOException e) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Envia les dades de l'usuari connectat al client.
     */
    private void enviarDadesUsuari() {
        try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            System.out.println("enviar dades usuari " + usuari.getUser());
            oos.writeObject(usuari);
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarDadesUsuariFoto() {
        try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            System.out.println("enviar dades usuari " + usuari.getUser());
            if(eines.comprovarRuta(usuari.getRutaFoto())){
                usuari.setImageData(usuari.getRutaFoto());
            }else{
                usuari.setImageData(eines.rutaImatgeUsuariDefault);
            }
            oos.writeObject(usuari);
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un usuari a partir de les dades rebudes del client.
     */
    private void crearUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Usuari u = (Usuari) ois.readObject();
            sqlManager.crearUsuari(u);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Borra un usuari a partir de les dades rebudes del client.
     */
    private void borrarUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String nom_usuari = (String) ois.readObject();
            sqlManager.eliminarUsuari(nom_usuari);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Modifica un usuari a partir de les dades rebudes del client.
     */
    private void modificarUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Usuari u = (Usuari) ois.readObject();
            sqlManager.modificarUsuari(u);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaUsuaris(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String[] dadesConsulta = (String[]) ois.readObject();
            oos.writeObject(sqlManager.buscarUsuaris(dadesConsulta));
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearLlibre(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            sqlManager.crearLlibre(llibre);
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void modificarllibre(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            sqlManager.modificarLlibre(llibre);
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void borrarLlibre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            sqlManager.esborrarLlibre(llibre);
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaLlibres(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String[] dadesConsulta = (String[]) ois.readObject();
            oos.writeObject(sqlManager.buscarLlibres(dadesConsulta));
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
