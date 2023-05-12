package BBDD;

import java.sql.SQLException;
import java.util.List;
import objectes.Avis;
import objectes.Eines;
import objectes.Reserva;

/**
 *
 * @author aleix
 */
public class MissatgesPredefinits {

    SqlManager sqlManager = new SqlManager();
    Eines eines = new Eines();
    java.util.Date date = new java.util.Date();

    public void enviarAvisLlibreDisponible(int idUsuari, int idLlibre) throws SQLException {
        Avis avisNou = new Avis();
        avisNou.setIdCreador(999999);
        avisNou.setId_usuari(idUsuari);
        avisNou.setTitol("Llibre disponible");
        avisNou.setLlegit(false);
        avisNou.setMissatge("T'informem que el llibre " + sqlManager.llibres.buscarLlibreid(idLlibre).getTitol() + "ja està disponible. Tens de fins el dia  "
                + eines.diaFinalReserva(date) + " per recullir-lo");
        avisNou.setIdCreador(idLlibre);
        avisNou.setDataCreacio(date);
        sqlManager.avisos.crearAvis(avisNou, idUsuari);
        List<Reserva> llistarReservesUsuari = sqlManager.reserves.llistarReservesUsuari(idUsuari);
        for (Reserva r : llistarReservesUsuari){
            if (r.getIdLlibre() == idLlibre){
                sqlManager.reserves.finalitzarReserva(idLlibre, eines.diaFinalReserva(date));
            }
        }
    }
}
