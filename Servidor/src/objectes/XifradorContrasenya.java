package objectes;


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
 * Classe que proporciona funcionalitats per xifrar i desxifrar contrasenyes utilitzant l'algorisme RSA.
 * També permet comprovar si dues contrasenyes desxifrades coincideixen.
 * Es basa en l'ús de claus públiques i privades.
 * 
 * @author Aleix
 */
public class XifradorContrasenya {
    
    
    /**
     * Desxifra un array de bytes utilitzant la clau privada emmagatzemada al servidor.
     *
     * @param encryptedData els dades xifrades a desxifrar
     * @return la contrasenya desxifrada en forma de String
     */
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
    
    /**
     * Xifra una contrasenya en forma de String utilitzant la clau pública emmagatzemada al servidor.
     *
     * @param contrasenya la contrasenya a xifrar
     * @return els dades xifrades en forma d'array de bytes
     */
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
    
    /**
     * Xifra una contrasenya en forma de String utilitzant la clau pública proporcionada i emmagatzemada al servidor.
     *
     * @param serverPublicKey la clau pública amb la qual s'ha de xifrar la contrasenya
     * @param contrassenya la contrasenya a xifrar
     * @return els dades xifrades en forma d'array de bytes
     */
    public byte[] XifradorString(PublicKey serverPublicKey, String contrassenya){
        byte[] dataToEncrypt = contrassenya.getBytes(StandardCharsets.UTF_8);
        try {
            // Cifrar los datos
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);
            byte[] encryptedData = cipher.doFinal(dataToEncrypt);
            return encryptedData;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(XifradorContrasenya.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Comprova si dues contrasenyes xifrades coincideixen desxifrant-les i comparant-les.
     *
     * @param client la contrasenya xifrada a comparar
     * @param bbdd la contrasenya xifrada emmagatzemada a la base de dades
     * @return true si les contrasenyes coincideixen, false en cas contrari
     */
    public boolean ComprovarContrasenya(byte[] client, byte[] bbdd){
        if (Desxifradorbyte(client).equals(Desxifradorbyte(bbdd))){
            
            return true;
        }

        return false;
        
        
    }
    
    /**
     * Retorna la clau pública emmagatzemada al servidor.
     *
     * @return la clau pública del servidor
     */
    public PublicKey ClauPublica(){
        try {
            byte[] publicKeyBytes;
            
            publicKeyBytes = Files.readAllBytes(Paths.get("public.key"));
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey serverPublicKey = keyFactory.generatePublic(publicKeySpec);
            return serverPublicKey;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(XifradorContrasenya.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
