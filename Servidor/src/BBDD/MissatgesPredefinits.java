package BBDD;

import java.sql.SQLException;
import java.util.List;
import objectes.Avis;
import objectes.Eines;
import objectes.Reserva;

/**
 * Classe que conté els mètodes per a l'enviament de missatges predefinits relacionats amb la base de dades.
 * Aquests missatges són notificacions enviades als usuaris per informar-los de diversos esdeveniments.
 * 
 * Les funcions d'aquesta classe permeten enviar avisos sobre la disponibilitat de llibres i realitzar accions associades a aquests avisos.
 * 
 * Per enviar avisos i realitzar les accions corresponents, s'utilitza un objecte SqlManager per a accedir a la base de dades,
 * un objecte Eines per a obtenir dades relacionades amb les reserves i una instància de java.util.Date per a obtenir la data actual.
 * 
 * @author aleix
 */
public class MissatgesPredefinits {

    SqlManager sqlManager = new SqlManager();
    Eines eines = new Eines();
    java.util.Date date = new java.util.Date();

    /**
     * Envia un avís a l'usuari indicant que un llibre està disponible per ser recollit.
     * Aquest mètode crea un nou avís i el desa a la base de dades. També realitza les accions necessàries
     * per a finalitzar les reserves relacionades amb el llibre indicat.
     * 
     * @param idUsuari l'identificador de l'usuari al qual s'envia l'avís
     * @param idLlibre l'identificador del llibre disponible
     * @throws SQLException si es produeix un error en l'execució de les consultes SQL
     */
    public void enviarAvisLlibreDisponible(int idUsuari, int idLlibre) throws SQLException {
        Avis avisNou = new Avis();
        avisNou.setIdCreador(999999);
        avisNou.setId_usuari(idUsuari);
        avisNou.setTitol("Llibre disponible");
        avisNou.setLlegit(false);
        avisNou.setMissatge("T'informem que el llibre " + sqlManager.llibres.buscarLlibreid(idLlibre).getTitol() + " ja està disponible. Tens fins al dia "
                + eines.diaFinalReserva(date) + " per recollir-lo");
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
