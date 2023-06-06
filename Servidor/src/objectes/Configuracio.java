
package objectes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa la configuració de l'aplicació.
 * Conté mètodes per llegir les dades de configuració des d'un fitxer i obtenir les dades desitjades.
 * Utilitza un HashMap per emmagatzemar les dades de configuració.
 * 
 * El fitxer de configuració ha de tenir el format de propietats.
 * Les dades de configuració es carreguen en la inicialització de la classe.
 * 
 * Per obtenir una dada de configuració, s'utilitza el mètode getDada().
 * Les dades de configuració es recuperen mitjançant una clau.
 * 
 * Exemple d'ús:
 * Configuracio config = new Configuracio();
 * String urlBBDD = config.getDada("bbdd.url");
 * 
 * Les dades de configuració són accessibles a través del HashMap intern de la classe.
 * 
 * @author Aleix
 */

public class Configuracio {
    static HashMap<String, String> dades = new HashMap<>();
    
     /**
     * Constructor de la classe Configuracio.
     * Carrega les dades de configuració des d'un fitxer de propietats.
     * 
     * Si el fitxer de configuració "configuracio.conf" no es troba a la carpeta actual,
     * intenta carregar-lo des de la carpeta superior "../configuracio.conf".
     * 
     * Les dades de configuració es llegeixen i s'emmagatzemen en un HashMap intern.
     * Les claus i valors del fitxer de configuració s'assignen al HashMap.
     * 
     * Si hi ha un error en llegir o tancar el fitxer de configuració, es registra un error.
     */
    public Configuracio() {
        Properties prop = new Properties();
         
        try {
            FileInputStream fis = new FileInputStream("configuracio.conf");
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            FileInputStream fis;
            try {
                fis = new FileInputStream("../configuracio.conf");
                prop.load(fis);
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Configuracio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Configuracio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        dades.put("bbdd.url", prop.getProperty("bbdd.url"));
        dades.put("bbdd.usuari", prop.getProperty("bbdd.usuari"));
        dades.put("bbdd.contrasenya", prop.getProperty("bbdd.contrasenya"));
    }
    
    /**
     * Obté la dada de configuració corresponent a una clau determinada.
     * 
     * @param entrada La clau de la dada de configuració a obtenir.
     * @return El valor associat amb la clau especificada.
     */
    public String getDada(String entrada)  {
        
        return dades.get(entrada);
    }
}
