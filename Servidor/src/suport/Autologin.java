/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package suport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.XifradorContrasenya;
import static suport.ProvaClient.HOST;
import static suport.ProvaClient.PORT;

/**
 *
 * @author aleix
 */
public class Autologin {
        static final String HOST="192.168.1.225";
        static final int  PORT=12345;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);         
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())                
        ){

        System.out.println("Enviando código inicial: (00000000)");
        String idUsuari = "algibo";
        String contrassenya = "pass1";
        out.writeUTF("00000000");
        
        System.out.println("Obteniendo PublicKey...");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        PublicKey publicKey = (PublicKey) ois.readObject();
        System.out.println(publicKey.toString());
        
               
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
        oos.writeObject(idUsuari);
        
        XifradorContrasenya xC = new XifradorContrasenya();
        byte[] passXifrat = xC.XifradorString(publicKey, contrassenya);
        System.out.println("Cifrando contraseña...");
        oos.writeObject(passXifrat);
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        boolean usuariValid = false;
            usuariValid = in.readBoolean();
            if(usuariValid==true){
                String codi = in.readUTF();
                String rol = in.readUTF();
                System.out.println("El teu codi es "+ codi + "i el rol "+ rol);
                
            }else{
                System.out.println("Usuari o contrasenya incorrectes");
            }
        
        
        
        }catch (IOException ex) {
                Logger.getLogger(Autologin.class.getName()).log(Level.SEVERE, "Conexio fallada", ex);
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(Autologin.class.getName()).log(Level.SEVERE, "Publickey", ex);
            }
    }
    
}
