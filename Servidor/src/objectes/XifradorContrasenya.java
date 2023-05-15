/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import suport.Contrasenya;

/**
 *
 * @author aleix
 */
public class XifradorContrasenya {
    
    public String Desxifradorbyte(byte[] encryptedData){
        try {
            
            // Leer clave privada del servidor
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get("private.key"));
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey serverPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            
            // Descifrar los datos
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, serverPrivateKey);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            
            String contrasenya = new String(decryptedData,StandardCharsets.UTF_8);
            System.out.println(contrasenya);
            return contrasenya;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(XifradorContrasenya.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public byte[] XifradorString(String contrasenya){
        byte[] dataToEncrypt = contrasenya.getBytes(StandardCharsets.UTF_8);
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
        return encryptedData;

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Contrasenya.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean ComprovarContrasenya(byte[] client, byte[] bbdd){
        if (Desxifradorbyte(client).equals(Desxifradorbyte(bbdd))){
            
            return true;
        }

        return false;
        
        
    }
       
}
