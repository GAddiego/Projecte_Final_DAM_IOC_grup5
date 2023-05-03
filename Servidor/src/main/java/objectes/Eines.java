package objectes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
            System.out.println("Falten alguns arxius o carpetes");
            return false;
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
    
    public byte[] convertirABytes(String ruta) throws IOException {
        return Files.readAllBytes(Paths.get(ruta));
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

}