package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aleix
 */
public class FilClient extends Thread{
    
    private BufferUsuaris bf;  
    private DataInputStream in;
    private DataOutputStream out;
    private Usuari u;
    private Socket s;


    FilClient(Socket s, BufferUsuaris bf, DataInputStream in, DataOutputStream out, Usuari usuari) {
        this.s = s;
        this.in = in;
        this.out = out;
        this.u = usuari;
    }

    
    @Override
    public void run() {
        boolean estat = true;
        String opcio, codi = "";
        
        try {
  
            System.out.println("Esperant ordres");
            while(estat){
               
                opcio = in.readUTF();
                
                switch(opcio){
                    case "sortir":
                        System.out.println("L'usuari " + u.getUser() + "ha sortit");
                        bf.borrar(codi);
                        s.close();
                        break;
                    case "consultar":
                         System.out.println(bf.hash);
                    default:
                        System.out.println("Esperant ordres");
                }
            }
            

        } catch (IOException e) {
                e.printStackTrace();
                }  
    }
    
}
