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
import java.util.ArrayList;
import java.util.List;
import objectes.Prestec;

/**
 *
 * @author aleix
 */
public class SqlPrestecs implements Sql{
    static final String AFEGIR_PRESTEC =  "INSERT INTO prestec (id_usuari, id_llibre, data_prestec, data_retorn, prolongacio) VALUES (?, ?, ?, ?, ?)";
    static final String PRESTECS_ACTIUS = "SELECT * FROM prestec WHERE id_usuari = ? AND data_retorn IS NULL";
    static final String BUSCAR_ACTIUS = "SELECT * FROM prestec WHERE id_llibre = ? AND data_retorn IS NULL";
    static final String BUSCAR_PRESTEC = "SELECT * FROM prestec WHERE id_llibre = ? AND data_retorn IS NULL LIMIT 1 ";
    static final String TANCAR_PRESTEC =  "UPDATE prestec SET data_retorn = ?, prolongacio = false WHERE id = ?";
    static final String MODIFICAR_PRESTEC =  "UPDATE prestec SET data_retorn = ?, prolongacio = true WHERE id = ?";
    
    public void crearPrestec(int idUsuari, int idLlibre, String dataPrestec, String dataRetorn, boolean prolongacio) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = AFEGIR_PRESTEC;
            ps = conn.prepareStatement(query);
            ps.setInt(1, idUsuari);
            ps.setInt(2, idLlibre);
            ps.setDate(3, Date.valueOf(dataPrestec));
            ps.setDate(4, dataRetorn != null ? Date.valueOf(dataRetorn) : null);
            ps.setBoolean(5, prolongacio);
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
     *
     * @param idUsuari
     * @return
     */
    public List<Prestec> llistaPrestecsActius(int idUsuari) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Prestec> prestecsActius = new ArrayList<>();

        try  {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = PRESTECS_ACTIUS;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idUsuari);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuariResultat = rs.getInt("id_usuari");
                int idLlibre = rs.getInt("id_llibre");
                Date dataPrestec = rs.getDate("data_prestec");
                Date dataRetorn = rs.getDate("data_retorn");
                boolean prolongacio = rs.getBoolean("prolongacio");

                Prestec prestec = new Prestec(id, idUsuariResultat, idLlibre, dataPrestec, dataRetorn, prolongacio);
                prestecsActius.add(prestec);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return prestecsActius;
        
    }
    public Prestec obtenirPrestecLlibre(int idLlibre) {
        Connection conn = null;
        PreparedStatement ps = null;

        try  {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = BUSCAR_PRESTEC;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idLlibre);

            ResultSet rs = statement.executeQuery();

            int id = rs.getInt("id");
            int idUsuariResultat = rs.getInt("id_usuari");
                idLlibre = rs.getInt("id_llibre");
            Date dataPrestec = rs.getDate("data_prestec");
            Date dataRetorn = rs.getDate("data_retorn");
            boolean prolongacio = rs.getBoolean("prolongacio");

            Prestec prestec = new Prestec(id, idUsuariResultat, idLlibre, dataPrestec, dataRetorn, prolongacio);
            return prestec;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
  
    public void modificarPrestec(int idLlibre, java.util.Date dataRenovacio) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        String sql = MODIFICAR_PRESTEC;
        Prestec prestec = obtenirPrestecLlibre(idLlibre);
        java.sql.Date aux = new java.sql.Date(dataRenovacio.getTime());
        ps = conn.prepareStatement(sql);
        ps.setDate(1, aux );
        ps.setInt(2, prestec.getId());
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
    
    public void tancarPrestec(int idPrestec, Date dataRetorn) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        String sql = TANCAR_PRESTEC;
        ps = conn.prepareStatement(sql);
        ps.setDate(1, dataRetorn);
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
    
    public void buscarPrestacActiu(int idLlibre, Date dataRetornoNova) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        String sql = BUSCAR_ACTIUS;
        ps = conn.prepareStatement(sql);
        ps.setDate(1, dataRetornoNova);
        ps.setInt(2, idLlibre);
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
