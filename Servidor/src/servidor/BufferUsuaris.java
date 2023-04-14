package servidor;

import objectes.Usuari;
import java.util.HashMap;

/**
 *
 * @author aleix
 */
public class BufferUsuaris {
    
    HashMap<String, Usuari> hash = new HashMap<>();

    public BufferUsuaris() {

    }
    
    public synchronized  boolean afegir(String clau, Usuari user){
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
    
        public synchronized Usuari recuperarUsuari(String clau){
        if (hash.containsKey(clau)) {
            return hash.get(clau);
        } else {           
            return null;
        }   
    } 
    
    public synchronized  void actualitzarTempsUsuari(String clau){
        this.hash.get(clau).setUltimaActualitzacio();
    }

    public synchronized HashMap<String, Usuari> getHash() {
        return hash;
    }
    
}
