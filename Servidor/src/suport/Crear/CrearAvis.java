package suport.Crear;

import BBDD.SqlManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Avis;

/**
 *
 * @author aleix
 */
public class CrearAvis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Avis miAvis = new Avis();
            miAvis.setId_usuari(21);
            miAvis.setTitol("Retard en l'entrega");
            miAvis.setMissatge("Vas tard a tornar el llibre");
            miAvis.setDataCreacio(new Date());
            miAvis.setLlegit(false);
            miAvis.setIdCreador(17);
            
            SqlManager sql = new SqlManager();
        try {
            sql.avisos.crearAvis(miAvis, 999999);
        } catch (SQLException ex) {
            Logger.getLogger(CrearAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
