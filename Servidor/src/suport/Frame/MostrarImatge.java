
package suport.Frame;

import suport.Frame.UsuariProfileFrame;
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

            int opcio = 2;
            
            if (opcio == 1){
                UsuariIntern usuari = sqlManager.uIntern.getUsuari("algibo");
                String ruta= "imatges_usuaris/DefaultUser.png";
                //usuari.setImageData(ruta);
                Usuari u = new Usuari(usuari);
                UsuariProfileFrame upf = new UsuariProfileFrame(u);
           
 
            }if (opcio == 2){
                //UsuariIntern usuari = sqlManager.uIntern.getUsuari("pepito", "pass2");
                //String ruta= "imatges_usuaris/DefaultUser1.png";
                //usuari.setImageData(eines.convertirABytes(ruta));
                //sqlManager.uIntern.modificarFoto(usuari);
                UsuariIntern usuari2 = sqlManager.uIntern.getUsuari("pepito");
                //if (eines.comprovarRuta(ruta)){
                    // Crear InputStream a partir del array de bytes
                    System.out.println(usuari2.getImageData());
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(usuari2.getImageData());
                    System.out.println(usuari2.toString());
                    // Crear objeto Image a partir del InputStream
                    Image imagen = ImageIO.read(inputStream);
                    // Mostrar imagen en un JLabel
                    JFrame frame = new JFrame();
                    JLabel label = new JLabel(new ImageIcon(imagen));
                    frame.add(label);
                    frame.pack();
                    frame.setVisible(true);
                //}else{
                    //System.out.println("La imatge no existeix");
                //}
            }if (opcio == 3){
                String[] param = {"","","","","","","","100"};       
                //LlistaUsuaris llistaUsuaris = new LlistaUsuaris(sqlManager.uIntern.buscarUsuarisFoto(param));
                //llistaUsuaris.setVisible(true);
            }


        }
    
}
