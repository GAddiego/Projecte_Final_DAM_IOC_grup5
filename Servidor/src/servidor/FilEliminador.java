package  servidor;


import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.UsuariIntern;


/**
 * Classe que representa un fil de client per gestionar la connexió amb el servidor.
 * Aquest fil s'encarrega d'establir una connexió amb el servidor, enviar l'usuari i
 * contrasenya al servidor, rebre el codi i el rol d'usuari assignat pel servidor,
 * i tancar la connexió.
 * Hereta de la classe Thread per permetre l'execució concurrent d'aquesta funcionalitat.
 * 
 * @author Aleix
 */
public class FilEliminador extends Thread {
    private static final long TEMPS_INACTIU=300000;   
    private final BufferUsuaris bf; 
    
    public FilEliminador(BufferUsuaris bf) {
        this.bf = bf;
    }
    
/**
 * Mètode que s'executa quan es comença a executar el fil. 
 * @author Aleix
 */
    @Override
    public void run() {
        while(true){
            try {
                sleep(50000);
                System.out.println("He entrar al eliminador de usuaris. " + bf.getHash().size());               
                if (!bf.getHash().isEmpty()){
                    Iterator<Map.Entry<String, UsuariIntern>> it = bf.getHash().entrySet().iterator();
                    while(it.hasNext()){
                        if((System.currentTimeMillis() - it.next().getValue().getUltimaActualitzacio())>TEMPS_INACTIU){
                             System.out.println("Borro usuari" );
                             it.remove();
                            }
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FilEliminador.class.getName()).log(Level.SEVERE, "Thread eliminador acabat inesperadament", ex);
            } 
        }
       

        
    }
    
}
