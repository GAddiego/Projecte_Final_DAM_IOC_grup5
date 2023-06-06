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
 * Classe que conté eines diverses per a la gestió de fitxers, dates i altres funcionalitats útils.
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


    /**
     * Genera un codi aleatori de 8 caràcters.
     * 
     * @return El codi generat.
     */
    public static String generarCodi() {
    String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder codi = new StringBuilder();
    Random rnd = new Random();

    for (int i = 0; i < 8; i++) {
        codi.append(alfabet.charAt(rnd.nextInt(alfabet.length())));
    }

    return codi.toString();
    }
    
    /**
     * Converteix una data representada com a cadena de caràcters a un objecte de tipus Date.
     * 
     * @param data La data en format de cadena de caràcters (yyyy-MM-dd).
     * @return L'objecte de tipus Date corresponent a la data especificada.
     * @throws ParseException Si hi ha un error en analitzar la data.
     */
    public Date convertirDataString(String data) throws ParseException{
    Date r;
    

    if (data==null){
        r = null;
    }else{
        r=  format.parse(data); 
    }

    return r;
    }
    
    /**
     * Converteix un objecte de tipus Date a una cadena de caràcters que representa la data en el format yyyy-MM-dd.
     * 
     * @param data L'objecte de tipus Date a convertir.
     * @return La cadena de caràcters que representa la data en el format especificat.
     * @throws ParseException Si hi ha un error en formatejar la data.
     */
    public String convertirStringData(Date data) throws ParseException{
        String retorn; 
        

        if (data==null){
            retorn = null;
        }else{
            retorn = (String)  format.format(data);
        }

    return retorn;
    }
    
     /**
     * Comprova l'existència d'una ruta d'arxiu.
     * 
     * @param ruta La ruta de l'arxiu a comprovar.
     * @return Retorna true si la ruta d'arxiu existeix, false altrament.
     */
    public Boolean comprovarRuta(String ruta){
        File file = new File(ruta);
        if (!file.exists()) {
            System.out.println("La ruta de la imatge no existeix: " + ruta);
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Comprova l'existència d'una ruta d'arxiu.
     * 
     * @param ruta La ruta de l'arxiu a comprovar.
     * @return Retorna true si la ruta d'arxiu existeix, false altrament.
     */
    public byte[] convertirABytes(String ruta)  {
        try {
            return Files.readAllBytes(Paths.get(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Eines.class.getName()).log(Level.SEVERE, "No s'ha pogut convertir", ex);
        }
        return null;
    }
    
    /**
     * Comprova si un objecte és null i el substitueix per un objecte de data fixe.
     * 
     * @param obj L'objecte a comprovar.
     * @return L'objecte de data fixe si l'objecte original és de tipus Date, null altrament.
     */
    public Object nullCkeckObject(Object obj){

        if(obj instanceof java.util.Date ){
            Date data = new Date(1630444800000L);
            return data;
        }
        
        return null;
    }
    
    /**
     * Obté la data d'avui en format de cadena de caràcters (yyyy-MM-dd).
     * 
     * @return La data d'avui.
     */
    public String diaAvui() {
        Date data = new Date();
        String dataAvui = format.format(data);
        return dataAvui;
    }
    
    /**
     * Obté la data d'un mes a partir de la data actual en format de cadena de caràcters (yyyy-MM-dd).
     * 
     * @return La data d'un mes a partir de la data actual.
     */
    public String diaRetorn() {
            Date data = new Date();
            Calendar calendari = Calendar.getInstance();
            calendari.setTime(data);
            calendari.add(Calendar.MONTH, 1);
            java.util.Date dataunmes = calendari.getTime();
            String dataAvui = format.format(dataunmes);
            return dataAvui;
    }
    
    /**
     * Amplia la data de retorn afegint 15 dies a partir de la data especificada.
     * 
     * @param diaRetorn La data de retorn inicial.
     * @return La data de retorn ampliada.
     */
    public Date ampliacióRetorn(Date diaRetorn) {
            
            Calendar calendari = Calendar.getInstance();
            calendari.setTime(diaRetorn);
            calendari.add(Calendar.DAY_OF_MONTH, 15);
            java.util.Date dataunmes = calendari.getTime();
           
            return dataunmes;
    }
    
    /**
     * Obté la data final de la reserva a partir de la data de retorn, afegint 15 dies.
     * 
     * @param diaRetorn La data de retorn.
     * @return La data final de la reserva.
     */
    public Date diaFinalReserva(Date diaRetorn) {
            
            Calendar calendari = Calendar.getInstance();
            calendari.setTime(diaRetorn);
            calendari.add(Calendar.DAY_OF_MONTH, 15);
            java.util.Date dataunmes = calendari.getTime();
           
            return dataunmes;
    }
    
    /**
     * Llegeix les dades d'un fitxer CSV i les retorna com una llista de vectors de cadenes de caràcters.
     * 
     * @param ruta La ruta del fitxer CSV a llegir.
     * @return La llista de vectors de cadenes de caràcters que representen les dades llegides del fitxer CSV.
     */
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