/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Altres;

import BBDD.SqlManager;
import servidor.Usuari;

/**
 *
 * @author aleix
 */
public class ConfiguracioBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SqlManager sql = new SqlManager();
        
        //sql.crearTaulaUsuaris();
        //Usuari u = new Usuari("algibo","pass1","admin","aleix","giralt","borrell","aleixgibo@gmail.com");
        Usuari u = new Usuari("pepito","pass2","bibliotecaria","pepe","molins","estruch","pepemoes@gmail.com");
        sql.crearUsuari(u);
        //sql.crearUsuari("marc", "marc1234", "professor", "1992-10-15");
        //sql.crearUsuari("pere", "pere1234", "alumne", "2018-09-01");
        

    }
    
}
