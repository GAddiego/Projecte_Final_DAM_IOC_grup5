package BBDD.consultes;

import BBDD.Sql;
import static BBDD.Sql.connexio;
import static BBDD.Sql.pasw;
import static BBDD.Sql.user;
import BBDD.SqlManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Llibre;
import objectes.UsuariIntern;

/**
 * Classe que implementa les operacions de consulta SQL relacionades amb els llibres.
 *
 * @author aleix
 */
public class SqlLlibres implements Sql{
    static final String CREAR_LLIBRE = "INSERT INTO llibres (titol, autor, isbn, editorial, any_publicacio, descripcio, sinopsi, illustrador, pagines, idioma, exemplar, nota, titol_original, traductor, imatge) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static final String BORRAR_LLIBRE = "DELETE FROM llibres WHERE isbn=?"  ;
    static final String MODIFICAR_LLIBRE = "UPDATE llibres SET titol = ?, autor = ?,editorial = ?,any_publicacio=?,descripcio=?,sinopsi=?,illustrador=?,pagines=?,idioma=?,exemplar=?,nota=?,titol_original=?,traductor=?,imatge=? WHERE isbn=?" ;
    static final String BUSCAR_LLIBRE_ID =  "SELECT * FROM llibres WHERE id = ?";
    static final String MODIFICAR_LLIBRE_IMATGE = "UPDATE llibres SET imatge = ? WHERE id = ?";
    
    /**
     * Mètode per crear un registre de llibre.
     * @param llibre L'objecte Llibre que s'ha de crear.
     */
    public void crearLlibre(Llibre llibre) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connectar amb la base de dades
            conn = DriverManager.getConnection(connexio, user, pasw);
            System.out.println(llibre.getPortada());
            // Preparar la consulta SQL
            String query = CREAR_LLIBRE;
            stmt = conn.prepareStatement(query);
            
            // Establir els valors dels paràmetres de la consulta
            stmt.setString(1, llibre.getTitol());
            stmt.setString(2, llibre.getAutor());
            stmt.setString(3, llibre.getIsbn());
            stmt.setString(4, llibre.getEditorial());
            stmt.setInt(5, llibre.getAnyPublicacio());
            stmt.setString(6, llibre.getDescripcio());
            stmt.setString(7, llibre.getSinopsi());
            stmt.setString(8, llibre.getIllustrador());
            stmt.setInt(9, llibre.getPagines());
            stmt.setString(10, llibre.getIdioma());
            stmt.setInt(11, llibre.getExemplar());
            stmt.setString(12, llibre.getNota());
            stmt.setString(13, llibre.getTitolOriginal());
            stmt.setString(14, llibre.getTraductor());
            stmt.setBytes(15, llibre.getPortada());

            // Executar la consulta
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Tancar les connexions i recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Mètode per modificar un registre de llibre.
     * @param llibre L'objecte Llibre que s'ha de modificar.
     * @throws SQLException Si es produeix un error durant l'execució de la consulta SQL.
     */
    public void modificarLlibre(Llibre llibre) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connectar amb la base de dades
            conn = DriverManager.getConnection(connexio, user, pasw);

            // Preparar la consulta SQL
            String query = MODIFICAR_LLIBRE ;
            stmt = conn.prepareStatement(query);

            // Establir els valors dels paràmetres de la consulta
            stmt.setString(1, llibre.getTitol());
            stmt.setString(2, llibre.getAutor());
            stmt.setString(3, llibre.getEditorial());
            stmt.setInt(4, llibre.getAnyPublicacio());
            stmt.setString(5, llibre.getDescripcio());
            stmt.setString(6, llibre.getSinopsi());
            stmt.setString(7, llibre.getIllustrador());
            stmt.setInt(8, llibre.getPagines());
            stmt.setString(9, llibre.getIdioma());
            stmt.setInt(10, llibre.getExemplar());
            stmt.setString(11, llibre.getNota());
            stmt.setString(12, llibre.getTitolOriginal());
            stmt.setString(13, llibre.getTraductor());
            stmt.setBytes(14,  llibre.getPortada());
            stmt.setString(15, llibre.getIsbn());
            
