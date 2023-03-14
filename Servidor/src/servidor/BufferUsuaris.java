package servidor;

import java.util.HashMap;

/**
 *
 * @author aleix
 */
public class BufferUsuaris {
    
    HashMap<String, String> hash = new HashMap<>();

    public BufferUsuaris() {
    }
    
    public synchronized  boolean afegir(String clau, String tipus){
        if (hash.containsKey(clau)) {
            return false;
        } else {
            hash.put(clau, tipus);
            return true;
        }
    }
    
    public synchronized String comprovar(String clau){
        if (hash.containsKey(clau)) {
            return hash.get(clau);
        } else {           
            return null;
        }
        
    } 
    
    public synchronized  void borrar(String clau){
        hash.remove(clau);
    }
    
}
