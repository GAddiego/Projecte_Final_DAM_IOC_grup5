package suport.Crear;

import BBDD.SqlManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;
import objectes.Llibre;
import objectes.Reserva;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class CrearPrestec {

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
            List<Reserva> reserves = sql.reserves.llistarReservesLlibre(llibre.getId());
            //System.out.println("Prestec " + sql.prestec.obtenirPrestecLlibre(llibre.getId()).toString());
            if (sql.prestec.obtenirPrestecLlibre(llibre.getId())!=null){
                System.out.println("Ja està prestat");
            }else{              
                if (reserves.size()>0){
                    for(Reserva r : reserves){
                        System.out.println(r.toString());
                    }
                    if (reserves.get(0).getIdUsuari() == ui.getId()){
                        sql.prestec.crearPrestec(ui.getId(), llibre.getId(), date, eines.convertirDataString(eines.diaRetorn()), false);
                        sql.reserves.finalitzarReserva(reserves.get(0).getId(), date);
                    }else{
                        System.out.println("No s'ha pogut realitzar el prestec ja que hi ha usuaris amb reserva esperant");
                    }
                }else{
                    sql.prestec.crearPrestec(ui.getId(), llibre.getId(), date, eines.convertirDataString(eines.diaRetorn()), false);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(CrearReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CrearPrestec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
