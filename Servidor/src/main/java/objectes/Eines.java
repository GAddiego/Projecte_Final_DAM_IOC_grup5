package objectes;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Aleix
 */
public class Eines implements Serializable{
    
    public final static String rutaImatgeUsuariDefault = "Imatges_usuaris/DefaultUser.png";
    
    public static String generarCodi() {
    String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder codigo = new StringBuilder();
    Random rnd = new Random();

    for (int i = 0; i < 8; i++) {
        codigo.append(alfabet.charAt(rnd.nextInt(alfabet.length())));
    }

    return codigo.toString();
    }
    
    public static Date convertirDataString(String data) throws ParseException{
    Date r;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    if (data==null){
        r = null;
    }else{
        r=  dateFormat.parse(data); 
    }

    return r;
    }
    
    public String convertirStringData(Date data) throws ParseException{
        String retorn; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (data==null){
            retorn = null;
        }else{
            retorn = (String)  dateFormat.format(data);
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
    
}
