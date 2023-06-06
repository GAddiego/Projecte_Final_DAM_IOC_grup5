package BBDD.consultes;

import BBDD.Sql;
import static BBDD.Sql.connexio;
import static BBDD.Sql.pasw;
import static BBDD.Sql.user;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import objectes.Prestec;

/**
 * Aquesta classe implementa l'interfície Sql i proporciona mètodes per a gestionar
 * les operacions relacionades amb els préstecs a la base de dades.
 *
 * @author aleix
 */
public class SqlPrestecs implements Sql{
    static final String AFEGIR_PRESTEC =  "INSERT INTO prestec (id_usuari, id_llibre, data_prestec, prolongacio, data_venciment) VALUES (?, ?, ?, ?, ?)";
    static final String PRESTECS_ACTIUS_USUARI = "SELECT * FROM prestec WHERE id_usuari = ? AND data_retorn IS NULL";
    static final String PRESTECS_ACTIUS_LLIBRE = "SELECT * FROM prestec WHERE id_llibre = ? AND data_retorn IS NULL" ;
    static final String PRESTECS_ACTIUS_LLIBRE_USUARI = "SELECT * FROM prestec WHERE id_llibre = ? AND id_usuari = ? AND data_retorn IS NULL" ;
    static final String TANCAR_PRESTEC =  "UPDATE prestec SET data_retorn = ?, prolongacio = false WHERE id = ?";
    static final String MODIFICAR_PRESTEC =  "UPDATE prestec SET data_venciment = ?, prolongacio = true WHERE id = ?";
    
    /**
     * Crea un préstec amb les dades proporcionades.
     *
     * @param idUsuari      ID de l'usuari
     * @param idLlibre      ID del llibre
     * @param dataPrestec   Data del préstec
     * @param dataVenciment Data de venciment del préstec
     * @param prolongacio   Indica si s'ha fet una pròrroga del préstec
     */
    public void crearPrestec(int idUsuari, int idLlibre, java.util.Date dataPrestec, java.util.Date dataVenciment, boolean prolongacio) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = AFEGIR_PRESTEC;
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuari);
            ps.setInt(2, idLlibre);
            ps.setTimestamp(3, new Timestamp(dataPrestec.getTime()));
            ps.setBoolean(4, prolongacio);
             ps.setTimestamp(5, new Timestamp(dataVenciment.getTime()));
            ps.executeUpdate();
            System.out.println("Prestec creat amb èxit.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retorna una llista amb els préstecs actius d'un usuari.
     *
     * @param idUsuari ID de l'usuari
     * @return Llista amb els préstecs actius de l'usuari
     */
    public List<Prestec> prestecActiuUsuari(int idUsuari) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Prestec> prestecsActius = new ArrayList<>();

        try  {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = PRESTECS_ACTIUS_USUARI;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariRetorn = rs.getInt("id_usuari");
                int idLlibre = rs.getInt("id_llibre");
                Date dataPrestec = rs.getDate("data_prestec");
                Date dataRetorn = rs.getDate("data_retorn");
                boolean prolongacio = rs.getBoolean("prolongacio");
                Date dataVenciment = rs.getDate("data_venciment");
                
                Prestec prestec = new Prestec(id, idUsuariRetorn, idLlibre, dataPrestec, dataRetorn, prolongacio, dataVenciment);
                prestecsActius.add(prestec);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return prestecsActius;
        
    }
    
    /**
     * Retorna una llista amb els préstecs actius d'un llibre.
     *
     * @param idLlibre ID del llibre
     * @return Llista amb els préstecs actius del llibre
     */
    public List<Prestec> prestecActiuLlibre(int idLlibre) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Prestec> prestecsActius = new ArrayList<>();

        try  {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = PRESTECS_ACTIUS_LLIBRE;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuari = rs.getInt("id_usuari");
                int idLlibreRetorn = rs.getInt("id_llibre");
                Date dataPrestec = rs.getDate("data_prestec");
                Date dataRetorn = rs.getDate("data_retorn");
                boolean prolongacio = rs.getBoolean("prolongacio");
                Date dataVenciment = rs.getDate("data_venciment");
                
                Prestec prestec = new Prestec(id, idUsuari, idLlibreRetorn, dataPrestec, dataRetorn, prolongacio, dataVenciment);
                prestecsActius.add(prestec);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return prestecsActius;
        
    }
    
    /**
     * Retorna el préstec actiu d'un llibre i un usuari específics.
     *
     * @param idLlibre ID del llibre
     * @param idUsuari ID de l'usuari
     * @return El préstec actiu del llibre i l'usuari especificats, o null si no es troba cap coincidència
     */
    public Prestec prestecActiuLlibreUsuari(int idLlibre,int idUsuari) {
        Connection conn = null;
        PreparedStatement ps = null;

        try  {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = PRESTECS_ACTIUS_LLIBRE_USUARI;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);
            statement.setInt(2, idUsuari);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariResultat = rs.getInt("id_usuari");
                int idLlibreResultat = rs.getInt("id_llibre");
                Date dataPrestec = rs.getDate("data_prestec");
                Date dataRetorn = rs.getDate("data_retorn");
                boolean prolongacio = rs.getBoolean("prolongacio");
                Date dataVenciment = rs.getDate("data_venciment");

                Prestec prestec = new Prestec(id, idUsuariResultat, idLlibreResultat, dataPrestec, dataRetorn, prolongacio, dataVenciment);

                return prestec;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    /**
     * Modifica un préstec amb la data de renovació especificada.
     *
     * @param idPrestec        ID del prestec
     * @param dataRenovacio   Data de renovació
     */
    public void modificarPrestec(int idPrestec, java.util.Date dataRenovacio) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        String sql = MODIFICAR_PRESTEC;
        java.sql.Date aux = new java.sql.Date(dataRenovacio.getTime());
        ps = conn.prepareStatement(sql);
        ps.setDate(1, aux );
        ps.setInt(2, idPrestec);
        ps.executeUpdate(); // Ejecutar la consulta de actualización
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Tanca un préstec especificat amb la data de retorn especificada.
     *
     * @param idPrestec   ID del préstec
     * @param dataRetorn  Data de retorn
     */
    public void tancarPrestec(int idPrestec, java.util.Date dataRetorn) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        String sql = TANCAR_PRESTEC;
        java.sql.Date aux = new java.sql.Date(dataRetorn.getTime());
        ps = conn.prepareStatement(sql);
        ps.setDate(1, aux);
        ps.setInt(2, idPrestec);
        ps.executeUpdate(); // Ejecutar la consulta de actualización
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
   
    
   

}
