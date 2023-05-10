package  servidor;

import objectes.UsuariIntern;
import java.util.HashMap;

/**
 *
 * @author aleix
 */
public class BufferUsuaris {
    
    HashMap<String, UsuariIntern> hash = new HashMap<>();

    public BufferUsuaris() {

    }
    
    public synchronized  boolean afegir(String clau, UsuariIntern user){
        if (hash.containsKey(clau)) {
            return false;
        } else {
            hash.put(clau, user);
            return true;
        }
    }
    
    public synchronized boolean comprovar(String clau){
        if (hash.containsKey(clau)) {
            return true;
        } else {           
            return false;
        }  
    } 
    
    public synchronized  void borrar(String clau){
        hash.remove(clau);
    }
    
        public synchronized UsuariIntern recuperarUsuari(String clau){
        if (hash.containsKey(clau)) {
            return hash.get(clau);
        } else {           
            return null;
        }   
    } 
    
    public synchronized  void actualitzarTempsUsuari(String clau){
        this.hash.get(clau).setUltimaActualitzacio();
    }

    public synchronized HashMap<String, UsuariIntern> getHash() {
        return hash;
    }
    
}
