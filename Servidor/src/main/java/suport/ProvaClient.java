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
import objectes.Llibre;
import objectes.Usuari;

/**
 *
 * @author Aleix
 */
public class ProvaClient {

    static final String HOST="localhost";
    static final int  PORT=12345;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
                
        CreadorFaker cf = new CreadorFaker();
        Llibre llibre = cf.crearLlibre();
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
            System.out.println();
            System.out.println("Operacions Usuaris:");
            System.out.println("1- Registrar-te");
            System.out.println("2- Fer logout");
            System.out.println("3- Qui soc");
            System.out.println("4- Crear usuari");
            System.out.println("5- Modificar usuari");
            System.out.println("6- Borrar usuari");
            System.out.println("7- Buscar usuaris");
            System.out.println("8- Usuari amb imatge");
            
            System.out.println();
            System.out.println("Operacions Llibres:");
            System.out.println("9- Crear llibre");
            System.out.println("10- Modificar llibre");
            System.out.println("11- Borrar llibre");
            System.out.println("12- Buscar llibres");
            
            System.out.println();
            System.out.println("Operacions Prestec:");
            System.out.println("13- Crear prestec");
            System.out.println("14- Modificar prestec");
            System.out.println("15- Finalitzar prestec");
            System.out.println("16- Buscar prestecs per usuari");
            System.out.println("17- Buscar prestecs per isbn");
            
            System.out.println();
            System.out.println("Operacions Reserves:");
            System.out.println("18- Crear reserva");
            System.out.println("19- Finalitzar reserva");
            System.out.println("20- Buscar reserva usuari");
            System.out.println("21- Buscar resserva llibre");
            
            System.out.println();
            System.out.println("Operacions Comentaris:");
            System.out.println("22- Crear Comentari");
            System.out.println("23- Modificar Comentari");
            System.out.println("24- Borrar comentari");
            System.out.println("25- Buscar comentaris llibre");
            
            System.out.println();
            System.out.println("Operacions Avisos:");
            System.out.println("26- Crear avís");
            System.out.println("27- Borrar avís");
            System.out.println("28- Buscar avisos usuari");

            
            System.out.println();
            System.out.println("29- Sortir");
            
            //printMenu(array);
                    
            int opcio = sc.nextInt();
            
            switch(opcio){
                case 1:                 
                    array = nouClient();
                    break;
                case 2:
                    array=logout(array);
                    break;
                case 3:
                    rebreUsuariAcual(array);
                    break;
                case 4:
                    crearUsuari(array);
                    break;
                case 5:
                    modificarUsuari(array);
                    break;
                case 6:
                    borrarUsuari(array);
                    break;
                case 7:                 
                    String[] llista = {"a",null,null,null,null,null,null};
                    llistarUsuaria(array,llista);
                    break;
                case 8:
                    veureUsuariImatge(array);
                case 9:
                    crearLlibre(array, llibre);
                    break;
                case 10:
                    modificarLlibre(array, llibre);
                    break;
                case 11:
                    borrarLlibre(array, llibre);               
                    break;
                case 12:
                    String[] llistaLlibre = {null,"a",null,null,null,null,null,null,null,null,null,null,null,null};
                    llistarLlibres(array,llistaLlibre);
                    break;
                case 13:
                    array=logout(array);
                    sortir=true;
                    break;
                default:
                    System.out.println("Tria una opció correcte.");
            }
   
        }

    }
    public static void printMenu(String[] array) {
        String[] menuItems = {
            "Operacions Usuaris:", "Operacions Llibres:", "Operacions Prestec:", 
            "Operacions Reserves:", "Operacions Comentaris:", "Operacions Avisos:"
        };
        String[][] subMenuItems = {
            {"Registrar-te", "Fer logout", "Qui soc", "Crear usuari", "Modificar usuari", "Borrar usuari", "Buscar usuaris", "Usuari amb imatge", ""},
            {"Crear llibre", "Modificar llibre", "Borrar llibre", "Buscar llibres","",""},
            {"Crear prestec", "Modificar prestec", "Finalitzar prestec", "Buscar prestecs per usuari", "Buscar prestecs per isbn",""},
            {"Crear reserva", "Finalitzar reserva", "Buscar reserva usuari", "Buscar resserva llibre","",""},
            {"Crear Comentari", "Modificar Comentari", "Borrar comentari", "Buscar comentaris llibre","",""},
            {"Crear avís", "Borrar avís", "Buscar avisos usuari"}
        };

        System.out.println("Benvingut al client");
        System.out.println("el teu codi actual es: " + array[0]);
        System.out.println("Que vols fer?");
        System.out.println();

        for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("%-25s%-25s%-25s%n", menuItems[i], "", "");
            for (int j = 0; j < subMenuItems[i].length; i +=3 ) {
                System.out.printf("%-25s%-25s%-25s%n", (j+1) + "- " + subMenuItems[i][j], (j+2) + "- " + subMenuItems[i][j+1], (j+3) + "- " + subMenuItems[i][j+3]);
            }
            System.out.println();
        }

        System.out.println("29- Sortir");
    }

    public static String[] nouClient(){
        String array[] = {"buit","buit"};
        try {    
            
        Socket socket = new Socket(HOST, PORT);
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
            Socket socket = new Socket(HOST, PORT);
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
            Socket socket = new Socket(HOST, PORT);
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
            Socket socket = new Socket(HOST, PORT);
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
            Socket socket = new Socket(HOST, PORT);
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
            for (int i=0; i<u.size(); i++){
                System.out.println(u.get(i).toString());
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void crearUsuari(String[] array) { //falta arreglar
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("crear_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Usuari usuariNou = new Usuari("giboca","gibo123","alumne","Gilabert","Martí","Guixeres","giboca@gmail.com");
            oos.writeObject(usuariNou);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void borrarUsuari(String[] array) {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("borrar_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String nomUsuari = "giboca";
            oos.writeObject(nomUsuari);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modificarUsuari(String[] array) { //falta arreglar
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per modificar usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("modificar_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Usuari usuariNou = new Usuari("giboca","gibo123","alumne","Gilabert","Martí","Guixeres","giboca@gmail.com");
            usuariNou.setMulta(1000);
            oos.writeObject(usuariNou);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void crearLlibre(String[] array, Llibre llibre) {
        try {

            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear llibre");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("crear_llibre");       
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibre);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modificarLlibre(String[] array, Llibre llibre) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per modificar llibre");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            llibre.setDescripcio("llibre modificat");
            out.writeUTF(array[0]);
            out.writeUTF("modificar_llibre");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibre);
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void borrarLlibre(String[] array, Llibre llibre) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per borrar llibre");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("borrar_llibre");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibre);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void llistarLlibres(String[] array, String[] llista) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per cercar llibres");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("llistar_llibres");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(llista);
            List<Llibre> llibres = new ArrayList();
            llibres = (List<Llibre>) ois.readObject();
            System.out.println("Hem trobat :" + llibres.size() + " llibres");
            for(int i=0; i<llibres.size(); i++)
            System.out.println("i= "+ i + " : " +llibres.get(i).toString());
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void veureUsuariImatge(String[] array) {

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("dades_usuari_imatge");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            // Recibir el objeto Usuario enviado por el servidor
            Usuari u = (Usuari) oin.readObject();
            System.out.println(u.toString());
            UsuariProfileFrame upf = new UsuariProfileFrame(u);
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
