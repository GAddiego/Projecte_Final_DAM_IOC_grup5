/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BBDD;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Usuari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aleix
 */
public class SqlManagerTest {
    SqlManager sqlManager = new SqlManager();
    
    public SqlManagerTest() {
    }

    @Test
    public void testCrearTaula() {
    }

    @Test
    public void testVerificarUsuari() {
        sqlManager.verificarUsuari("algibo", "pass1");
        
    }

    @Test
    public void testCrearUsuari() {
        Usuari usuari = new Usuari("test1","testPass","test","nomTest","pCognomtest","sCognomtest","test@test.com");
        sqlManager.crearUsuari(usuari);      
        assertTrue(sqlManager.existeixNomUsuari(usuari.getNom()));
        assertTrue(sqlManager.verificarUsuari(usuari.getNom(), usuari.getPass()).equals(usuari.getRol()));
        usuari.setEmail("testModificat@test.com");
        sqlManager.modificarUsuari(usuari);
        sqlManager.eliminarUsuari(usuari.getNom());
        assertFalse(sqlManager.existeixNomUsuari(usuari.getNom()));
    }

    @Test
    public void testGetUsuari() throws Exception {
    }

    @Test
    public void testExisteixUsuari() {
    }

    @Test
    public void testExisteixNomUsuari() {
    }

    @Test
    public void testEliminarUsuari() {
    Usuari usuari = new Usuari("test1","testPass","test","nomTest","pCognomtest","sCognomtest","test@test.com");
    sqlManager.eliminarUsuari("test1");
    }

    @Test
    public void testModificarUsuari() {
        Usuari usuari = new Usuari("test1","testPass","test","nomTest","pCognomtest","sCognomtest","test@test.com");
        usuari.setEmail("testModificat@test.com");
        sqlManager.modificarUsuari(usuari);
        try {
            System.out.println(sqlManager.getUsuari(usuari.getNom(),usuari.getPass()).getEmail());
            assertTrue(sqlManager.getUsuari(usuari.getNom(),usuari.getPass()).getEmail().equals(usuari.getEmail()));
        } catch (ParseException ex) {
            Logger.getLogger(SqlManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testBuscarUsuaris() throws Exception {
    }

    @Test
    public void testCrearLlibre() throws Exception {
    }

    @Test
    public void testModificarLlibre() throws Exception {
    }

    @Test
    public void testEsborrarLlibre() throws Exception {
    }

    @Test
    public void testBuscarLlibres() throws Exception {
    }
    
}
