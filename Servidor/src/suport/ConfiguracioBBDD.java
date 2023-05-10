package suport;

import BBDD.SqlManager;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Eines;
import objectes.Llibre;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import objectes.Avis;
import objectes.Usuari;
import objectes.UsuariIntern;



/**
 *
 * @author aleix
 */
public class ConfiguracioBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {


        SqlManager sql = new SqlManager();
        Eines eines = new Eines();
        eines.convertirABytes("imatges_usuaris/DefaultUser.png");
        
        //sql.crearTaula(5);
        java.util.Date data = new java.util.Date();
        Avis a = new Avis(17,"avis retard","et retades en la entrega", data, false, 99999);
        //sql.avisos.crearAvis(a, 999999);
        
        sql.avisos.marcarllegit(1);
        /*String data = "30/04/2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataReserva = LocalDate.parse(data, formatter);
        LocalDate mesSeguent = dataReserva.plusMonths(1);
        String expiracioData = mesSeguent.format(formatter);
        sql.reserves.crearReserva(1, 10, data, expiracioData );
        
        
        //sql.crearTaula(1);
        //List<Usuari> u = new ArrayList<>();
        //String llista[] = {"a",null,null,null,null,null,null};
        //u = sql.uIntern.buscarTotsUsuaris(llista);
        UsuariIntern ui = new UsuariIntern();
        for(int i=2; i <=16 ; i++){            
           ui = sql.uIntern.getUsuariMigracio(i);
           System.out.println(ui.toString());
           sql.uIntern.crearUsuari(ui);
        }*/
        
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




    
}
