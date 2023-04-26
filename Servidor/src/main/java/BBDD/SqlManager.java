
package BBDD;
    
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Configuracio;
import objectes.Eines;
import objectes.Llibre;
import objectes.UsuariIntern;
import objectes.Usuari;

/**
 *
 * @author Aleix
 */
public class SqlManager {
    static final String TAULA_USUARIS = "CREATE TABLE usuaris (" +
                                        "id SERIAL PRIMARY KEY," +
                                        "nom_usuari TEXT UNIQUE NOT NULL," +
                                        "nom TEXT NOT NULL," +
                                        "contrasenya TEXT NOT NULL," +
                                        "rol TEXT NOT NULL," +
                                        "data_naixement TEXT," +
                                        "primer_cognom TEXT NOT NULL," +
                                        "segon_cognom TEXT," +
                                        "email TEXT NOT NULL," +
                                        "data_alta TEXT NOT NULL," +
                                        "data_baixa TEXT," +
                                        "multa FLOAT," +
                                        "suspensio BOOLEAN," +
                                        "data_final_suspensio TEXT," +
                                        "ruta_foto TEXT" +
                                        ")";
    static final String TAULA_LLIBRES = "CREATE TABLE llibres (" +
                                        "id SERIAL PRIMARY KEY," +
                                        "titol TEXT NOT NULL," +
                                        "autor TEXT NOT NULL," +
                                        "isbn TEXT UNIQUE NOT NULL," +
                                        "editorial TEXT NOT NULL," +
                                        "any_publicacio INTEGER NOT NULL," +
                                        "descripcio TEXT NOT NULL," +
                                        "sinopsi TEXT NOT NULL," +
                                        "illustrador TEXT," +
                                        "ruta_portada TEXT," +
                                        "pagines INTEGER NOT NULL," +
                                        "idioma TEXT NOT NULL," +
                                        "exemplar INTEGER NOT NULL," +
                                        "nota TEXT," +
                                        "titol_original TEXT NOT NULL," +
                                        "traductor TEXT," +
                                        "llista_reserva INTEGER[][]" +
                                        ")";
    static final String[][] TAULES = {{"usuaris", "llibres"},{TAULA_USUARIS, TAULA_LLIBRES}};
    static final String VERIFICAR_USUARI = "SELECT tipus FROM usuaris WHERE usuari = ? AND contrasenya = ?";
    static final String CREAR_USUARI = "INSERT INTO usuaris (nom_usuari, nom, contrasenya, rol, primer_cognom, segon_cognom, email, data_alta ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    static final String RECUPERAR_USUARI = "SELECT id, nom_usuari, contrasenya, rol, nom,  primer_cognom, segon_cognom, email, data_alta  FROM usuaris WHERE nom_usuari = ? AND contrasenya = ?";
    static final String BORRAR_USUARI = "DELETE FROM usuaris WHERE nom_usuari = ?";
    static final String CREAR_LLIBRE = "INSERT INTO llibres (titol, autor, isbn, editorial, any_publicacio, descripcio, sinopsi, illustrador, ruta_portada, pagines, idioma, exemplar, nota, titol_original, traductor) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
    static final String BORRAR_LLIBRE = "DELETE FROM llibres WHERE isbn=?"  ;
    static final String MODIFICAR_LLIBRE = "UPDATE llibres SET titol=?, autor=?, editorial=?, any_publicacio=?, descripcio=?, sinopsi=?, illustrador=?, ruta_portada=?, pagines=?, idioma=?, exemplar=?, nota=?, titol_original=?, traductor=? WHERE isbn=?" ;
    static final String MODIFICAR_USUARI_OLD =  "UPDATE usuaris SET nom = ?, rol = ?, primer_cognom = ?, segon_cognom = ?, email = ?, data_alta = ?, data_baixa = ?, multa = ?, suspensio = ?, data_final_suspensio = ?, ruta_foto = ? WHERE nom_usuari = ?";
    
    Configuracio conf = new Configuracio();
    Eines eines = new Eines();
    
    String connexio = conf.getDada("bbdd.url");
    String user = conf.getDada("bbdd.usuari");
    String pasw = conf.getDada("bbdd.contrasenya");

public void provarConexio(){
    Connection conn = null;
    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
    } catch (SQLException ex) {
        Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    if (conn != null)
   {
      System.out.println("Connection Successful! Enjoy. Now it's time to push data");
   }
   else
   {
      System.out.println("Failed to make connection!");
   }
}

public void crearTaula(int taula) {
    Connection conn = null;
    Statement stmt = null;

    try {
        conn = DriverManager.getConnection(connexio, user, pasw);
        stmt = conn.createStatement();
        System.out.println("La taula " + TAULES[taula][0]);
        // Comprobar si la tabla ja existeix.
        DatabaseMetaData metadata = conn.getMetaData();
        ResultSet resultSet = metadata.getTables(null, null, TAULES[taula][0] , null);
        if (resultSet.next()) {
            System.out.println("La taula ja existeix.");
            return;
        }

        // Si la taula no existeix es crea.
        String sql = TAULES[taula][1];
        stmt.executeUpdate(sql);

        System.out.println("Taula " + TAULES[taula][0] +" creada correctament");
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
    
    // Mètode per crear un registre de llibre
    public void crearLlibre(Llibre llibre) throws SQLException  {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connectar amb la base de dades
            conn = DriverManager.getConnection(connexio, user, pasw);
            
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
            stmt.setString(9, llibre.getRutaPortada());
            stmt.setInt(10, llibre.getPagines());
            stmt.setString(11, llibre.getIdioma());
            stmt.setInt(12, llibre.getExemplar());
            stmt.setString(13, llibre.getNota());
            stmt.setString(14, llibre.getTitolOriginal());
            stmt.setString(15, llibre.getTraductor());
            //stmt.setInt(16, llibre.getLlistaReserva());

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
            stmt.setString(8, llibre.getRutaPortada());
            stmt.setInt(9, llibre.getPagines());
            stmt.setString(10, llibre.getIdioma());
            stmt.setInt(11, llibre.getExemplar());
            stmt.setString(12, llibre.getNota());
            stmt.setString(13, llibre.getTitolOriginal());
            stmt.setString(14, llibre.getTraductor());
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

    // Mètode per esborrar un registre de llibre
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
                    rs.getString("ruta_portada"),
                    rs.getInt("pagines"),
                    rs.getString("idioma"),
                    rs.getInt("exemplar"),
                    rs.getString("nota"),
                    rs.getString("titol_original"),
                    rs.getString("traductor")
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


}





    

