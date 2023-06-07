package utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import objectes.Llibre;

public class Utils {

    public static String obtenirContrassenya(JPasswordField pass) {

        char[] contrassenyaChar = pass.getPassword();
        StringBuilder contrassenyaSB = new StringBuilder();
        for (char c : contrassenyaChar) {
            contrassenyaSB.append(c);
        }
        String contrassenya = contrassenyaSB.toString();

        return contrassenya;
    }

    
    //deprecated
    public static byte[] convertirImagenABytes(BufferedImage imagen) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "jpg", baos);

        return baos.toByteArray();
    }

//    public static ImageIcon convertirBytesAImagen(byte[] imageBytes) throws IOException {
//        System.out.println("imageBytes: " + imageBytes.length);
//        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
//        BufferedImage imagen = ImageIO.read(bais);
//        ImageIcon imageIcon = new ImageIcon(imagen);
//        
//        return imageIcon;
//    }
    
    public static ImageIcon convertirBytesAImagen(byte[] imageBytes) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(imageBytes);
            ImageIcon imageIcon = new ImageIcon(image);
            System.out.println("imageBytes convertida a imageIcon, devolviendo...");
            return imageIcon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("convertirBytesAImagen devuelve null");
        return null;
    }
    
    public static Llibre converteixArrayEnLlibre(String[] arrayLlibre){
        int any;
        if(!arrayLlibre[4].isBlank()) {
            any = (Integer.valueOf(arrayLlibre[4]));
        }else{
            any = 0000;
        }
        Llibre llibre = new Llibre(
            arrayLlibre[0], arrayLlibre[1], arrayLlibre[2], arrayLlibre[3], any, 
                "", "", arrayLlibre[7], 0, arrayLlibre[9], 0, arrayLlibre[11],
                "", "", null);  
        
        return llibre;
    }
}
