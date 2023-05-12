
package suport.Crear;

import BBDD.SqlManager;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import objectes.Eines;
import objectes.Llibre;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class CrearReserva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SqlManager sql = new SqlManager();
        Eines eines = new Eines();
        Date date = new Date();
        try {
            UsuariIntern ui = sql.uIntern.getUsuari("algibo", "pass1");
            Llibre llibre = sql.llibres.buscarLlibreid(1);
            System.out.println("ID ui + ID llibre :" + ui.getId() + " + " + llibre.getId());
            sql.reserves.crearReserva(ui.getId(), llibre.getId(), date, null);
        } catch (ParseException ex) {
            Logger.getLogger(CrearReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
