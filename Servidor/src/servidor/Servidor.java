package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Aleix
 */
public class Servidor {

    private static ArrayList<Usuari> usuaris = new ArrayList<Usuari>();

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        
        BufferUsuaris bf = new BufferUsuaris();
        
        Eines eines = new Eines();
        Fil fil;
        
        usuaris.add(new Usuari("user1", "pass1","admin"));
        usuaris.add(new Usuari("user2", "pass2","alumne"));
        usuaris.add(new Usuari("user3", "pass3","professor"));
       
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket socket;
           
            System.out.println("Servidor iniciat");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Usuari conectat desde " + socket.getInetAddress().getHostName());

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String user = in.readUTF();
                String pass = in.readUTF();
               
                boolean usuarioValido = false;
                
                        int i=0;
                        while(i<=usuaris.size()) {
                            if ((usuaris.get(i).getUser().equals(user))&&(usuaris.get(i).getPass().equals(pass)) ) {
                                usuarioValido = true;
                                break;
                            }
                            i++;
                        }

                        if (usuarioValido) {
                            fil = new Fil(socket, in,out,usuaris.get(i),bf);
                            fil.start();
                        } else {
                            out.writeBoolean(false);
                            out.writeUTF("Error");
                        }

               




        }
    }
   
}
    

