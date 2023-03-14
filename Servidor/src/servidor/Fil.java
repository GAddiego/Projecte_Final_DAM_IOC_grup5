package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aleix
 */
public class Fil extends Thread{
    
    private BufferUsuaris bf;  
    private DataInputStream in;
    private DataOutputStream out;
    private Usuari u;
    private Socket s;

    public Fil(Socket s, DataInputStream in, DataOutputStream out, Usuari u, BufferUsuaris b) {
        this.s = s;
        this.bf = b;
        this.in = in;
        this.out = out;
        this.u = u;
    }

    
    @Override
    public void run() {
        Eines eines = new Eines();
        boolean estat = true;
        String opcio, codi = "";
        boolean afegit = false;
        
        try {
            while(!afegit){
                codi = eines.generarCodi(); 
                afegit = bf.afegir(codi, u.getTipus());
                if(afegit){
                    System.out.println("El codi es: "+ codi);
                    out.writeBoolean(true);
                    out.writeUTF(codi);
                }
            }        
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
