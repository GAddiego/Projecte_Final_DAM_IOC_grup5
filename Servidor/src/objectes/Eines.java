package objectes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleix
 */
public class Eines implements Serializable{
    
    public final static String rutaImatgeUsuariDefault = "Imatges_usuaris/DefaultUser.png";
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean ComprovarArxius() {
    
        File config = new File("configuracio.conf");
        File imgLlibre = new File("imatges_llibre");
        File imgUsuari = new File("imatges_usuaris");

        if (config.exists() && imgLlibre.exists() && imgUsuari.exists()) {
            System.out.println("Tots els arxius i carpetes correctes");
            return true;
        } else {
            config = new File("../configuracio.conf");
            imgLlibre = new File("../imatges_llibre");
            imgUsuari = new File("../imatges_usuaris");
            if (config.exists() && imgLlibre.exists() && imgUsuari.exists()) {
                System.out.println("Tots els arxius i carpetes correctes");
                return true;
            }else{
                System.out.println("Falten arxius");
                return false;
            }
        }
    }


 
    public static String generarCodi() {
    String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder codi = new StringBuilder();
    Random rnd = new Random();

    for (int i = 0; i < 8; i++) {
        codi.append(alfabet.charAt(rnd.nextInt(alfabet.length())));
    }

    return codi.toString();
    }
    
    public static Date convertirDataString(String data) throws ParseException{
    Date r;
    

    if (data==null){
        r = null;
    }else{
        r=  format.parse(data); 
    }

    return r;
    }
    
    public String convertirStringData(Date data) throws ParseException{
        String retorn; 
        

        if (data==null){
            retorn = null;
        }else{
            retorn = (String)  format.format(data);
        }

    return retorn;
    }
    
    public Boolean comprovarRuta(String ruta){
        File file = new File(ruta);
        if (!file.exists()) {
            System.out.println("La ruta de la imatge no existeix: " + ruta);
            return false;
        }else{
            return true;
        }
    }
    
    public byte[] convertirABytes(String ruta)  {
        try {
            return Files.readAllBytes(Paths.get(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Eines.class.getName()).log(Level.SEVERE, "No s'ha pogut convertir", ex);
        }
        return null;
    }
    
    public Object nullCkeckObject(Object obj){

        if(obj instanceof java.util.Date ){
            Date data = new Date(1630444800000L);
            return data;
        }
        
        return null;
    }
    
    public String diaAvui() {
        Date data = new Date();
        String dataAvui = format.format(data);
        return dataAvui;
    }
    
    public String diaRetorn() {
            Date data = new Date();
            Calendar calendari = Calendar.getInstance();
            calendari.setTime(data);
            calendari.add(Calendar.MONTH, 1);
            java.util.Date dataunmes = calendari.getTime();
            String dataAvui = format.format(dataunmes);
            return dataAvui;
    }
    public String ampliacióRetorn(Date diaRetorn) {
            
            Calendar calendari = Calendar.getInstance();
            calendari.setTime(diaRetorn);
            calendari.add(Calendar.DAY_OF_MONTH, 15);
            java.util.Date dataunmes = calendari.getTime();
            String novaData = format.format(dataunmes);
            return novaData;
    }
    
    public List<String[]> llegirCSV (String ruta) {
        try {
            File archivo = new File(ruta);
            Scanner scanner = new Scanner(archivo);
            String[] dades = null;
            List<String[]> llista = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String linia = scanner.nextLine();
                dades = linia.split(",");
                llista.add(dades);
                
            }
            scanner.close();
            return llista;       
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
            e.printStackTrace();
        }
        return null;
        
    }


}