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
 *
 * @author aleix
 */
public class SqlAvisos implements Sql {
    final static String CREAR_AVIS = "INSERT INTO avis (id_usuari, titol, missatge, data_creacio, llegit) VALUES (?, ?, ?, ?, ?)";
    final static String LLEGIR_AVIS = "UPDATE INTO avis SET llegit WHERE id VALUES (?, ?)";
    final static String LLISTAR_AVISOS_NOUS= "SELECT * FROM avis WHERE llegit = true AND id VALUES (?)";
    final static String LLISTAR_AVISOS_HISTORIC="SELECT * FROM avis WHERE id = ? VALUES (?)";
    
    public void createAvis(Avis avis, int idUsuari) throws SQLException {

        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = CREAR_AVIS;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idUsuari);
            pstmt.setString(2, avis.getTitol());
            pstmt.setString(3, avis.getMissatge());
            pstmt.setDate(4, new java.sql.Date(avis.getDataCreacio().getTime()));
            pstmt.setBoolean(5,avis.isLlegit());
            pstmt.executeUpdate();
        }
    }

    public void llegit(Avis avis) throws SQLException {
        
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLEGIR_AVIS;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            
        }
        
    }
    
    public List<Avis> llistarNous(int idUsuari) throws SQLException {
         
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_AVISOS_NOUS;
        List<Avis> avisos = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int idUsuariResposta = rs.getInt("id_usuari");
                String titol = rs.getString("titol");
                String missatge = rs.getString("missatge");
                Date dataCreacio = rs.getDate("data_creacio");
                Boolean llegit = rs.getBoolean("llegit");
                

                Avis avis = new Avis(idUsuariResposta, titol, missatge, dataCreacio, llegit);
                avisos.add(avis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisos;
    }
     
     
    public List<Avis> llistarHistoric(int idUsuari) throws SQLException {
          
        Connection conn = DriverManager.getConnection(connexio, user, pasw);  
        String query = LLISTAR_AVISOS_HISTORIC;
        List<Avis> avisos = new ArrayList<>();
        try  {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int idUsuariResposta = rs.getInt("id_usuari");
                String titol = rs.getString("titol");
                String missatge = rs.getString("missatge");
                Date dataCreacio = rs.getDate("data_creacio");
                Boolean llegit = rs.getBoolean("llegit");
                

                Avis avis = new Avis(idUsuariResposta, titol, missatge, dataCreacio, llegit);
                avisos.add(avis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisos;
    }
    
    
}
