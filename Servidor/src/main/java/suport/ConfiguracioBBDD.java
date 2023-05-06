package suport;

import BBDD.SqlManager;
import com.github.javafaker.Faker;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;
import objectes.Llibre;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import objectes.UsuariIntern;



/**
 *
 * @author aleix
 */
public class ConfiguracioBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {


        SqlManager sql = new SqlManager();
        CreadorFaker cf = new CreadorFaker();
        Eines eines = new Eines();
        //sql.crearTaula(4);
        /*String data = "30/04/2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataReserva = LocalDate.parse(data, formatter);
        LocalDate mesSeguent = dataReserva.plusMonths(1);
        String expiracioData = mesSeguent.format(formatter);
        sql.reserves.crearReserva(1, 10, data, expiracioData );
        
        */
        UsuariIntern ui = new UsuariIntern();
        ui = sql.uIntern.getUsuari("algibo", "pass1");
        sql.uIntern.crearUsuari2(ui);
        //sql.crearTaula(0);
        //sql.crearTaula(1);
        
        //Llibre llibre = cf.crearLlibre();
        //sql.crearLlibre(llibre);
        //llibre.setDescripcio("modificat");
        //sql.modificarLlibre(llibre);
        
        //UsuariIntern ui = sql.getUsuari("jucomo", "jucó9957");
        //ui.setRutaFoto("imatges_usuaris/DefaultUser1.png");
        //sql.modificarUsuari(ui);
        
        
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

        
    }
}


    
}
