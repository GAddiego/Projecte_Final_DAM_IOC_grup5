
package BBDD;
    
import BBDD.consultes.SqlAvisos;
import BBDD.consultes.SqlComentaris;
import BBDD.consultes.SqlLlibres;
import BBDD.consultes.SqlPrestecs;
import BBDD.consultes.SqlReserves;
import BBDD.consultes.SqlUsuariIntern;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Configuracio;
import objectes.Eines;


/**
 *
 * @author Aleix
 */
public class SqlManager implements Sql{
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
                                        "foto BYTEA" +
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
                                        "pagines INTEGER NOT NULL," +
                                        "idioma TEXT NOT NULL," +
                                        "exemplar INTEGER NOT NULL," +
                                        "nota TEXT," +
                                        "titol_original TEXT NOT NULL," +
                                        "traductor TEXT," +
                                        "portada BYTES " +
                                        ")";
    static final String TAULA_PRESTEC = "CREATE TABLE prestec (" +
                                        "id SERIAL PRIMARY KEY, " +
                                        "id_usuari INTEGER REFERENCES usuaris(id), " +
                                        "id_llibre INTEGER REFERENCES llibres(id), "+
                                        "data_prestec DATE NOT NULL, "+
                                        "data_retorn DATE, "+
                                        "prolongacio BOOLEAN " +
                                        ")";
    static final String TAULA_COMENTARIS =  "CREATE TABLE Comentaris ("+
                                            "id SERIAL PRIMARY KEY, "+
                                            "id_llibre INTEGER NOT NULL REFERENCES llibres(id), "+
                                            "id_usuari INTEGER NOT NULL REFERENCES usuaris(id), "+
                                            "comentari TEXT NOT NULL, "+
                                            "data_creacio TIMESTAMP NOT NULL "+
                                            ")";
    static final String TAULA_RESERVES =    "CREATE TABLE reserves ( "+
                                            "id SERIAL PRIMARY KEY, "+
                                            "id_usuari INTEGER REFERENCES usuaris(id), "+
                                            "id_llibre INTEGER REFERENCES llibres(id), "+
                                            "data_reserva TEXT NOT NULL, "+
                                            "expiracio_reserva TEXT NOT NULL, "+
                                            "CONSTRAINT unique_reserva UNIQUE (id_usuari, id_llibre) "+
                                            ")";
    static final String TAULA_AVISOS = "CREATE TABLE avisos ( "+
                                            "id SERIAL PRIMARY KEY, "+
                                            "id_usuari INTEGER REFERENCES usuaris(id), "+
                                            "titol VARCHAR(100) NOT NULL, "+
                                            "missatge TEXT NOT NULL, "+
                                            "data_creacio TIMESTAMP NOT NULL, "+
                                            "llegit BOOLEAN NOT NULL, "+
                                            "id_creador INTEGER NOT NULL"+
                                            ")";
    
    static final String[][] TAULES = {{"usuaris", "llibres", "prestec", "comentaris", "reserves", "avisos"},{TAULA_USUARIS, TAULA_LLIBRES, TAULA_PRESTEC, TAULA_COMENTARIS, TAULA_RESERVES, TAULA_AVISOS}};

    Configuracio conf = new Configuracio();
    Eines eines = new Eines();
    
    public SqlComentaris comentaris = new SqlComentaris();
    public SqlLlibres llibres = new SqlLlibres();
    public SqlReserves reserves = new SqlReserves();
    public SqlPrestecs prestec = new SqlPrestecs();
    public SqlUsuariIntern uIntern = new SqlUsuariIntern();
    public SqlAvisos avisos = new SqlAvisos();
    

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
        System.out.println(TAULES[0][taula]);
        System.out.println("La taula " + TAULES[1][taula]);
        // Comprobar si la tabla ja existeix.
        DatabaseMetaData metadata = conn.getMetaData();
        ResultSet resultSet = metadata.getTables(null, null, TAULES[1][taula] , null);
        if (resultSet.next()) {
            System.out.println("La taula ja existeix.");
            return;
        }

        // Si la taula no existeix es crea.
        String sql = TAULES[1][taula];
        stmt.executeUpdate(sql);

        System.out.println("Taula " + TAULES[0][taula] +" creada correctament");
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


    
    
   


}

