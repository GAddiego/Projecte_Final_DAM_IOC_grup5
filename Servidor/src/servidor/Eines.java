/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.util.Random;

/**
 *
 * @author Aleix
 */
public class Eines {
    
        public static String generarCodi() {
        String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder codigo = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            codigo.append(alfabet.charAt(rnd.nextInt(alfabet.length())));
        }

        return codigo.toString();
    }
    
}