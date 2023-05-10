package  servidor;


import objectes.Eines;
import objectes.UsuariIntern;
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
 * Classe que representa un fil de servidor per gestionar una nova connexió de client.
 * Aquest fil s'encarrega de llegir l'usuari i contrasenya enviats pel client, validar-los
 * amb la base de dades, generar un codi i afegir-lo a un buffer de codis d'usuari.
 * A continuació, envia el codi i el rol d'usuari al client.
 * 
 * @author Aleix
 */
public class FilNouClient extends Thread {
    private final ServerSocket sv;
    private final BufferUsuaris bf;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket s;

     /**
     * Constructor de la classe FilNouClient.
     * 
     * @param sv     ServerSocket del servidor.
     * @param s      Socket de la connexió del client.
     * @param in     DataInputStream per llegir les dades enviades pel client.
     * @param out    DataOutputStream per enviar dades al client.
     * @param bf      BufferUsuaris on afegir el codi i l'usuari generat.
     */
    
    public FilNouClient(ServerSocket sv, Socket s, DataInputStream in, DataOutputStream out, BufferUsuaris bf) {
        this.sv = sv;
        this.s = s;
        this.bf = bf;
        this.in = in;
        this.out = out;
    }
    
        /**
     * Mètode que s'executa quan es comença a executar el fil. 
     * Llegeix l'usuari i contrasenya enviats pel client, valida-los amb la base de dades,
     * genera un codi i l'afegeix al buffer d'usuaris. A continuació, envia el codi i el rol
     * d'usuari al client.
     */
    @Override
    public void run() {
        Eines eines = new Eines();
        boolean afegit = false;
        String codi = "";
        UsuariIntern user = new UsuariIntern();
        SqlManager sqlManager = new SqlManager();

        try {
            System.out.println("Esperant usuari i contrasenya...");
            String usuari = in.readUTF();
            String pass = in.readUTF();
            boolean existeixUsuari = sqlManager.uIntern.existeixUsuari(usuari, pass);
            if (existeixUsuari) {
                user = sqlManager.uIntern.getUsuari(usuari, pass);
                while (!afegit) {
                    codi = eines.generarCodi();
                    afegit = bf.afegir(codi, user);
                    System.out.println(user.toString());
                    if (afegit) {
                        System.out.println("El codi es: " + codi + " el rol es " + user.getRol());
                        out.writeBoolean(true);
                        out.writeUTF(codi);
                        out.writeUTF(user.getRol());
                    }
                }
                System.out.println("Tancant socket codi " + codi);
            } else {
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
