/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Aleix
 */
public class Fil extends Thread{
    
    private DataInputStream in;
    private DataOutputStream out;
    private String user;
    private String pass;

    public Fil(DataInputStream in, DataOutputStream out, String user, String pass) {
        this.in = in;
        this.out = out;
        this.user = user;
        this.pass = pass;
    }

    
    @Override
    public void run() {
        Eines eines = new Eines();
        boolean estat = true;
        String opcio;
        try {
            
            String codi = eines.generarCodi(); 
            System.out.println("El codi es: "+ codi);
            out.writeBoolean(true);
            out.writeUTF(codi);
            System.out.println("Esperant ordres");
            while(estat){
               
                opcio = in.readUTF();
                
                switch(opcio){
                    case "sortir":
                        System.out.println("Has triat sortir");
                        break;
                    default:
                        System.out.println("Esperant ordres");
                }
            }
            

        } catch (IOException e) {
                e.printStackTrace();
                }  
    }
    
}
