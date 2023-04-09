package servidor;

import objectes.Usuari;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleix
 */
public class FilClient extends Thread{
    
    private BufferUsuaris bf;  
    private DataInputStream in;
    private DataOutputStream out;
    private Usuari u;
    private String codiEntrada;
    private Socket s;


    FilClient(Socket s, BufferUsuaris bf, DataInputStream in, DataOutputStream out, String codiEntrada) {
        this.s = s;
        this.in = in;
        this.out = out;
        this.bf = bf;
        this.codiEntrada = codiEntrada;
        this.u = bf.recuperarUsuari(codiEntrada);
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
                        System.out.println("L'usuari " + u.getUser() + " amb codi "+ codiEntrada + " ha sortit");
                        bf.borrar(codiEntrada);
                        s.close();
                        break;
                    case "llistar_usuaris":
                        if(u.getRol().equals("admin")){
                            System.out.println(bf.hash);
                            out.writeUTF("fet");
                        }else{
                            out.writeUTF("no tens permisos");
                        }
                    case "dades_usuari":
                        enviarDadesUsuari(u);
                        break;
                    default:
                        System.out.println("Esperant ordres");
                }
            }
            

        } catch (IOException e) {
                
                }  
    }

    private void enviarDadesUsuari(Usuari u) {
        try {
            // Crear un ObjectOutputStream a partir del OutputStream del socket
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            System.out.println("enviar dades usuari " + u.getUser());
            // Enviar el objeto Usuario al cliente
            out.writeObject(u);
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
