package servidor;

import java.util.HashMap;

/**
 *
 * @author aleix
 */
public class BufferUsuaris {
    
    HashMap<String, Usuari> hash = new HashMap<>();

    public BufferUsuaris() {
        Usuari u = new Usuari();
        u.setUser("admin");
        u.setPass("1234");
        u.setRol("ghost");
        hash.put("00000000", u);
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
    
}
