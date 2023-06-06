package servidor;

import objectes.UsuariIntern;
import java.util.HashMap;

/**
 * Classe que representa un buffer d'usuaris interns.
 * Permet emmagatzemar, verificar, eliminar i actualitzar informació dels usuaris interns.
 * Els usuaris es guarden en un HashMap utilitzant una clau única per a cada usuari.
 */
public class BufferUsuaris {

    HashMap<String, UsuariIntern> hash = new HashMap<>();

    /**
     * Constructor per defecte de la classe BufferUsuaris.
     */
    public BufferUsuaris() {

    }
    
    /**
     * Afegeix un usuari intern al buffer.
     * 
     * @param clau la clau única de l'usuari
     * @param user l'usuari intern a afegir
     * @return true si l'usuari s'ha afegit amb èxit, false si ja existeix un usuari amb la mateixa clau
     */
    public synchronized boolean afegir(String clau, UsuariIntern user){
        if (hash.containsKey(clau)) {
            return false;
        } else {
            hash.put(clau, user);
            return true;
        }
    }
    
    /**
     * Comprova si un usuari amb la clau especificada existeix al buffer.
     * 
     * @param clau la clau única de l'usuari a comprovar
     * @return true si l'usuari existeix al buffer, false si no
     */
    public synchronized boolean comprovar(String clau){
        if (hash.containsKey(clau)) {
            return true;
        } else {           
            return false;
        }  
    } 
    
    /**
     * Elimina un usuari del buffer utilitzant la clau especificada.
     * 
     * @param clau la clau única de l'usuari a eliminar
     */
    public synchronized void borrar(String clau){
        hash.remove(clau);
    }
    
    /**
     * Recupera un usuari del buffer utilitzant la clau especificada.
     * 
     * @param clau la clau única de l'usuari a recuperar
     * @return l'usuari intern corresponent a la clau especificada, o null si no s'ha trobat cap usuari
     */
    public synchronized UsuariIntern recuperarUsuari(String clau){
        if (hash.containsKey(clau)) {
            return hash.get(clau);
        } else {           
            return null;
        }   
    } 
    
    /**
     * Actualitza el temps de darrera actualització per a un usuari utilitzant la clau especificada.
     * 
     * @param clau la clau única de l'usuari a actualitzar
     */
    public synchronized void actualitzarTempsUsuari(String clau){
        this.hash.get(clau).setUltimaActualitzacio();
    }

    /**
     * Obté el HashMap que conté els usuaris interns del buffer.
     * 
     * @return el HashMap que conté els usuaris interns
     */
    public synchronized HashMap<String, UsuariIntern> getHash() {
        return hash;
    }
    
}
