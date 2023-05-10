/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package suport;

import BBDD.SqlManager;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import objectes.Eines;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class GuardarMostrarImatge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            SqlManager sqlManager = new SqlManager();
            Eines eines = new Eines();
            
            //GUARDAR IMATGE
            /*
            UsuariIntern usuari = sqlManager.uIntern.getUsuari("pepito", "pass2");
            String ruta= "imatges_usuaris/DefaultUser1.png";
            usuari.setImageData(eines.convertirABytes(ruta));
            sqlManager.uIntern.modificarFoto(usuari);
            */
            
            //MOSTRAR IMATGE
            UsuariIntern usuari2 = sqlManager.uIntern.getUsuari("pepito", "pass2");
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
        } catch (ParseException ex) {
            Logger.getLogger(GuardarMostrarImatge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
