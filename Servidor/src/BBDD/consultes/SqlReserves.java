package BBDD.consultes;

import BBDD.Sql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Reserva;

/**
 * Aquesta classe implementa l'interfície Sql i proporciona mètodes per gestionar
 * les operacions relacionades amb les reserves de llibres.
 * @author aleix
 */
public class SqlReserves implements Sql {
    static final String CREAR_RESERVA = "INSERT INTO reserves (id_usuari, id_llibre, data_reserva, data_fi_reserva) VALUES (?, ?, ?, ?)";
    static final String FINALITZAR_RESERVA = "UPDATE reserves SET data_fi_reserva= ? WHERE id = ?";
    static final String LLISTAR_RESERVES_USUARIS = "SELECT * FROM reserves WHERE id_usuari = ? ORDER BY data_reserva";
    static final String LLISTAR_RESERVES_LLIBRE_ACTIVES = "SELECT * FROM reserves WHERE id_llibre = ? AND data_fi_reserva IS NULL ORDER BY data_reserva ASC, id ASC";
    static final String ELIMINAR_RESERVA = "DELETE FROM reserves WHERE id = ?";
    
    /**
     * Eliminar una reserva donada.
     *
     * @param idReserva    ID de la reserva a finalitzar
     * @throws SQLException si es produeix un error en la connexió a la base de dades
     */
    public void eliminarReserva(int idReserva) throws SQLException{
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = ELIMINAR_RESERVA;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idReserva);
            pstmt.executeUpdate();
        }
        System.out.println("Reserva eliminada");
    }
    /**
     * Finalitza una reserva donada, actualitzant la data de recollida.
     *
     * @param idReserva    ID de la reserva a finalitzar
     * @param dataRecollida Data de recollida de la reserva
     * @throws SQLException si es produeix un error en la connexió a la base de dades
     */
    
    public void finalitzarReserva(int idReserva, java.util.Date dataRecollida) throws SQLException {
        
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = FINALITZAR_RESERVA;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setTimestamp(1, new Timestamp(dataRecollida.getTime()));
            pstmt.setInt(2, idReserva);
            pstmt.executeUpdate();
        }
        System.out.println("Reserva finalitzada");
    }

    /**
     * Retorna una llista de totes les reserves d'un usuari específic.
     *
     * @param idUsuari ID de l'usuari
     * @return llista de reserves de l'usuari
     * @throws SQLException si es produeix un error en la connexió a la base de dades
     */
    public List<Reserva> llistarReservesUsuari(int idUsuari) throws SQLException {
        
        Connection conn = DriverManager.getConnection(connexio, user, pasw);
        List<Reserva> reserves = new ArrayList<>();
        String query = LLISTAR_RESERVES_USUARIS;

        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariResultat = rs.getInt("id_usuari");
                int idLlibre = rs.getInt("id_llibre");
                Timestamp dataReserva = rs.getTimestamp("data_reserva");
                Date dataRecollida = rs.getDate("data_fi_reserva");

                Reserva reserva = new Reserva(id, idUsuari, idLlibre, dataReserva, dataRecollida);
                reserves.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reserves;
    }
    
    
    /**
     * Retorna una llista de totes les reserves actives d'un llibre específic.
     *
     * @param idLlibre ID del llibre
     * @return llista de reserves actives del llibre
     * @throws SQLException si es produeix un error en la connexió a la base de dades
     */
    public List<Reserva> llistarReservesLlibre(int idLlibre) throws SQLException {

        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_RESERVES_LLIBRE_ACTIVES;
        List<Reserva> reserves = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuari = rs.getInt("id_usuari");
                int idLlibreResultat = rs.getInt("id_llibre");
                Timestamp dataReserva = rs.getTimestamp("data_reserva");
                Timestamp dataRecollida = rs.getTimestamp("data_fi_reserva");

                Reserva reserva = new Reserva(id, idUsuari, idLlibre, dataReserva, dataRecollida);
                reserves.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserves;
    }

    /**
     * Crea una nova reserva de llibre.
     *
     * @param idUsuari           ID de l'usuari que realitza la reserva
     * @param idLlibre           ID del llibre reservat
     * @param dataReserva        Data de la reserva
     * @param dataFinalitzacio   Data de finalització de la reserva (opcional, pot ser null)
     * @throws SQLException si es produeix un error en la connexió a la base de dades
     */
    public void crearReserva(int idUsuari, int idLlibre, java.util.Date date,java.util.Date dataFinalitzacio) {
     
            try {
                Connection conn = DriverManager.getConnection(connexio, user, pasw);
                String query = CREAR_RESERVA;
                
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, idUsuari);
                    pstmt.setInt(2, idLlibre);
                    pstmt.setTimestamp(3, new Timestamp(date.getTime()));
                    if (dataFinalitzacio == null){
                        pstmt.setTimestamp(4, null);
                    }else{
                    pstmt.setTimestamp(4,  new Timestamp(dataFinalitzacio.getTime()));
                    }
                    pstmt.executeUpdate();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SqlReserves.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
            Logger.getLogger(SqlReserves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
