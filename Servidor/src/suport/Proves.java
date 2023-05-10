
package suport;

import BBDD.SqlManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
public class Proves {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Eines eines = new Eines();
        SqlManager sqlManager = new SqlManager();
        
        Date dataNaixement = new Date(95, 5, 1);
        Date dataAlta = new Date(2022, 3, 15);
        Date dataBaixa = null;
        Date dataFinalSuspensio = null;
        byte[] imageData = null;
        imageData = eines.convertirABytes("imatges_usuaris/DefaultUser.png");

        Usuari usuari = new Usuari(
            123456,
            "nomusuari",
            "passwordsecreta",
            "rol",
            dataNaixement,
            "Nom",
            "Primer Cognom",
            "Segon Cognom",
            "email@domini.com",
            dataAlta,
            dataBaixa,
            0.0,
            false,
            dataFinalSuspensio,
            imageData
        );
            System.out.println(usuari.toString());
            UsuariIntern uintern = new UsuariIntern(usuari);
            sqlManager.uIntern.crearUsuari2(uintern);
            
            sqlManager.uIntern.modificarFoto();
            
    }
    
    public List<Usuari> CrearUsuari(List<String[]> llista) {
        Eines eines = new Eines();
        for(int i = 0; i < llista.size(); i++){
            try {
                String[] aux = llista.get(i);
                Usuari usuari = new Usuari(
                        Integer.parseInt(aux[0]),
                        aux[1],
                        aux[2],
                        aux[3],
                        eines.convertirDataString(aux[5]),
                        aux[4],
                        aux[6],
                        aux[7],
                        aux[8],
                        eines.convertirDataString(aux[9]),
                        eines.convertirDataString(aux[10]),
                        Double.parseDouble(aux[11]),
                        Boolean.valueOf(aux[12]),
                        eines.convertirDataString(aux[13]),
                        eines.convertirABytes("imatges_usuaris/DefaultUser.png")
                );  
                System.out.println(usuari.toString());
            } catch (ParseException ex) {
                Logger.getLogger(Proves.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        return null;
        
    }
    
}
