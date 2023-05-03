
package suport;

import BBDD.SqlManager;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import objectes.Eines;
import objectes.Usuari;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class MostrarImatge {

    public static void main(String[] args) throws Exception {
            // Crear objeto Usuario con imagen en array de bytes
            SqlManager sqlManager = new SqlManager();
            Eines eines = new Eines();          

            int opcio = 3;
            
            if (opcio == 1){
                UsuariIntern usuari = sqlManager.uIntern.getUsuari("algibo", "pass1");
                String ruta= "imatges_usuaris/DefaultUser.png";
                usuari.setImageData(ruta);
                Usuari u = new Usuari(usuari);
                UsuariProfileFrame upf = new UsuariProfileFrame(u);
           
 
            }if (opcio == 2){
                UsuariIntern usuari = sqlManager.uIntern.getUsuari("algibo", "pass1");
                String ruta= "imatges_usuaris/DefaultUser.png";
                usuari.setImageData(ruta);
                if (eines.comprovarRuta(ruta)){
                    // Crear InputStream a partir del array de bytes
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(usuari.getImageData());

                    // Crear objeto Image a partir del InputStream
                    Image imagen = ImageIO.read(inputStream);
                    // Mostrar imagen en un JLabel
                    JFrame frame = new JFrame();
                    JLabel label = new JLabel(new ImageIcon(imagen));
                    frame.add(label);
                    frame.pack();
                    frame.setVisible(true);
                }else{
                    System.out.println("La imatge no existeix");
                }
            }if (opcio == 3){
                String[] param = {"","","","","","","","100"};       
                LlistaUsuaris llistaUsuaris = new LlistaUsuaris(sqlManager.uIntern.buscarUsuarisFoto(param));
                llistaUsuaris.setVisible(true);
            }


        }
    
}
