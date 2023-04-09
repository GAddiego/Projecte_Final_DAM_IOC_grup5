/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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
            e.printStackTrace();
        }
        
        dades.put("bbdd.url", prop.getProperty("bbdd.url"));
        dades.put("bbdd.usuari", prop.getProperty("bbdd.usuari"));
        dades.put("bbdd.contrasenya", prop.getProperty("bbdd.contrasenya"));
    }
    
    public String getDada(String entrada)  {
        
        return dades.get(entrada);
    }
}
