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
 *
 * @author algibo
 */
public class Servidor {

    static String CODI_ENTRADA = "00000000";
    
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
