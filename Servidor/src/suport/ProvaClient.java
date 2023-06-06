package suport;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import objectes.Avis;
import objectes.Comentari;
import objectes.Eines;
import objectes.Llibre;
import objectes.Prestec;
import objectes.Reserva;
import objectes.Usuari;
import objectes.XifradorContrasenya;

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
    public static void main(String[] args) {
        Eines eines = new Eines();
       XifradorContrasenya xC = new XifradorContrasenya();
        
        Llibre llibre = new Llibre();
            llibre.setTitol("El código Da Vinci");
            llibre.setAutor("Dan Brown");
            llibre.setIsbn("9788401337031");
            llibre.setEditorial("Planeta");
            llibre.setAnyPublicacio(2003);
            llibre.setDescripcio("Una emocionante novela que combina elementos de misterio, historia y conspiración.");
            llibre.setSinopsi("El profesor de simbología religiosa Robert Langdon es llamado a resolver un misterioso asesinato en el Louvre y termina envuelto en una trama de intrigas y secretos que podrían cambiar la historia del cristianismo.");
            llibre.setPagines(454);
            llibre.setIdioma("Español");
            llibre.setExemplar(10);
            llibre.setNota("Excelente libro, muy recomendado.");
            llibre.setTitolOriginal("The Da Vinci Code");
            llibre.setTraductor("Alejandro Palomas");
        
        Usuari usuari = new Usuari();
            
            usuari.setUser("johndoe");
            usuari.setPassX(xC.XifradorString("pass32"));
            usuari.setRol("alumne");
            usuari.setDataNaixement(new java.util.Date(1990, 5, 11));
            usuari.setNom("John");
            usuari.setPrimerCognom("Doe");
            usuari.setSegonCognom("Smith");
            usuari.setEmail("johndoe@example.com");
            usuari.setDataAlta(new Date());
            usuari.setMulta(0.0);
            usuari.setSuspensio(false);
            usuari.setUltimaActualitzacio(System.currentTimeMillis());
            usuari.setImageData(eines.convertirABytes("imatges_usuaris/DefaultUser.png"));

            
        System.out.println("Hola");
        String codiUsuari = "buit";
        String array[]= {"buit","buit"};
        Scanner sc = new Scanner(System.in);
        boolean estat = true;
        String adeu;
        boolean sortir = false;
        Llibre llibreMemoria = new Llibre();
        Usuari usuariMemoria = new Usuari();
        Prestec prestecMemoria = new Prestec();
        Reserva reservaMemoria = new Reserva();
        Avis avisMemoria = new Avis();
        array = Autologin();
        while(!sortir){        
            
            System.out.println("Benvingut al client");
            System.out.println("el teu codi actual es: "+ array[0]);
            System.out.println("Que vols fer?");
            if(usuariMemoria != null){
                System.out.println("tens l'usuari en memòria: " + usuariMemoria.getUser() + " " + usuariMemoria.getId() );
            }
            if(llibreMemoria != null){
                System.out.println("el teu llibre actual es: " + llibreMemoria.getTitol());
            }
            if(prestecMemoria != null){
                System.out.println("el teu prestec actual es: " + prestecMemoria.getId());
            }
            if(avisMemoria != null){
                System.out.println("el teu avis actual es: " + avisMemoria.getId());
            }
            if(reservaMemoria != null){
                System.out.println("la reva reserva actual es: " + reservaMemoria.getId());
            }
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
            System.out.println("32- Memoritzar usuari");
            System.out.println("33- Memoritzar algibo");
            
            System.out.println();
            System.out.println("Operacions Llibres:");
            System.out.println("9- Crear llibre");
            System.out.println("10- Modificar llibre");
            System.out.println("11- Borrar llibre");
            System.out.println("12- Buscar llibres");
            System.out.println("30- Veure llibre i portada");
            
            
            System.out.println();
            System.out.println("Operacions Prestec:");
            System.out.println("13- Crear prestec");
            System.out.println("14- Modificar prestec");
            System.out.println("15- Finalitzar prestec");
            System.out.println("16- Buscar prestecs per usuari");
            System.out.println("17- Buscar prestecs per llibre");
            
            System.out.println();
            System.out.println("Operacions Reserves:");
            System.out.println("18- Crear reserva");
            System.out.println("19- Finalitzar reserva");
            System.out.println("20- Buscar reserva usuari");
            System.out.println("21- Buscar resserva llibre");
            System.out.println("34- Passar a prestec");
            System.out.println("35- Les meves reserves");
            
            System.out.println();
            System.out.println("Operacions Comentaris:");
            System.out.println("22- Crear Comentari");
            System.out.println("23- Mostrar comanetaris llibre");
            System.out.println("24- Borrar comentari");
            System.out.println("25- Buscar comentaris llibre");
            
            System.out.println();
            System.out.println("Operacions Avisos:");
            System.out.println("26- Crear avís");
            System.out.println("27- Marcar avós com a llegit");
            System.out.println("28- Buscar avisos nous usuari");

            
            System.out.println();
            System.out.println("29- Sortir");
            
 
                    
            int opcio = sc.nextInt();
            
            switch(opcio){
                case 1:                 
                    array = nouClient();
                    break;
                case 2:
                    array=logout(array);
                    break;
                case 3:
                    usuariMemoria = rebreUsuariAcual(array);
                    break;
                case 4:
                    crearUsuari(array, usuari);
                    break;
                case 5:
                    modificarUsuari(array, usuari);
                    break;
                case 6:
                    borrarUsuari(array);
                    break;
                case 7:                 
                    String[] llista = {"a",null,null,null,null,null,null};
                    llistarUsuari(array,llista);
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
                    String[] llistaLlibre = {null,null,"175",null,null,null,null,null,null,null,null,null,null,null};
                    llibreMemoria=llistarLlibres(array,llistaLlibre);
                    break;
                case 13:
                    crearPrestec(array, llibreMemoria, usuariMemoria);
                    break;
                case 14:
                    allargarPrestec(array, llibreMemoria);
                    break;
                case 15:
                    finalitzarPrestec(array, prestecMemoria);
                    break;
                case 16:
                    prestecMemoria = buscarPrestecsUsuari(array, usuariMemoria);
                    break;
                case 17:
                    buscarPrestecsLlibre(array, llibreMemoria);
                    break;
                case 18:
                    crearReserva(array, llibreMemoria);
                    break;
                case 19:
                    finalitzarReserva(array, reservaMemoria);
                    break;
                case 20:
                    reservaMemoria = buscarReservaUsuari(array, usuariMemoria);
                    break;
                case 21:
                    buscarReservaLlibre(array, llibreMemoria);
                    break;
                case 22:
                    crearComentari(array, usuariMemoria, llibreMemoria);
                    break;
                case 23:
                    mostrarComentaris( array,  llibreMemoria);
                    break;
                case 24:
                    //borrarComentari();
                    break;
                case 25:
                    buscarComentarisLlibre( array,llibreMemoria);
                    break;
                case 26:
                    //crearAvis();
                    break;
                case 27:
                    marcarAvisLlegit(array, avisMemoria);
                    break;
                case 28:
                    avisMemoria=recuperarAvisosNous(array);
                    break;
                case 30:                    
                    llibreMemoria = veureLlibrePortada(array,1);
                    break;
                case 32:                 
                    String[] llista2 = {"analor",null,null,null,null,null,null};
                    usuariMemoria=llistarUsuariRetorn(array,llista2);
                    break;
                case 33:                 
                    String[] llista3 = {"algibo",null,null,null,null,null,null};
                    usuariMemoria=llistarUsuariRetorn(array,llista3);
                    break;
                case 34:
                    passarAprestec(array,reservaMemoria);
                    break;
                default:
                    System.out.println("Tria una opció correcte.");
            }
   
        }

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
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Eines eines = new Eines();
            byte[] obj = (byte[]) ois.readObject();
            System.out.println("longitus : "+ obj.length);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        //recuperarAvisosNous(array);
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

    private static Usuari rebreUsuariAcual(String[] array) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("dades_usuari");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Usuari u = (Usuari) oin.readObject();
            System.out.println(u.toString());
            out.close();
            in.close();
            socket.close();
            return u;
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void llistarUsuari(String[] array, String[] llista) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per llistar usuaris");
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
    
        private static Usuari llistarUsuariRetorn(String[] array, String[] llista) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per llistar usuaris");
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
            return u.get(0);
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static void crearUsuari(String[] array, Usuari usuari) { 
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("crear_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(usuari.toString());
            oos.writeObject(usuari);
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
            String nomUsuari = "johndoe";
            oos.writeObject(nomUsuari);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modificarUsuari(String[] array, Usuari usuari) { //falta arreglar
        XifradorContrasenya xC = new XifradorContrasenya();
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per modificar usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("modificar_usuari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            usuari.setMulta(1000);
            oos.writeObject(usuari);
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

    private static Llibre llistarLlibres(String[] array, String[] llista) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per cercar llibres");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("llistar_llibres");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());           
            oos.writeObject(llista);
            List<Llibre> llibres = new ArrayList();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            llibres = (List<Llibre>) ois.readObject();
            System.out.println("Hem trobat :" + llibres.size() + " llibres");
            for(int i=0; i<llibres.size(); i++)
            System.out.println("i= "+ i + " : " +llibres.get(i).toString());
            out.close();
            
            socket.close();
            return llibres.get(0);
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void veureUsuariImatge(String[] array) {

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre usuari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());      
            out.writeUTF(array[0]);
            out.writeUTF("dades_usuari");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Usuari u = (Usuari) oin.readObject();
            System.out.println(u.toString());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(u.getImageData());
            System.out.println(u.toString());
            // Crear objecte Image a partir del InputStream
            Image imagen = ImageIO.read(inputStream);
            // Mostrar imatge en un JLabel
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(imagen));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
            

        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Avis recuperarAvisosNous(String[] array) {

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_avisos_usuari");
            out.flush();
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            List<Avis> avisos = new ArrayList<>();
            avisos = (List<Avis>) oin.readObject();
                
       
            System.out.println("Tens " + avisos.size() + " comentaris");
            for(Avis a : avisos){
                System.out.println(a.toString());
            }
          
          

            oin.close();
            out.close();
            socket.close();
            return avisos.get(0);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
    }
     private static Llibre veureLlibrePortada(String[] array, int idLlibre) {

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per rebre portada llibre idLLibre = 1");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());      
            out.writeUTF(array[0]);
            out.writeUTF("buscar_llibre_id");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(idLlibre);
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Llibre llibre = (Llibre) oin.readObject();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(llibre.getPortada());
            // Crear objecte Image a partir del InputStream
            Image imagen = ImageIO.read(inputStream);
            // Mostrar imatge en un JLabel
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(imagen));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
            return llibre;

        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void crearReserva(String[] array, Llibre llibreMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear una reserva");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());      
            out.writeUTF(array[0]);
            out.writeUTF("crear_reserva");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("llibre " + llibreMemoria.getId());
            oos.writeObject(llibreMemoria.getId());
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void crearPrestec(String[] array, Llibre llibre, Usuari usuari) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear una reserva " + usuari.getId() );
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());      
            out.writeUTF(array[0]);
            out.writeUTF("crear_prestec");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibre);
            oos.writeObject(usuari);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   public static String[] Autologin() {
        try (Socket socket = new Socket(HOST, PORT);         
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())                
        ){

        System.out.println("Enviando código inicial: (00000000)");
        String idUsuari = "algibo";
        String contrassenya = "pass1";
        out.writeUTF("00000000");
        
        System.out.println("Obtenint PublicKey...");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        PublicKey publicKey = (PublicKey) ois.readObject();
        System.out.println(publicKey.toString());
        
               
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
        oos.writeObject(idUsuari);
        
        XifradorContrasenya xC = new XifradorContrasenya();
        byte[] passXifrat = xC.XifradorString(publicKey, contrassenya);
        System.out.println("Xifrant contrasenya...");
        oos.writeObject(passXifrat);
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        boolean usuariValid = false;
            usuariValid = in.readBoolean();
            if(usuariValid==true){
                String codi = in.readUTF();
                String rol = in.readUTF();
                System.out.println("El teu codi es "+ codi + "i el rol "+ rol);
                String array[]= {"buit","buit"};
                array[0]=codi;
                array[1]=rol;
            return array;
            }else{
                System.out.println("Usuari o contrasenya incorrectes");
            }
            return null;
        
        }catch (IOException ex) {
                Logger.getLogger(Autologin.class.getName()).log(Level.SEVERE, "Conexio fallada", ex);
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(Autologin.class.getName()).log(Level.SEVERE, "Publickey", ex);
        }
        return null;
    
   
    }

    private static void allargarPrestec(String[] array, Llibre llibreMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per ampliar prestec");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("modificar_prestec");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibreMemoria);
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void finalitzarPrestec(String[] array, Prestec prestecMemoria) {
                try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per finalitzar prestec");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("finalitzar_prestec");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(prestecMemoria.getId());
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Prestec buscarPrestecsUsuari(String[] array, Usuari usuariMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_prestec_usuari");
            out.flush();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(usuariMemoria.getId());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            List<Prestec> prestecs = new ArrayList<>();
            prestecs = (List<Prestec>) oin.readObject();
                
       
            System.out.println("Tens " + prestecs.size() + " prestecs");
            for(Prestec p : prestecs){
                System.out.println(p.toString());
            }
          

            oin.close();
            out.close();
            socket.close();
            return prestecs.get(0);


        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void buscarPrestecsLlibre(String[] array, Llibre llibreMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_prestec_llibre");
            out.flush();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibreMemoria.getId());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            List<Prestec> prestecs = new ArrayList<>();
            prestecs = (List<Prestec>) oin.readObject();
                
       
            System.out.println("Tens " + prestecs.size() + " prestecs");
            for(Prestec p : prestecs){
                System.out.println(p.toString());
            }
          

            oin.close();
            out.close();
            socket.close();


        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void finalitzarReserva(String[] array, Reserva reservaMemoria) {
                try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per finalitzar reserva");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("finalitzar_reserva");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(reservaMemoria.getId());
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Reserva buscarReservaUsuari(String[] array, Usuari usuariMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_reserva_usuari");
            out.flush();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(usuariMemoria.getId());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            List<Reserva> reserves = new ArrayList<>();
            reserves = (List<Reserva>) oin.readObject();
                
       
            System.out.println("Tens " + reserves.size() + " reserves");
            for(Reserva p : reserves){
                System.out.println(p.toString());
            }
          

            oin.close();
            out.close();
            socket.close();
            return reserves.get(0);


        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void buscarReservaLlibre(String[] array, Llibre llibreMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_reserva_llibre");
            out.flush();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibreMemoria.getId());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
             List<Reserva> reserves = new ArrayList<>();
            reserves = (List<Reserva>) oin.readObject();
                
       
            System.out.println("Tens " + reserves.size() + " reserves");
            for(Reserva p : reserves){
                System.out.println(p.toString());
            }
          
          

            oin.close();
            out.close();
            socket.close();


        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void crearComentari(String[] array, Usuari usuariMemoria, Llibre llibreMemoria) {
        System.out.println("creant comentari");
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per crear llibre");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("crear_comentari");       
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Comentari comentari = new Comentari();
            comentari.setIdUsuari(usuariMemoria.getId());
            comentari.setIdLlibre(llibreMemoria.getId());
            java.util.Date dataCreacio = new java.util.Date();
            comentari.setDataCreacio(dataCreacio);
            comentari.setComentari("molt bon llibre");
            oos.writeObject(comentari);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /*private static void modificarComentari(String[] array, Comentari comentari) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per modificar comentari");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("modificar_comentari");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(comentari);
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }*/
    
     private static List<Comentari> buscarComentarisLlibre(String[] array, Llibre llibreMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            out.writeUTF(array[0]);
            

            out.writeUTF("buscar_comentari_llibre");
            out.flush();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(llibreMemoria.getId());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            List<Comentari> comentaris = new ArrayList<>();
            comentaris = (List<Comentari>) oin.readObject();
                
       
            System.out.println("Tens " + comentaris.size() + " comentaris");
            for(Comentari c : comentaris){
                System.out.println(c.toString());
            }
            
            
          
            oin.close();
            out.close();
            socket.close();
            return comentaris;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void marcarAvisLlegit(String[] array, Avis avis) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per marcar avis ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("avis_llegit");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(String.valueOf(avis.getId()));
            out.close();
            oos.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Usuari rebreAltreUsuariReduit(String[] array, int idUsuari) {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());            
            out.writeUTF(array[0]);
            out.writeUTF("qui_es");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(idUsuari);
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Usuari u = (Usuari) oin.readObject();
            out.close();
            in.close();
            socket.close();
            return u;
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static void mostrarComentaris(String[] array, Llibre llibreMemoria) {
        List<Comentari>  comentaris = buscarComentarisLlibre(array, llibreMemoria);
        for(Comentari c : comentaris){
            Usuari usu = rebreAltreUsuariReduit(array, c.getIdUsuari());
            usu.toString();
            System.out.println("Usuari " + usu.getUser() + " diu : " + c.getComentari());
        }
    }

    private static void passarAprestec(String[] array, Reserva reservaMemoria) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Conectat al servidor per pasar aprestec ");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeUTF(array[0]);
            out.writeUTF("passar_a_prestec");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(reservaMemoria);
            out.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProvaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
