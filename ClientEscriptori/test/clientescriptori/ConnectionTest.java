/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package clientescriptori;

import java.util.List;
import objectes.Avis;
import objectes.Comentari;
import objectes.Llibre;
import objectes.Reserva;
import objectes.Usuari;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gonzalo
 */
public class ConnectionTest {
    
    public ConnectionTest() {
    }

    @Test
    public void testIniciarSessio() throws Exception {
        System.out.println("iniciarSessio");
        Connection instance = null;
        boolean expResult = false;
        boolean result = instance.iniciarSessio();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCodi() {
        System.out.println("getCodi");
        Connection instance = null;
        String expResult = "";
        String result = instance.getCodi();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Connection instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUsuari() {
        System.out.println("getUsuari");
        Connection instance = null;
        Usuari expResult = null;
        Usuari result = instance.getUsuari();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testLogOut() {
        System.out.println("logOut");
        Connection instance = null;
        instance.logOut();
        fail("The test case is a prototype.");
    }

    @Test
    public void testRebreUsuariAcual() {
        System.out.println("rebreUsuariAcual");
        String codi = "";
        Connection instance = null;
        instance.rebreUsuariAcual(codi);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRebreAvisos() {
        System.out.println("rebreAvisos");
        Connection instance = null;
        List<Avis> expResult = null;
        List<Avis> result = instance.rebreAvisos();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotificarAvisLlegit() throws Exception {
        System.out.println("notificarAvisLlegit");
        Avis avis = null;
        Connection instance = null;
        instance.notificarAvisLlegit(avis);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRebreUsuariAcualStatic() {
        System.out.println("rebreUsuariAcualStatic");
        String codi = "";
        Usuari expResult = null;
        Usuari result = Connection.rebreUsuariAcualStatic(codi);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscarUsuaris() throws Exception {
        System.out.println("buscarUsuaris");
        String[] arrayUsuari = null;
        List<Usuari> expResult = null;
        List<Usuari> result = Connection.buscarUsuaris(arrayUsuari);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEsborrarUsuari() throws Exception {
        System.out.println("esborrarUsuari");
        String nom_usuari = "";
        Connection instance = null;
        instance.esborrarUsuari(nom_usuari);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEsborrarLlibre() throws Exception {
        System.out.println("esborrarLlibre");
        Llibre llibre = null;
        Connection instance = null;
        instance.esborrarLlibre(llibre);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCrearUsuari() throws Exception {
        System.out.println("crearUsuari");
        Usuari u = null;
        Connection instance = null;
        instance.crearUsuari(u);
        fail("The test case is a prototype.");
    }

    @Test
    public void testModificarUsuari() {
        System.out.println("modificarUsuari");
        Usuari usuari = null;
        Connection.modificarUsuari(usuari);
        fail("The test case is a prototype.");
    }

    @Test
    public void testModificarUsuariSenseXifrar() {
        System.out.println("modificarUsuariSenseXifrar");
        Usuari usuari = null;
        Connection.modificarUsuariSenseXifrar(usuari);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCrearReserva() throws Exception {
        System.out.println("crearReserva");
        int idLlibre = 0;
        Connection.crearReserva(idLlibre);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscarReservaUsuari() throws Exception {
        System.out.println("buscarReservaUsuari");
        int idUsuari = 0;
        List<Reserva> expResult = null;
        List<Reserva> result = Connection.buscarReservaUsuari(idUsuari);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRecuperarNomLlibrePerId() throws Exception {
        System.out.println("recuperarNomLlibrePerId");
        int id = 0;
        Llibre expResult = null;
        Llibre result = Connection.recuperarNomLlibrePerId(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreaAvis() throws Exception {
        System.out.println("creaAvis");
        Avis avis = null;
        Connection instance = null;
        instance.creaAvis(avis);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreaLlibre() throws Exception {
        System.out.println("creaLlibre");
        String[] arrayLlibre = null;
        Connection instance = null;
        instance.creaLlibre(arrayLlibre);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscaLlibres() {
        System.out.println("buscaLlibres");
        String[] llibre = null;
        Connection instance = null;
        List<Llibre> expResult = null;
        List<Llibre> result = instance.buscaLlibres(llibre);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscaComentaris() throws Exception {
        System.out.println("buscaComentaris");
        int id = 0;
        List<Comentari> expResult = null;
        List<Comentari> result = Connection.buscaComentaris(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEnviaComentari() {
        System.out.println("enviaComentari");
        String comentariString = "";
        int idLlibre = 0;
        Connection instance = null;
        boolean expResult = false;
        boolean result = instance.enviaComentari(comentariString, idLlibre);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscaNomPerID() {
        System.out.println("buscaNomPerID");
        String id = "";
        Connection instance = null;
        String expResult = "";
        String result = instance.buscaNomPerID(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUsuariStatic() {
        System.out.println("getUsuariStatic");
        Usuari expResult = null;
        Usuari result = Connection.getUsuariStatic();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
