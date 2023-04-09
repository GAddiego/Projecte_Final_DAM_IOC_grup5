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
       // Usuari u = new Usuari();
        //u.setUser("admin");
        //u.setUser("ghost");
        //u.setPass("1234");
        //u.setRol("ghost");
        //u.setCodi("00000000");
       // hash.put("00000000", u);
       // this.actualitzarTempsUsuari("00000000");
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
