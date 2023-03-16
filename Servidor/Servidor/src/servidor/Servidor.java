package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Aleix
 */
public class Servidor {
    static String CODI_ENTRADA = "00000000";


    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        
        BufferUsuaris bf = new BufferUsuaris();
        

        FilNouClient fil;
        FilClient filClient;
        
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket socket;
           
            System.out.println("Servidor iniciat");

            while (true) {
                socket = serverSocket.accept();
                
                System.out.println("Usuari conectat desde " + socket.getInetAddress().getHostName());

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                
                out.writeUTF("Benvingut");
                System.out.println("esperant codi entradda: ");
                String codiEntrada = in.readUTF();
                System.out.println("codi entrada introduit "+ codiEntrada);

                if (codiEntrada.equals(CODI_ENTRADA)) {
                    System.out.println("entrem al fil");
                    fil = new FilNouClient(serverSocket,socket,in,out,bf);
                    fil.start();
                } else if (bf.comprovar(codiEntrada)) { 
                    filClient = new FilClient(socket,bf, in,out,bf.recuperarUsuari(codiEntrada));
                    filClient.start();
                } else{
                    out.writeUTF("USER_ERROR");
                    socket.close();
                }
                

        }
    }
   
}
    

