package BBDD.consultes;

import BBDD.Sql;
import static BBDD.Sql.connexio;
import static BBDD.Sql.pasw;
import static BBDD.Sql.user;
import BBDD.SqlManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
import java.util.Date;
import objectes.Eines;
import objectes.Usuari;
import objectes.UsuariIntern;
import objectes.XifradorContrasenya;

/**
 * Classe que implementa l'interfície Sql i proporciona mètodes per gestionar els usuaris interns a través de consultes SQL.
 *
 * @author aleix
 */
public class SqlUsuariIntern implements Sql{
    static final String VERIFICAR_USUARI = "SELECT rol , pswd FROM usuaris WHERE nom_usuari = ?";
    static final String CREAR_USUARI = "INSERT INTO usuaris ( nom_usuari , rol , data_naixement, nom , primer_cognom , segon_cognom, email , data_alta,  multa,  foto, pswd ) VALUES ( ?, ?, ?, ?, ?,?, ?,?,?,?,?)";
    static final String RECUPERAR_USUARI = "SELECT id, nom_usuari, rol, nom,  primer_cognom, segon_cognom, email, data_alta, foto FROM usuaris WHERE nom_usuari = ? AND pswd = ?";
    static final String RECUPERAR_USUARI_TOT = "SELECT * FROM usuaris WHERE nom_usuari = ? ";
    static final String RECUPERAR_USUARI_TOT_ID = "SELECT * FROM usuaris WHERE id = ? ";
    static final String BORRAR_USUARI = "DELETE FROM usuaris WHERE nom_usuari = ?";
    static final String MODIFICAR_USUARI_OLD =  "UPDATE usuaris SET nom = ?, rol = ?, primer_cognom = ?, segon_cognom = ?, email = ?, data_alta = ?, data_baixa = ?, multa = ?, suspensio = ?, data_final_suspensio = ?, foto = ?, pswd = ? WHERE nom_usuari = ?";

    Eines eines = new Eines();
    
    /**
     * Verifica les credencials d'un usuari intern.
     *
     * @param usuari el nom d'usuari de l'usuari intern
     * @param pass   la contrasenya de l'usuari intern
     * @return el tipus d'usuari si les credencials són correctes, o null si no coincideixen
     */
    public String verificarUsuari(String usuari, byte[] pass ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tipusUsuari = null;
        byte[] pswd = null;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            stmt = conn.prepareStatement(VERIFICAR_USUARI);
            stmt.setString(1, usuari);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tipusUsuari = rs.getString("rol");
                 pswd = rs.getBytes("pswd");
            }
            XifradorContrasenya xC = new XifradorContrasenya();
            if(xC.ComprovarContrasenya(pass, pswd)){
                return tipusUsuari;
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

        return null;
    }
    
    /**
     * Crea un nou usuari intern.
     *
     * @param usuari l'usuari intern a crear
     */
    public void crearUsuari(UsuariIntern usuari)  {
        System.out.println(usuari.toString());
        Connection conn = null;
        PreparedStatement pstmt = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            // Preparar la sentencia SQL
            String query = CREAR_USUARI;
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, usuari.getUser());           
                pstmt.setString(2, usuari.getRol());
                pstmt.setDate(3, new java.sql.Date(usuari.getDataNaixement().getTime()));
                pstmt.setString(4, usuari.getNom());
                pstmt.setString(5, usuari.getPrimerCognom());
                pstmt.setString(6, usuari.getSegonCognom());
                pstmt.setString(7, usuari.getEmail());
                pstmt.setDate(8, new java.sql.Date(usuari.getDataAlta().getTime()));
                pstmt.setDouble(9,usuari.getMulta());
                pstmt.setBytes(10, usuari.getImageData());
                pstmt.setBytes(11, usuari.getPass());

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
    
