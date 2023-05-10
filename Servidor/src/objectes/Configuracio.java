
package objectes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleix
 */
public class Configuracio {
    static HashMap<String, String> dades = new HashMap<>();
    
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
    
    public String getDada(String entrada)  {
        
        return dades.get(entrada);
    }
}
