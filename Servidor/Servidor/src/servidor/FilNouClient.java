package servidor;

import BBDD.SqlManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleix
 */
public class FilNouClient extends Thread{
    private ServerSocket sv;
    private BufferUsuaris bf;  
    private DataInputStream in;
    private DataOutputStream out;
    private Socket s;

    public FilNouClient(ServerSocket sv, Socket s, DataInputStream in, DataOutputStream out, BufferUsuaris b) {
        this.sv = sv;
        this.s = s;
        this.bf = b;
        this.in = in;
        this.out = out;
    }

    
    @Override
    public void run() {
        Eines eines = new Eines();
        boolean estat = true;
        String opcio, codi = "", usuari, pass;
        boolean afegit = false;
        Usuari user = new Usuari();
        SqlManager sq = new SqlManager();
        
        try {
            System.out.println("esperant usuari i contraseña ");
            out.writeBoolean(true);
            usuari = in.readUTF();
            pass = in.readUTF();
            boolean b = sq.existeixUsuari(usuari, pass);
            if(b){
                user = sq.getUsuari(usuari, pass);
                while(!afegit){
                    codi = eines.generarCodi(); 
                    afegit = bf.afegir(codi, user );
                    if(afegit){
                    System.out.println("El codi es: "+ codi);
                    out.writeBoolean(true);

                    out.writeUTF(codi);
                    out.writeUTF(user.getRol());
                    }
                }
                System.out.println("Tancant socket codi " + codi);
            }else{
                out.writeBoolean(false);
                System.out.println("Tancant socket de l'usuari " + usuari + " incorrecte ");
            }

            s.close();
        } catch (IOException e) {
                
        } catch (ParseException ex) {
            Logger.getLogger(FilNouClient.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
