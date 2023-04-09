
package BBDD;
    
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import objectes.Configuracio;
import objectes.Usuari;

/**
 *
 * @author Aleix
 */
public class SqlManager {
    Configuracio conf = new Configuracio();
    
    String connexio = conf.getDada("bbdd.url");
    String user = conf.getDada("bbdd.usuari");
    String pasw = conf.getDada("bbdd.contrasenya");
    

public String verificarUsuari(String usuari, String pass) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String tipusUsuari = null;

    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        stmt = conn.prepareStatement("SELECT tipus FROM usuaris WHERE usuari = ? AND contrasenya = ?");
        stmt.setString(1, usuari);
        stmt.setString(2, pass);
        rs = stmt.executeQuery();

        if (rs.next()) {
            tipusUsuari = rs.getString("tipus");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return tipusUsuari;
}

public void crearTaulaUsuaris() {
    Connection conn = null;
    Statement stmt = null;

    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        stmt = conn.createStatement();

        // Comprobar si la tabla ya existe
        DatabaseMetaData metadata = conn.getMetaData();
        ResultSet resultSet = metadata.getTables(null, null, "usuaris", null);
        if (resultSet.next()) {
            System.out.println("La taula ja existeix.");
            return;
        }

        // Si la tabla no existe, crearla
        String sql = "CREATE TABLE usuaris (" +
                     "id SERIAL PRIMARY KEY," +
                     "nom_usuari TEXT UNIQUE," +
                     "nom TEXT," +
                     "contrasenya TEXT," +
                     "rol TEXT," +
                     "data_naixement TEXT," +
                     "primer_cognom TEXT," +
                     "segon_cognom TEXT," +
                     "email TEXT," +
                     "data_alta TEXT," +
                     "data_baixa TEXT," +
                     "multa FLOAT," +
                     "suspensio BOOLEAN," +
                     "data_final_suspensio TEXT," +
                     "ruta_foto TEXT" +
                     ")";
        stmt.executeUpdate(sql);

        System.out.println("Taula creada correctament");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


public void crearUsuari(Usuari usuari)  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);

        // Preparar la sentencia SQL
        String query = "INSERT INTO usuaris (nom_usuari, nom, contrasenya, rol, primer_cognom, segon_cognom, email, data_alta ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usuari.getUser());
            pstmt.setString(2, usuari.getNom());
            pstmt.setString(3, usuari.getPass());
            pstmt.setString(4, usuari.getRol());
            pstmt.setString(5, usuari.getPrimerCognom());
            pstmt.setString(6, usuari.getSegonCognom());
            pstmt.setString(7, usuari.getEmail());
            pstmt.setString(8, formatter.format(usuari.getDataAlta()));

        // Ejecutar la sentencia SQL
        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Usuari creat correctament.");
        } else {
            System.out.println("No s'ha pogut crear l'usuari.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    public Usuari getUsuari(String nomUsuari, String pass) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Usuari usuari = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            
            String query = "SELECT id, nom_usuari, contrasenya, rol, nom,  primer_cognom, segon_cognom, email, data_alta  FROM usuaris WHERE nom_usuari = ? AND contrasenya = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuari = new Usuari();
                usuari.setId(rs.getInt("id"));
                usuari.setUser(rs.getString("nom_usuari"));
                usuari.setPass(rs.getString("contrasenya"));
                usuari.setRol(rs.getString("rol"));
                // usuari.setDataNaixement(formatter.parse(rs.getString("data_naixement")));
                usuari.setNom(rs.getString("nom"));
                usuari.setPrimerCognom(rs.getString("primer_cognom"));
                usuari.setSegonCognom(rs.getString("segon_cognom"));
                usuari.setEmail(rs.getString("email"));
                usuari.setDataAlta(formatter.parse(rs.getString("data_alta")));
                //usuari.setDataBaixa(formatter.parse(rs.getString("data_baixa")));
                //usuari.setMulta(rs.getFloat("multa"));
                //usuari.setSuspensio(rs.getBoolean("suspensio"));
                //usuari.setDataFinalSuspensio(formatter.parse(rs.getString("data_final_suspensio")));
                //usuari.setRutaFoto(rs.getString("ruta_foto"));

            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuari;
    }

    public boolean existeixUsuari(String nomUsuari, String contrasenya) {
         Connection conn = null;
        PreparedStatement stmt = null;
        
        boolean existeix = false;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            
            String query = "SELECT COUNT(*) FROM usuaris WHERE nom_usuari = ? AND contrasenya = ?";
            stmt = conn.prepareStatement(query);
            System.out.println("nomUsuari: "+ nomUsuari + " pass: " + contrasenya );
            stmt.setString(1, nomUsuari);
            stmt.setString(2, contrasenya);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    existeix = true;
                }
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existeix;
    }


}





    

