package Altres;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleix
 */
public class ProvaClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
                
        Socket socket = new Socket("localhost", 12345);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String codiUsuari="buit";
        String array[]= {"buit","buit"};
        Scanner sc = new Scanner(System.in);
        boolean estat = true;
        String adeu;
        System.out.println(in.readUTF());
        boolean sortir = false;
        while(!sortir){
            
            System.out.println("Benvingut al cleint");
            System.out.println("el teu codi actual es: "+ codiUsuari);
            System.out.println("Que vols fer?");
            System.out.println("1- Registrar-te");
            System.out.println("2- Fer logout");
            System.out.println("3- Compravar usuaris");
            System.out.println("4- Sortir");
            
            int opcio = sc.nextInt();
            
            switch(opcio){
                case 1:                 
                    array = nouClient(socket, in, out);
                    break;
                case 2:
                    sortir(codiUsuari);
                    break;
                case 3:
                    veureUsuaris();
                    break;
                case 4:
                    sortir=true;
                    break;
                default:
                    System.out.println("Tria una opció correcte.");
            }
   
        }

    }
    public static String[] nouClient(Socket s, DataInputStream in, DataOutputStream out ){
        String codi= "buit", rol = "buit";
        String array[] = {"buit","buit"};
        try {         
            Scanner sc = new Scanner(System.in);

            System.out.println("Conectat al servidor");

            
            System.out.println("Usuari: ");
            String usuari = sc.nextLine();

            System.out.print("Contrassenya: ");
            String pass = sc.nextLine();

            out.writeUTF("00000000");

            out.writeUTF(usuari);
            out.writeUTF(pass);
            
            boolean usuariValid = false;
            usuariValid = in.readBoolean();
            if(usuariValid==true){
                codi = in.readUTF();
                rol = in.readUTF();
                System.out.println("El teu codi es "+ codi + "i el rol "+ rol);
                array[0]=codi;
                array[1]=rol;
                
            }else{
                System.out.println("Usuari o contrasenya incorrectes");
            }
            out.writeUTF("adeu");
            out.close();
            in.close();
            s.close();
                    
        } catch (IOException ex) {
            System.out.println("Usuari o contrasenya incorrectes");
            return array;
        }
        return array;
        
    }
    public static void sortir(String codi){
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectat al servidor");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            if(codi.equals("buit")){
                System.out.print("Primer entra, no estàs validat al sistema");
            }else{
                out.writeUTF(codi);
                out.writeUTF("sortir");
                System.out.print("Ja has sortit");
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void veureUsuaris() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
