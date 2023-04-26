package suport;

import BBDD.SqlManager;
import objectes.Usuari;
import com.github.javafaker.Faker;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Llibre;
import objectes.UsuariIntern;



/**
 *
 * @author aleix
 */
public class ConfiguracioBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SqlManager sql = new SqlManager();
        CreadorFaker cf = new CreadorFaker();
        
        //Llibre llibre = cf.crearLlibre();
        //sql.crearLlibre(llibre);
        //llibre.setDescripcio("modificat");
        //sql.modificarLlibre(llibre);
        
        UsuariIntern ui = cf.crearUsuariIntern();
        sql.crearUsuari(ui);
        ui.setMulta(1000);
        sql.modificarUsuari(ui);
        
        //sql.crearTaula(1);
        //Usuari u = new Usuari("algibo","pass1","admin","aleix","giralt","borrell","aleixgibo@gmail.com");
        //Usuari u = new Usuari("pepito","pass2","bibliotecaria","pepe","molins","estruch","pepemoes@gmail.com");
        //sql.crearUsuari(u);
        //sql.crearUsuari("marc", "marc1234", "professor", "1992-10-15");
        //sql.crearUsuari("pere", "pere1234", "alumne", "2018-09-01");
        //crear50Llibres();

        

    }
    
    public static void crear50Llibres() {
    SqlManager sqlManager = new SqlManager(); // Crea una instancia de la clase SQLManager
    Faker faker = new Faker(); // Crea una instancia de la clase Faker

    for (int i = 1; i <= 50; i++) { // Bucle para crear 50 libros
        Llibre llibre = new Llibre(); // Crea una instancia de la clase Llibre

       
        llibre.setTitol(faker.book().title());
        llibre.setAutor(faker.book().author());
        llibre.setIsbn(faker.code().isbn10());
        llibre.setEditorial(faker.book().publisher());
        llibre.setAnyPublicacio(faker.number().numberBetween(1950, 2023));
        llibre.setDescripcio(faker.lorem().paragraph());
        llibre.setSinopsi(faker.lorem().sentence());
        llibre.setIllustrador(faker.name().fullName());
        llibre.setRutaPortada("ruta_de_la_portada"); 
        llibre.setPagines(faker.number().numberBetween(100, 500));
        llibre.setIdioma(faker.nation().language());
        llibre.setExemplar(faker.number().numberBetween(1, 10));
        llibre.setNota(faker.lorem().sentence(5));
        llibre.setTitolOriginal(faker.book().title());
        llibre.setTraductor(faker.name().fullName());

        try {
            sqlManager.crearLlibre(llibre); 
        } catch (SQLException ex) {
            Logger.getLogger(ConfiguracioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    
}
