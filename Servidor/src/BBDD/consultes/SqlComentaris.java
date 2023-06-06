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
 * Classe que conté les consultes SQL relacionades amb els comentaris a la base de dades.
 * Aquesta classe implementa la interfície Sql.
 * 
 * Les funcions d'aquesta classe permeten crear nous comentaris, obtenir llistats de comentaris per llibre i per usuari.
 * Per realitzar les consultes SQL, s'utilitzen les credencials de connexió a la base de dades emmagatzemades en la classe Sql.
 * 
 * @author aleix
 */
public class SqlComentaris implements Sql {
    final static String CREAR_COMENTARI = "INSERT INTO comentaris (id_usuari, id_llibre, comentari, data_creacio) VALUES (?, ?, ?, ?)";
    final static String LLISTAR_COMENTARIS_LIBRE = "SELECT * FROM comentaris WHERE id_llibre = ? ORDER BY data_creacio";
    final static String LLISTAR_COMENTARIS_USUARI = "SELECT * FROM comentaris WHERE id_usuari = ?";
    final static String MODIFICAR_COMENTARI = "UPDATE comentaris SET comentari = ? WHERE id = ?";
    
    /**
     * Crea un nou comentari a la base de dades.
     * 
     * @param comentari el comentari a modificar
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public void modificarComentari(Comentari comentari) throws SQLException {
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = MODIFICAR_COMENTARI;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, comentari.getComentari());
            pstmt.setInt(2, comentari.getIdLlibre());
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Crea un nou comentari a la base de dades.
     * 
     * @param comentari el comentari a crear
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public void crearComentari(Comentari comentari) throws SQLException {
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

    /**
     * Obté una llista de comentaris per a un llibre específic.
     * 
     * @param idLlibre l'identificador del llibre per al qual s'obtenen els comentaris
     * @return una llista de comentaris per al llibre
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public List<Comentari> llistarComentarisLlibre(int idLlibre) throws SQLException {
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_COMENTARIS_LIBRE;
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

                Comentari comentari = new Comentari(idUsuari, idLlibreResposta, dataCreacio, comentariText);
                comentaris.add(comentari);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentaris;
    }
     
    /**
     * Obté una llista de comentaris per a un usuari específic.
     * 
     * @param idLlibre l'identificador del llibre per al qual s'obtenen els comentaris
     * @return una llista de comentaris per a l'usuari
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
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

                Comentari comentari = new Comentari(idUsuari, idLlibreResposta, dataCreacio, comentariText);
                comentaris.add(comentari);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentaris;
    }
}
