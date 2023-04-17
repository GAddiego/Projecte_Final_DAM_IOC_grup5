package suport;

import BBDD.SqlManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Usuari;

/**
 *
 * @author Aleix
 */
public class ProvaClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
                

        System.out.println("Hola");
        String codiUsuari = "buit";
        String array[]= {"buit","buit"};
        Scanner sc = new Scanner(System.in);
        boolean estat = true;
        String adeu;
        boolean sortir = false;
        while(!sortir){            
            System.out.println("Benvingut al client");
            System.out.println("el teu codi actual es: "+ array[0]);
            System.out.println("Que vols fer?");
            System.out.println("1- Registrar-te");
            System.out.println("2- Fer logout");
            System.out.println("3- Llistar usuaris al servidor");
            System.out.println("4- Qui soc");
            System.out.println("5- Sortir");
            System.out.println("6- buscar usuaris");
            
            int opcio = sc.nextInt();
            
            switch(opcio){
                case 1:                 
                    array = nouClient();
                    break;
                case 2:
                    array=logout(array);
                    break;
                case 3:
                    veureUsuaris(array);
                    break;
                case 4:
                    rebreUsuariAcual(array);                  
                    break;
                case 5:
                    array=logout(array);
                    sortir=true;
                    break;
                case 6:
                    String[] llista = {"a",null,null,null,null,null,null};
                    llistarUsuaria(array,llista);
                break;
                default:
                    System.out.println("Tria una opció correcte.");
            }
   
        }

    }
    public static String[] nouClient(){
        String array[] = {"buit","buit"};
        try {    
            
        Socket socket = new Socket("213.195.106.120", 12345);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String codi= "buit", rol = "buit";
        
             
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
            socket.close();
                    
        } catch (IOException ex) {
            System.out.println("Usuari o contrasenya incorrectes");
            return array;
        }
        return array;
        
    }
    public static String[] logout(String[] array){
        String retorn[] = {"buit","buit"};
        try {
            Socket socket = new Socket("localhost", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            if(array[0].equals("buit")){
                System.out.print("Primer entra, no estàs validat al sistema");
            }else{
                out.writeUTF(array[0]);
                out.writeUTF("sortir");
                System.out.print("Ja has sortit");
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorn;
    }

    private static void veureUsuaris(String[] array) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectat al servidor");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(array[0]);
            out.writeUTF("llistar_usuaris");
            System.out.println(in.readUTF());
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rebreUsuariAcual(String[] array) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectat al servidor per rebre usuari");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("dades_usuari");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            System.out.println("fet");
            // Recibir el objeto Usuario enviado por el servidor
            Usuari u = (Usuari) oin.readObject();
            System.out.println(u.toString());
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void llistarUsuaria(String[] array, String[] llista) {
         try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectat al servidor per rebre usuari");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("llistar_usuaris");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(llista);
            List<Usuari> u = new ArrayList();
            u = (List<Usuari>) ois.readObject();
            System.out.println("Hem trobat :" + u.size() + " usuaris");
            System.out.println(u.get(1).toString());
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
