package BBDD.consultes;

import BBDD.Sql;
import static BBDD.Sql.connexio;
import static BBDD.Sql.pasw;
import static BBDD.Sql.user;
import BBDD.SqlManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;
import objectes.Usuari;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class SqlUsuariIntern implements Sql{
    static final String VERIFICAR_USUARI = "SELECT tipus FROM usuaris WHERE usuari = ? AND contrasenya = ?";
    static final String CREAR_USUARI = "INSERT INTO usuaris (nom_usuari, nom, contrasenya, rol, primer_cognom, segon_cognom, email, data_alta ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    static final String RECUPERAR_USUARI = "SELECT id, nom_usuari, contrasenya, rol, nom,  primer_cognom, segon_cognom, email, data_alta, ruta_foto FROM usuaris WHERE nom_usuari = ? AND contrasenya = ?";
    static final String BORRAR_USUARI = "DELETE FROM usuaris WHERE nom_usuari = ?";
    static final String MODIFICAR_USUARI_OLD =  "UPDATE usuaris SET nom = ?, rol = ?, primer_cognom = ?, segon_cognom = ?, email = ?, data_alta = ?, data_baixa = ?, multa = ?, suspensio = ?, data_final_suspensio = ?, ruta_foto = ? WHERE nom_usuari = ?";

    Eines eines = new Eines();
    
    public String verificarUsuari(String usuari, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tipusUsuari = null;

        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            stmt = conn.prepareStatement(VERIFICAR_USUARI);
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

public void crearUsuari(UsuariIntern usuari)  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);

        // Preparar la sentencia SQL
        String query = CREAR_USUARI;
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
public void crearUsuari2(UsuariIntern usuari)  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
        conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.219:5432/biblioteca", user, pasw);

        // Preparar la sentencia SQL
        String query = CREAR_USUARI;
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
    public UsuariIntern getUsuari(String nomUsuari, String pass) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        UsuariIntern usuari = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            String query = RECUPERAR_USUARI;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuari = new UsuariIntern();
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
                usuari.setRutaFoto(rs.getString("ruta_foto"));

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
    
    public boolean existeixNomUsuari(String nomUsuari) {
         Connection conn = null;
        PreparedStatement stmt = null;
        
        boolean existeix = false;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            
            String query = "SELECT COUNT(*) FROM usuaris WHERE nom_usuari = ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
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

    public  void eliminarUsuari(String nomUsuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {       
            conn = DriverManager.getConnection(connexio, user, pasw);

            String query = BORRAR_USUARI ;
            stmt = conn.prepareStatement(query);

            stmt.setString(1, nomUsuari);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void modificarUsuariOld(UsuariIntern usuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {       
            conn = DriverManager.getConnection(connexio, user, pasw);
            
            String query = MODIFICAR_USUARI_OLD;
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, usuari.getNom());
            stmt.setString(2, usuari.getRol());
            stmt.setString(3, usuari.getPrimerCognom());
            stmt.setString(4, usuari.getSegonCognom());
            stmt.setString(5, usuari.getEmail());
            stmt.setString(6, eines.convertirStringData(usuari.getDataAlta()));
            stmt.setString(7, eines.convertirStringData(usuari.getDataBaixa()));
            stmt.setFloat(8, (float) usuari.getMulta());
            stmt.setBoolean(9, usuari.isSuspensio());
            stmt.setString(10, eines.convertirStringData(usuari.getDataFinalSuspensio()));
            stmt.setString(11, usuari.getRutaFoto());
            stmt.setString(12, usuari.getUser());
            stmt.executeUpdate();
            
        }   catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void modificarUsuari(UsuariIntern usuari){
    Connection conn = null;
    PreparedStatement stmt = null;

    try{
        conn = DriverManager.getConnection(connexio, user, pasw);
        Boolean primer = true;
        String consulta= "UPDATE usuaris SET ";
    
        if (usuari.getPass() != null && !usuari.getPass().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " contrasenya = '" + usuari.getPass() + "'";

        }

        if (usuari.getNom() != null && !usuari.getNom().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " nom = '" + usuari.getNom() + "'";
        }

        if (usuari.getPrimerCognom() != null && !usuari.getPrimerCognom().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " primer_cognom = '" + usuari.getPrimerCognom() + "'";
        }

        if (usuari.getSegonCognom() != null && !usuari.getSegonCognom().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " segon_cognom = '" + usuari.getSegonCognom() + "'";
        }

        if (usuari.getRol() != null && !usuari.getRol().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " rol = '" + usuari.getRol() + "'";
        }

        if (eines.convertirStringData(usuari.getDataNaixement()) != null && !eines.convertirStringData(usuari.getDataNaixement()).isEmpty()){
            if (!primer){
                consulta += ",";

            }else{
                primer = false;
            }
            consulta += " data_naixement = '" + eines.convertirStringData(usuari.getDataNaixement()) + "'";
        }


        if (usuari.getEmail() != null && !usuari.getEmail().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " email = '" + usuari.getEmail() + "'";
        }

        if (eines.convertirStringData(usuari.getDataBaixa()) != null && !eines.convertirStringData(usuari.getDataBaixa()).isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " data_baixa = '" + eines.convertirStringData(usuari.getDataBaixa()) + "'";
        }


        if (usuari.getMulta() > 0){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " multa = " + (float) usuari.getMulta() + " ";
        }

        //if (usuari.isSuspensio()!= null && !usuari.getSuspensio().isEmpty()){
        //    consulta += ", contrasenya = '%" + usuari.isSuspensio() + "%'";
        //}
        
        if (eines.convertirStringData(usuari.getDataFinalSuspensio()) != null && !eines.convertirStringData(usuari.getDataFinalSuspensio()).isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " final_suspensio = '" + eines.convertirStringData(usuari.getDataFinalSuspensio()) + "'";
        }

        if (usuari.getRutaFoto() != null && !usuari.getRutaFoto().isEmpty()){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " ruta_foto = '" + usuari.getRutaFoto() + "'";
        }

        consulta += " WHERE nom_usuari = '" + usuari.getUser() + "'";
            System.out.println(consulta);
            stmt = conn.prepareStatement(consulta);
            stmt.executeUpdate();
            
        }   catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    
    
    public List<Usuari> buscarUsuaris(String args[]) throws SQLException, ParseException {
        
        List<Usuari> usuaris = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
            
        // Creació de la consulta
        String consulta = "SELECT * FROM usuaris WHERE";
        boolean filtres = false;
        if (args[0] != null && !args[0].isEmpty()) {
            consulta += " nom_usuari ILIKE '%" + args[0] + "%'";
            filtres = true;
        }
        if (args[1] != null && !args[1].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " nom LIKE '%" + args[1] + "%'";
            filtres = true;
        }
        if (args[2] != null && !args[2].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " primer_cognom LIKE '%" + args[2] + "%'";
        }
        if (args[3] != null && !args[3].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " segon_cognom LIKE '%" + args[3] + "%'";
        }
        if (args[4] != null && !args[4].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " rol LIKE '%" + args[4] + "%'";
        }
        if (args[5] != null && !args[5].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " data_naixement LIKE '%" + args[5] + "%'";
        }
        if (args[6] != null && !args[6].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " email LIKE '%" + args[6] + "%'";
        }
        // Execució de la consulta
        conn = DriverManager.getConnection(connexio, user, pasw);
        stmt = conn.createStatement();
        System.out.println(consulta);
        ResultSet rs = stmt.executeQuery(consulta);
        // Guardem els resultats
        while (rs.next()) {
            UsuariIntern usuari = new UsuariIntern(
                    rs.getInt("id"),
                    rs.getString("nom_usuari"),
                    rs.getString("rol"),
                    eines.convertirDataString(rs.getString("data_naixement")),
                    rs.getString("nom"),
                    rs.getString("primer_cognom"),
                    rs.getString("segon_cognom"),
                    rs.getString("email"),
                    eines.convertirDataString(rs.getString("data_alta")),                                            
                    rs.getFloat("multa"),
                    rs.getBoolean("suspensio"),
                    eines.convertirDataString(rs.getString("data_final_suspensio")),               
                    rs.getString("ruta_foto")
            );
            System.out.println("UsuariIntern : " + usuari.toString());
            Usuari u = new Usuari(usuari); 
            System.out.println("Usuari : " + u.toString());
            usuaris.add(u);
        }
        // Tancar les connexions i recursos
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
        
        return usuaris;
    }
    
    public List<Usuari> buscarUsuarisFoto(String args[]) throws SQLException, ParseException {
        
        List<Usuari> usuaris = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
            
        // Creació de la consulta
        String consulta = "SELECT * FROM usuaris WHERE";
        boolean filtres = false;
        if (args[0] != null && !args[0].isEmpty()) {
            consulta += " nom_usuari ILIKE '%" + args[0] + "%'";
            filtres = true;
        }
        if (args[1] != null && !args[1].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " nom LIKE '%" + args[1] + "%'";
            filtres = true;
        }
        if (args[2] != null && !args[2].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " primer_cognom LIKE '%" + args[2] + "%'";
        }
        if (args[3] != null && !args[3].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " segon_cognom LIKE '%" + args[3] + "%'";
        }
        if (args[4] != null && !args[4].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " rol LIKE '%" + args[4] + "%'";
        }
        if (args[5] != null && !args[5].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " data_naixement LIKE '%" + args[5] + "%'";
        }
        if (args[6] != null && !args[6].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " email LIKE '%" + args[6] + "%'";
        }
        if (args[7] != null && !args[7].isEmpty()) {
            consulta += (filtres ? " AND" : "") + " multa = " + args[7] ;
        }
        // Execució de la consulta
        conn = DriverManager.getConnection(connexio, user, pasw);
        stmt = conn.createStatement();
        System.out.println(consulta);
        ResultSet rs = stmt.executeQuery(consulta);
        // Guardem els resultats
        while (rs.next()) {
            UsuariIntern usuari = new UsuariIntern(
                    rs.getInt("id"),
                    rs.getString("nom_usuari"),
                    rs.getString("rol"),
                    eines.convertirDataString(rs.getString("data_naixement")),
                    rs.getString("nom"),
                    rs.getString("primer_cognom"),
                    rs.getString("segon_cognom"),
                    rs.getString("email"),
                    eines.convertirDataString(rs.getString("data_alta")),                                            
                    rs.getFloat("multa"),
                    rs.getBoolean("suspensio"),
                    eines.convertirDataString(rs.getString("data_final_suspensio")),               
                    rs.getString("ruta_foto")
            );
            if(eines.comprovarRuta(usuari.getRutaFoto())){
                try {
                    usuari.setImageData(usuari.getRutaFoto());
                } catch (IOException ex) {
                    Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    usuari.setImageData(eines.rutaImatgeUsuariDefault);
                } catch (IOException ex) {
                    Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("UsuariIntern : " + usuari.toString());
            Usuari u = new Usuari(usuari);          
            System.out.println("Usuari : " + u.toString());
            usuaris.add(u);
        }
        // Tancar les connexions i recursos
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
        
        return usuaris;
    }
}
