package BBDD.consultes;

import BBDD.Sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objectes.Comentari;

/**
 *
 * @author aleix
 */
public class SqlComentaris implements Sql{
        final static String CREAR_COMENTARI = "INSERT INTO comentaris (id_usuari, id_llibre, comentari, data_creacio) VALUES (?, ?, ?, ?, ?)";
        final static String LLISTAR_COMENTARIS_LIBRE= "SELECT * FROM comentaris WHERE id_llibre ORDER BY data_creacioVALUES (?)";
        final static String LLISTAR_COMENTARIS_USUARI= "SELECT * FROM comentaris WHERE id_llibre VALUES (?)";
        
    public void createAvis(Comentari comentari) throws SQLException {
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = CREAR_COMENTARI;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, comentari.getIdUsuari());
            pstmt.setInt(2, comentari.getIdLlibre());
            pstmt.setString(3, comentari.getComentari());
            pstmt.setDate(4, new java.sql.Date(comentari.getDataCreacio().getTime()));
            pstmt.executeUpdate();
        }
    }

    public List<Comentari> llistarComentarisLlibre(int idLlibre) throws SQLException {
         
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_COMENTARIS_LIBRE;
        List<Comentari> comentaris = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);

            ResultSet rs = statement.executeQuery();
            // "SELECT * FROM comentaris WHERE id_llibre ORDER BY data_creacioVALUES (?)";
            while (rs.next()) {
                int idUsuari = rs.getInt("id_usuari");
                int idLlibreResposta = rs.getInt("id_llibre");
                String comentariText = rs.getString("comentari");
                Date dataCreacio = rs.getDate("data_creacio");

                

                Comentari comentari = new Comentari(idUsuari, idLlibre, dataCreacio, comentariText);
                comentaris.add(comentari);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentaris;
    }
     
     
     public List<Comentari> llistarComentarisUsuari(int idLlibre) throws SQLException {
          
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_COMENTARIS_USUARI;
        List<Comentari> comentaris = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int idUsuari = rs.getInt("id_usuari");
                int idLlibreResposta = rs.getInt("id_llibre");
                String comentariText = rs.getString("comentari");
                Date dataCreacio = rs.getDate("data_creacio");
                

                Comentari comentari = new Comentari(idUsuari, idLlibre, dataCreacio, comentariText);
                comentaris.add(comentari);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentaris;
    }
    
}

