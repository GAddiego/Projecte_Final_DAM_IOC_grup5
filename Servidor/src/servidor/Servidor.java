package servidor;

import BBDD.SqlManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;

/**
 * Classe que implementa el servidor del sistema.
 * Gestiona les connexions dels clients i executa els fils per atendre les peticions.
 * 
 * El servidor utilitza un codi d'entrada per identificar els clients coneguts.
 * Accepta connexions de nous clients i connexions de clients coneguts.
 * 
 * Per cada client, crea un fil per atendre les seves peticions.
 * També executa un fil d'eliminació per gestionar els clients inactius.
 * 
 * El servidor utilitza una connexió a una base de dades per comprovar la validesa dels usuaris.
 * 
 * 
 * @author algibo
 */
public class Servidor {

    /**
     * Codi d'entrada per identificar els clients coneguts.
     */
    static String CODI_ENTRADA = "00000000";
    
     /**
     * Punt d'entrada principal del servidor.
     * Crea i inicialitza els recursos necessaris, com ara connexions de sockets i gestor de base de dades.
     * Accepta connexions dels clients i executa els fils per atendre les peticions.
     * 
     * @param args els arguments de línia de comandes (no s'utilitzen en aquest cas)
     */
    public static void main(String[] args) {
        Eines eines = new Eines();
        SqlManager sqlManager = new SqlManager();
        BufferUsuaris bf = new BufferUsuaris();
        sqlManager.provarConexio();
        System.out.println("v 05");
        
        if(!Eines.ComprovarArxius()){
            System.exit(0);
        }
        
        FilNouClient fil;
        FilClient filClient;
        FilEliminador filEliminador = new FilEliminador(bf);
        filEliminador.start();
        
        try {
            ServerSocket serverSocket = new ServerSocket(12345);    

            Socket socket;
        
            while (true) {
                    socket = serverSocket.accept();

                    System.out.println("Usuari conectat desde " + socket.getInetAddress().getHostName());

                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                    System.out.println("esperant codi entrada: ");
                    

                    String codiEntrada = in.readUTF();

                    System.out.println("codi entrada introduit "+ codiEntrada);

                    if (codiEntrada.equals(CODI_ENTRADA)) {
                        System.out.println("entrem al fil nou");
                        fil = new FilNouClient(serverSocket,socket,in,out,bf);
                        fil.start();
                    } else if (bf.comprovar(codiEntrada)) { 
                        System.out.println("entrem al fil conegut");
                        filClient = new FilClient(socket,bf,codiEntrada);
                        filClient.start();
                    } else{
                        out.writeUTF("USER_ERROR");
                        socket.close();
                    }


            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Ha fallat el socker de la classe servidor", ex);
        }
        
        
    }
    
}
