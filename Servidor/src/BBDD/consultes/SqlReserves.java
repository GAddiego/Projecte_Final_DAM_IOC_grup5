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
import objectes.Reserva;

/**
 *
 * @author aleix
 */
public class SqlReserves implements Sql {
    static final String CREAR_RESERVA = "INSERT INTO reserves (id_usuari, id_llibre, data_reserva, expiracio_reserva) VALUES (?, ?, ?, ?)";
    static final String FINALITZAR_RESERVA = "UPDATE reserves SET data_recollida = ? WHERE id = ?";
    static final String LLISTAR_RESERVES_USUARIS = "SELECT * FROM reserves WHERE id_usuari = ? ORDER BY data_reserva";
    static final String LLISTAR_RESERVES_LLIBRE = "SELECT * FROM reservse WHERE id_llibre = ? ORDER BY data_reserva";
    
     public void crearReserva(int idUsuari, int idLlibre, String dataReserva, String expiracioData) throws SQLException {
   
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = CREAR_RESERVA;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idUsuari);
            pstmt.setInt(2, idLlibre);
            pstmt.setString(3, dataReserva);
            pstmt.setString(4, expiracioData);
            pstmt.executeUpdate();
        };
    }

    public void finalitzarReserva(int idReserva, Date dataRecollida) throws SQLException {
        
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = FINALITZAR_RESERVA;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, dataRecollida);
            pstmt.setInt(2, idReserva);
            pstmt.executeUpdate();
        }
    }

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
                Date dataRecollida = rs.getDate("data_recollida");

                Reserva reserva = new Reserva(id, idUsuari, idLlibre, dataReserva, dataRecollida);
                reserves.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reserves;
    }
    
    public List<Reserva> llistarReservesLlibre(int idLlibre) throws SQLException {

        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_RESERVES_LLIBRE;
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
                Date dataRecollida = rs.getDate("data_recollida");

                Reserva reserva = new Reserva(id, idUsuari, idLlibre, dataReserva, dataRecollida);
                reserves.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserves;
    }
}
