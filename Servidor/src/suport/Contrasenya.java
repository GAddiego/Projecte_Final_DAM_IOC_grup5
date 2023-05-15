/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package suport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import objectes.XifradorContrasenya;

/**
 *
 * @author aleix
 */
public class Contrasenya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //client contrasenya
        // Leer clave pública del servidor
        String pass = "pepito2345!";
        byte[] dataToEncrypt = pass.getBytes(StandardCharsets.UTF_8);
        byte[] publicKeyBytes;
        try {
            publicKeyBytes = Files.readAllBytes(Paths.get("public.key"));
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey serverPublicKey = keyFactory.generatePublic(publicKeySpec);

        // Cifrar los datos
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);
        byte[] encryptedData = cipher.doFinal(dataToEncrypt);
        XifradorContrasenya dc = new XifradorContrasenya();
        dc.Desxifradorbyte(encryptedData);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Contrasenya.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
    
}
