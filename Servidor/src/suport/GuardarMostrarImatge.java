package suport;

import BBDD.SqlManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import objectes.Eines;
import objectes.Llibre;
import objectes.Usuari;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class GuardarMostrarImatge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CrearLLibre();
        //CrearUsuari();
    }
    
    public static void CrearUsuari(){
         try {
            SqlManager sqlManager = new SqlManager();
            Eines eines = new Eines();
            
            //GUARDAR IMATGE
            
            //UsuariIntern usuari = sqlManager.uIntern.getUsuari("pepito", "pass2");
            //String ruta= "imatges_usuaris/DefaultUser1.png";
            //usuari.setImageData(eines.convertirABytes(ruta));
            //System.out.println(usuari.getImageData());
            //sqlManager.uIntern.modificarFoto(usuari);
            
            
            //MOSTRAR IMATGE
            UsuariIntern usuari2 = sqlManager.uIntern.getUsuari("algibo", "pass1");
            
            
            //if (eines.comprovarRuta(ruta)){
            // Crear InputStream a partir del array de bytes
            Usuari u = new Usuari(usuari2);
            System.out.println(u.getImageData());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(u.getImageData());
            System.out.println(u.toString());

            // Crear objeto Image a partir del InputStream
            Image imagen = ImageIO.read(inputStream);
            // Mostrar imagen en un JLabel
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(imagen));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);

        } catch (ParseException ex) {
            Logger.getLogger(GuardarMostrarImatge.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuardarMostrarImatge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void CrearLLibre(){
        try{
            SqlManager sqlManager = new SqlManager();
            Eines eines = new Eines();
            //GUARDAR IMATGE
            
            Llibre llibre = new Llibre();
            String ruta= "imatges_llibre/llibre1.jpg";
            llibre.setPortada((byte[]) eines.convertirABytes(ruta));
            System.out.println(llibre.getPortada());
            sqlManager.llibres.modificarLlibreImatge(llibre);
            
            
            
            //MOSTRAR IMATGE
            
            Llibre llibre2 = sqlManager.llibres.buscarLlibreid(1);
            System.out.println(llibre2.getPortada());
            ByteArrayInputStream inputStream2 = new ByteArrayInputStream(llibre2.getPortada());
            if (inputStream2==null){
                System.out.println("alguna cosa falla");
            }
            // Crear objeto Image a partir del InputStream
            Image  imagen2 = ImageIO.read(inputStream2);
            System.out.println(imagen2.toString());
            // Mostrar imagen en un JLabel
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(imagen2));
            frame.add(label); 
            frame.pack();
            frame.setVisible(true);
            //}else{
            //System.out.println("La imatge no existeix");
        } catch (IOException ex) {
            Logger.getLogger(GuardarMostrarImatge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