            // Executar la consulta
            stmt.executeUpdate();
        } finally {
            // Tancar les connexions i recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    /**
    * Mètode per modificar la imatge d'un llibre.
    * @param llibre L'objecte Llibre que conté la nova imatge.
    */
    public void modificarLlibreImatge(Llibre llibre) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connectar amb la base de dades
            conn = DriverManager.getConnection(connexio, user, pasw);

            // Preparar la consulta SQL
            String query = MODIFICAR_LLIBRE_IMATGE ;
            stmt = conn.prepareStatement(query);
            System.out.println(llibre.getPortada());
            // Establir els valors dels paràmetres de la consulta
            stmt.setBytes(1,  llibre.getPortada());
            stmt.setInt(2, 1);
            // Executar la consulta
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Tancar les connexions i recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SqlLlibres.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
    * Mètode per esborrar un registre de llibre.
    * @param llibre L'objecte Llibre que s'ha d'esborrar.
    * @throws SQLException Si es produeix un error durant l'execució de la consulta SQL.
    */
    public void esborrarLlibre(Llibre llibre) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connectar amb la base de dades
            conn = DriverManager.getConnection(connexio, user, pasw);

            // Preparar la consulta SQL
            String query = BORRAR_LLIBRE;
            stmt = conn.prepareStatement(query);

            // Establir el valor del paràmetre de la consulta
            stmt.setString(1, llibre.getIsbn()); // Establir l'ID del llibre a esborrar

            // Executar la consulta
            stmt.executeUpdate();
        } finally {
            // Tancar les connexions i recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    /**
    * Mètode per buscar llibres basat en diversos criteris de cerca.
    * @param args Un array de strings que conté els criteris de cerca.
    * @return Una llista de llibres que coincideixen amb els criteris de cerca.
    * @throws SQLException Si es produeix un error durant l'execució de la consulta SQL.
    */
    public List<Llibre> buscarLlibres(String args[]) throws SQLException {
        
        List<Llibre> llibres = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
            
        try {           
            // Creació de la consulta
            String consulta = "SELECT * FROM llibres WHERE";
            boolean filtres = false;

            if (args[0] != null && !args[0].isEmpty()) {
                consulta += " titol ILIKE '%" + args[0] + "%'";
                filtres = true;
            }
            if (args[1] != null && !args[1].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " autor ILIKE '%" + args[1] + "%'";
                filtres = true;
            }
            if (args[2] != null && !args[2].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " isbn ILIKE '%" + args[2] + "%'";
            }
            if (args[3] != null && !args[3].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " editorial ILIKE '%" + args[3] + "%'";
            }
            if (args[4] != null && !args[4].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " any_publicacio ILIKE '%" + args[4] + "%'";
            }
            if (args[5] != null && !args[5].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " descripcio ILIKE '%" + args[5] + "%'";
            }
            if (args[6] != null && !args[6].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " sinopsi ILIKE '%" + args[6] + "%'";
                filtres = true;
            }
            if (args[7] != null && !args[7].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " illustrador ILIKE '%" + args[7] + "%'";
            }
            if (args[8] != null && !args[8].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " pagines ILIKE '%" + args[8] + "%'";
            }
            if (args[9] != null && !args[9].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " idioma ILIKE '%" + args[9] + "%'";
            }
            if (args[10] != null && !args[10].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " exemplar ILIKE '%" + args[10] + "%'";
            }
            if (args[11] != null && !args[11].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " nota ILIKE '%" + args[11] + "%'";
                filtres = true;
            }
            if (args[12] != null && !args[12].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " titol_original ILIKE '%" + args[12] + "%'";
            }
            if (args[13] != null && !args[13].isEmpty()) {
                consulta += (filtres ? " AND" : "") + " traductor ILIKE '%" + args[13] + "%'";
            }

            // Execució de la consulta
            conn = DriverManager.getConnection(connexio, user, pasw);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            // Guardem els resultats
            while (rs.next()) {
                Llibre llibre = new Llibre(
                    rs.getInt("id"),
                    rs.getString("titol"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getString("editorial"),
                    rs.getInt("any_publicacio"),
                    rs.getString("descripcio"),
                    rs.getString("sinopsi"),
                    rs.getString("illustrador"),
                    rs.getInt("pagines"),
                    rs.getString("idioma"),
                    rs.getInt("exemplar"),
                    rs.getString("nota"),
                    rs.getString("titol_original"),
                    rs.getString("traductor"),
                    rs.getBytes("imatge")
                );
                llibres.add(llibre);
                System.out.println("Llibre : " + llibre.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);         
        } finally {
            // Tancar les connexions i recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return llibres;
    }
    
    /**
    * Mètode per buscar un llibre per ID.
    * @param id L'ID del llibre a cercar.
    * @return L'objecte Llibre corresponent a l'ID especificat, o null si no es troba cap coincidència.
    */
    public Llibre buscarLlibreid(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        UsuariIntern usuari = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            String query = BUSCAR_LLIBRE_ID;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                Llibre llibre = new Llibre();
                llibre.setId(rs.getInt("id"));
                llibre.setTitol(rs.getString("titol"));
                llibre.setAutor(rs.getString("autor"));
                llibre.setIsbn(rs.getString("isbn"));
                llibre.setEditorial(rs.getString("editorial"));
                llibre.setAnyPublicacio(rs.getInt("any_publicacio"));
                llibre.setDescripcio(rs.getString("descripcio"));
                llibre.setSinopsi(rs.getString("sinopsi"));
                llibre.setIllustrador(rs.getString("illustrador"));
                llibre.setPagines(rs.getInt("pagines"));
                llibre.setIdioma(rs.getString("idioma"));
                llibre.setExemplar(rs.getInt("exemplar"));
                llibre.setNota(rs.getString("nota"));
                llibre.setTitolOriginal(rs.getString("titol_original"));
                llibre.setTraductor(rs.getString("traductor"));
                llibre.setPortada(rs.getBytes("imatge"));
                System.out.println(llibre.getPortada());
                return llibre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
        