package BBDD.consultes;

import BBDD.Sql;
import static BBDD.Sql.connexio;
import static BBDD.Sql.pasw;
import static BBDD.Sql.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objectes.Avis;

/**
 * Classe que conté les consultes SQL relacionades amb els avisos a la base de dades.
 * Aquesta classe implementa la interfície Sql.
 * 
 * Les funcions d'aquesta classe permeten crear nous avisos, marcar avisos com a llegits i obtenir llistats d'avisos nous i històrics.
 * Per realitzar les consultes SQL, s'utilitzen les credencials de connexió a la base de dades emmagatzemades en la classe Sql.
 * 
 * @author aleix
 */
public class SqlAvisos implements Sql {
    final static String CREAR_AVIS = "INSERT INTO avisos (id_usuari, titol, missatge, data_creacio, llegit, id_creador) VALUES (?, ?, ?, ?, ?, ?)";
    final static String LLEGIR_AVIS = "UPDATE avisos SET llegit = true WHERE id = ?";
    final static String LLISTAR_AVISOS_NOUS= "SELECT * FROM avisos WHERE llegit = false AND id_usuari = ?";
    final static String LLISTAR_AVISOS_HISTORIC="SELECT * FROM avisos WHERE llegit = true AND id_usuari = ?";
    
    /**
     * Crea un nou avís a la base de dades.
     * 
     * @param avis l'avís a crear
     * @param idCreador l'identificador del creador de l'avís
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public void crearAvis(Avis avis, int idCreador) throws SQLException {

        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = CREAR_AVIS;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, avis.getId_usuari());
            pstmt.setString(2, avis.getTitol());
            pstmt.setString(3, avis.getMissatge());
            pstmt.setDate(4, new java.sql.Date(avis.getDataCreacio().getTime()));
            pstmt.setBoolean(5, avis.isLlegit());
            pstmt.setInt(6, idCreador);
            pstmt.executeUpdate();
        }
    }

    /**
     * Marca un avís com a llegit a la base de dades.
     * 
     * @param idAvis l'identificador de l'avís a marcar com a llegit
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public void marcarllegit(int idAvis) throws SQLException {
        
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLEGIR_AVIS;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idAvis);
            pstmt.executeUpdate();
        }
        
    }
    
    /**
     * Obté una llista d'avisos nous per a un usuari específic.
     * 
     * @param idUsuari l'identificador de l'usuari per al qual s'obtenen els avisos nous
     * @return una llista d'avisos nous per a l'usuari
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public List<Avis> llistarNous(int idUsuari) throws SQLException {
         
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_AVISOS_NOUS;
        List<Avis> avisos = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariResposta = rs.getInt("id_usuari");
                String titol = rs.getString("titol");
                String missatge = rs.getString("missatge");
                Date dataCreacio = rs.getDate("data_creacio");
                Boolean llegit = rs.getBoolean("llegit");
                int idCreador = rs.getInt("id_creador");

                Avis avis = new Avis(id, idUsuariResposta, titol, missatge, dataCreacio, llegit, idCreador);
                avisos.add(avis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisos;
    }
     
    /**
     * Obté una llista d'avisos històrics per a un usuari específic.
     * 
     * @param idUsuari l'identificador de l'usuari per al qual s'obtenen els avisos històrics
     * @return una llista d'avisos històrics per a l'usuari
     * @throws SQLException si es produeix un error en l'execució de la consulta SQL
     */
    public List<Avis> llistarHistoric(int idUsuari) throws SQLException {
          
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_AVISOS_HISTORIC;
        List<Avis> avisos = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariResposta = rs.getInt("id_usuari");
                String titol = rs.getString("titol");
                String missatge = rs.getString("missatge");
                Date dataCreacio = rs.getDate("data_creacio");
                Boolean llegit = rs.getBoolean("llegit");
                int idCreador = rs.getInt("id_creador");

                Avis avis = new Avis(id,idUsuariResposta, titol, missatge, dataCreacio, llegit, idCreador);
                avisos.add(avis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisos;
    }
    
    
}
