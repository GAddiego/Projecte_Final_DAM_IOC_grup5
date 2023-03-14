package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Aleix
 */
public class ProvaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        boolean estat = true;
        System.out.print("Usuari: ");
        String usuari = sc.nextLine();

        System.out.print("Contrassenya: ");
        String pass = sc.nextLine();

        Socket socket = new Socket("localhost", 12345);
        System.out.println("Conectat al servidor");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(usuari);
        out.writeUTF(pass);

        boolean usuarioValido = in.readBoolean();
        String codi = in.readUTF();

        if (usuarioValido) {
            System.out.println("Benvingut al servidor " + codi);
            
        } else {
            System.out.println("Accés denegat. Revisa usuari i contrasenya.");
        }
        while(estat){
            System.out.println("Donam una ordre:");
            String ordre = sc.nextLine();
            out.writeUTF(ordre);
        }

        out.close();
        in.close();
        socket.close();
    }
    
}