    /**
     * Crea un nou usuari intern.
     *
     * @param usuari l'usuari intern a crear
     */
    public void crearUsuariExtens(UsuariIntern usuari)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            // Preparar la sentencia SQL
            String query = CREAR_USUARI;
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, usuari.getUser());           
                pstmt.setString(2, usuari.getRol());
                pstmt.setDate(3, new java.sql.Date(usuari.getDataNaixement().getTime()));
                pstmt.setString(4, usuari.getNom());
                pstmt.setString(5, usuari.getPrimerCognom());
                pstmt.setString(6, usuari.getSegonCognom());
                pstmt.setString(7, usuari.getEmail());
                pstmt.setDate(8, new java.sql.Date(usuari.getDataAlta().getTime()));
                pstmt.setDouble(9,usuari.getMulta());
                pstmt.setBytes(10, usuari.getImageData());
                pstmt.setBytes(11, usuari.getPass());
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
    
    /**
     * Obté un usuari intern pel seu nom d'usuari.
     *
     * @param nomUsuari el nom d'usuari de l'usuari intern a obtenir
     * @return l'usuari intern corresponent al nom d'usuari especificat, o null si no es troba cap usuari
     */
    public UsuariIntern getUsuari(String nomUsuari) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        UsuariIntern usuari = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            String query = RECUPERAR_USUARI_TOT;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                usuari = new UsuariIntern();
                usuari.setId(rs.getInt("id"));
                usuari.setUser(rs.getString("nom_usuari"));
                usuari.setPass(rs.getBytes("pswd"));
                usuari.setRol(rs.getString("rol"));
                usuari.setDataNaixement(rs.getDate("data_naixement"));
                usuari.setNom(rs.getString("nom"));
                usuari.setPrimerCognom(rs.getString("primer_cognom"));
                usuari.setSegonCognom(rs.getString("segon_cognom"));
                usuari.setEmail(rs.getString("email"));
                usuari.setDataAlta(rs.getDate("data_alta"));
                usuari.setDataBaixa(rs.getDate("data_baixa"));
                usuari.setMulta(rs.getFloat("multa"));
                usuari.setSuspensio(rs.getBoolean("suspensio"));
                usuari.setDataFinalSuspensio(rs.getDate("data_final_suspensio"));
                usuari.setImageData(rs.getBytes("foto"));
                
                System.out.println(usuari.toString());
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuari;
    }
    
    /**
     * Obté un usuari intern pel seu nom d'usuari (només el nom d'usuari).
     *
     * @param nomUsuari el nom d'usuari de l'usuari intern a obtenir
     * @return l'usuari intern corresponent al nom d'usuari especificat, o null si no es troba cap usuari
     */
    public UsuariIntern getUsuariNomesUserName(String nomUsuari) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        UsuariIntern usuari = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            String query = RECUPERAR_USUARI_TOT;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                usuari = new UsuariIntern();
                usuari.setId(rs.getInt("id"));
                usuari.setUser(rs.getString("nom_usuari"));
                usuari.setPass(rs.getBytes("pswd"));
                usuari.setRol(rs.getString("rol"));
                usuari.setDataNaixement(rs.getDate("data_naixement"));
                usuari.setNom(rs.getString("nom"));
                usuari.setPrimerCognom(rs.getString("primer_cognom"));
                usuari.setSegonCognom(rs.getString("segon_cognom"));
                usuari.setEmail(rs.getString("email"));
                usuari.setDataAlta(rs.getDate("data_alta"));
                usuari.setDataBaixa(rs.getDate("data_baixa"));
                usuari.setMulta(rs.getFloat("multa"));
                usuari.setSuspensio(rs.getBoolean("suspensio"));
                usuari.setDataFinalSuspensio(rs.getDate("data_final_suspensio"));
                usuari.setImageData(rs.getBytes("foto"));
                
                System.out.println(usuari.toString());
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuari;
    }
    /**
     * Obté un usuari intern pel seu nom d'usuari (només el nom d'usuari).
     *
     * @param nomUsuari el nom d'usuari de l'usuari intern a obtenir
     * @return l'usuari intern corresponent al nom d'usuari especificat, o null si no es troba cap usuari
     */
    
    public UsuariIntern getUsuariRetornaReduit(int idUsuari) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        UsuariIntern usuari = null;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);

            String query = RECUPERAR_USUARI_TOT_ID;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idUsuari);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                usuari = new UsuariIntern();
                usuari.setUser(rs.getString("nom_usuari"));
                usuari.setRol(rs.getString("rol"));
                usuari.setNom(rs.getString("nom"));
                usuari.setPrimerCognom(rs.getString("primer_cognom"));
                usuari.setSegonCognom(rs.getString("segon_cognom"));
                usuari.setEmail(rs.getString("email"));
                usuari.setImageData(rs.getBytes("foto"));
                
                System.out.println(usuari.toString());
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuari;
    }
    
    /**
    * Comprova si un usuari amb el nom d'usuari i contrasenya proporcionats existeix.
    *
    * @param nomUsuari el nom d'usuari de l'usuari
    * @param pass      la contrasenya de l'usuari
    * @return true si l'usuari existeix i la contrasenya és correcta, false altrament
    */
    public boolean existeixUsuari(String nomUsuari, byte[] pass) {
         Connection conn = null;
        PreparedStatement stmt = null;

        boolean existeix = false;
        try {
            conn = DriverManager.getConnection(connexio, user, pasw);
            XifradorContrasenya xC = new XifradorContrasenya();
            //String query = "SELECT COUNT(*) FROM usuaris WHERE nom_usuari = ?";
            String query = "SELECT * FROM usuaris WHERE nom_usuari = ? LIMIT 1";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomUsuari);
            ResultSet rs = stmt.executeQuery();
            
            
            if (rs.next()) {

                    byte[] b = (rs.getBytes("pswd"));
                    System.out.println(xC.ComprovarContrasenya(b,pass));
                    if(xC.ComprovarContrasenya(b,pass)){
                        return true;
                    }else{
                        return false;
                    }

            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existeix;
    }
    
    /**
    * Comprova si existeix un usuari amb el nom d'usuari especificat.
    *
    * @param nomUsuari el nom d'usuari a comprovar
    * @return true si l'usuari existeix, false altrament
    */
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

    /**
     * Elimina un usuari amb el nom d'usuari especificat.
     *
     * @param nomUsuari el nom d'usuari de l'usuari a eliminar
     */
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
    
    /* borrar
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
            stmt.setBytes(11, usuari.getImageData());
            stmt.setString(12, usuari.getUser());
            stmt.executeUpdate();
            
        }   catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    /**
    * Modifica les dades d'un usuari especificat.
    *
    * @param usuari l'usuari amb les dades modificades
    */
    public void modificarUsuari(UsuariIntern usuari){
    Connection conn = null;
    PreparedStatement stmt = null;

    try{
        conn = DriverManager.getConnection(connexio, user, pasw);
        Boolean primer = true;
        String consulta= "UPDATE usuaris SET ";
    
        if (usuari.getPass() != null && usuari.getPass().length != 0){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " pswd = ? ";

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

        if (usuari.getImageData() != null){
            if (!primer){
                consulta += ", ";

            }else{
                primer = false;
            }
            consulta += " foto = ? ";
        }
            
       

        consulta += " WHERE nom_usuari = '" + usuari.getUser() + "'";
            System.out.println(consulta);
            
            stmt = conn.prepareStatement(consulta);
            stmt.setBytes(1, usuari.getPass());
             if (usuari.getImageData() != null){
                 stmt.setBytes(2, usuari.getImageData());
             }
            
            
            stmt.executeUpdate();
            
        }   catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    
    /**
    * Busca usuaris amb els filtres especificats.
    *
    * @param args un array de cadenes amb els valors dels filtres
    * @return una llista d'usuaris que coincideixen amb els filtres
    * @throws SQLException    si hi ha un error en la consulta SQL
    * @throws ParseException  si hi ha un error en analitzar una data
    */
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
                    rs.getBytes("foto"),
                    rs.getBytes("pswd")
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
    /* borrar
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
                    rs.getBytes("foto")
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
    }*/
    
    /**
    * Modifica la foto d'un usuari a la base de dades.
    *
    * @param usuari L'usuari intern amb la nova imatge de perfil i l'ID de l'usuari a modificar.
    */
    public void modificarFoto(UsuariIntern usuari){
    
    // Inicializar variables de conexión
    Connection conn = null;
    PreparedStatement pstmt = null;
    
        try {
            // Establecer conexión con la base de datos
            conn = DriverManager.getConnection(connexio, user, pasw);
            
            // Preparar sentencia SQL para actualizar el campo "foto" de la tabla "usuaris"
            String sql = "UPDATE usuaris SET foto = ? WHERE id = ?";
            
            // Leer archivo de imagen en bytes
            //File archivo = new File("Imatges_usuaris/DefaultUser.png");
            //byte[] foto = new byte[(int) archivo.length()];
            
            String query = sql;
            pstmt = conn.prepareStatement(query);
                pstmt.setBytes(1, usuari.getImageData());
                pstmt.setInt(2, usuari.getId()); // reemplaza el valor "1" por el ID del usuario que quieres modificar
            
            // Ejecutar sentencia SQL y verificar el número de filas afectadas
            int filasModificadas = pstmt.executeUpdate();
            if (filasModificadas > 0) {
                System.out.println("Se ha modificado la foto del usuario correctamente.");
            } else {
                System.out.println("No se ha encontrado el usuario con ese ID.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la sentencia SQL: " + e.getMessage());       
        } finally {
            // Cerrar objetos de conexión y sentencia SQL
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
            }
        }


}
}
