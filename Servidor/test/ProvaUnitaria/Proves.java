package ProvaUnitaria;

import BBDD.SqlManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Avis;
import objectes.Comentari;
import objectes.Eines;
import objectes.Llibre;
import objectes.Prestec;
import objectes.Reserva;
import objectes.UsuariIntern;
import objectes.XifradorContrasenya;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleix
 */
public class Proves {
    SqlManager sqlManager = new SqlManager();
    Eines eines = new Eines();
    XifradorContrasenya xC = new XifradorContrasenya();
    
    public static <T> void mostrarLlista(List<T> llista){
        for (T element : llista) {          
            System.out.println(element);
        }
    }
    
    public Proves() {
    }
      
    public UsuariIntern uExemple(){
        UsuariIntern usuari = new UsuariIntern();
        usuari.setUser("johndoe");
        usuari.setPass(xC.XifradorString("Visca"));
        usuari.setRol("usuario");
        usuari.setDataNaixement(new java.util.Date(1990, 5, 11));
        usuari.setNom("John");
        usuari.setPrimerCognom("Doe");
        usuari.setSegonCognom("Smith");
        usuari.setEmail("johndoe@example.com");
        usuari.setDataAlta(new Date());
        usuari.setMulta(0.0);
        usuari.setSuspensio(false);
        usuari.setImageData(eines.convertirABytes("imatges_usuaris/DefaultUser.png"));
        return usuari;
    }
    public Llibre llExemple(){
        Llibre llibre = new Llibre();
            llibre.setTitol("El código Da Vinci");
            llibre.setAutor("Dan Brown");
            llibre.setIsbn("9788401337031");
            llibre.setEditorial("Planeta");
            llibre.setAnyPublicacio(2003);
            llibre.setDescripcio("Una emocionante novela que combina elementos de misterio, historia y conspiración.");
            llibre.setSinopsi("El profesor de simbología religiosa Robert Langdon es llamado a resolver un misterioso asesinato en el Louvre y termina envuelto en una trama de intrigas y secretos que podrían cambiar la historia del cristianismo.");
            llibre.setPagines(454);
            llibre.setIdioma("Español");
            llibre.setExemplar(10);
            llibre.setNota("Excelente libro, muy recomendado.");
            llibre.setTitolOriginal("The Da Vinci Code");
            llibre.setTraductor("Alejandro Palomas");
            return llibre;
        
    }
  
    @Test
    public void testUsuari() {
        UsuariIntern u = uExemple();
        sqlManager.uIntern.crearUsuariExtens(u);
        assertTrue(sqlManager.uIntern.existeixNomUsuari(u.getUser()));
        assertTrue(sqlManager.uIntern.existeixNomUsuari(u.getUser()));
        assertEquals((sqlManager.uIntern.verificarUsuari(u.getUser(), u.getPass())),u.getRol());
        sqlManager.uIntern.eliminarUsuari(u.getUser());
        assertFalse(sqlManager.uIntern.existeixNomUsuari(u.getUser()));
    }
    
    @Test
    public void testUsuariCurt(){
        try {
            sqlManager.uIntern.getUsuariRetornaReduit(17).toString();
        } catch (ParseException ex) {
            Logger.getLogger(Proves.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    @Test
    public void testLlibre() {
        try {
            Llibre ll = llExemple();
            sqlManager.llibres.crearLlibre(ll);
            String[] args = {null,null,ll.getIsbn(),null,null,null,null,null,null,null,null,null,null,null};
            List<Llibre> buscarLlibres = sqlManager.llibres.buscarLlibres(args);
            System.out.println(buscarLlibres.size());
            System.out.println(buscarLlibres.get(0).getId());
            assertEquals(buscarLlibres.get(0).getIsbn(),ll.getIsbn());
            ll.setAutor("Xavier Bosch");
            sqlManager.llibres.modificarLlibre(ll);
            assertEquals(sqlManager.llibres.buscarLlibreid(buscarLlibres.get(0).getId()).getAutor(),ll.getAutor());
            sqlManager.llibres.esborrarLlibre(ll);
            assertTrue(sqlManager.llibres.buscarLlibres(args).size()==0);
        } catch (SQLException ex) {
            Logger.getLogger(Proves.class.getName()).log(Level.SEVERE, null, ex);
        }
        

       
       
   }
    
    @Test
    public void testCrearComentari() {        // Crear un nou comentari per a un llibre
        Comentari comentari = new Comentari(17, 1, new Date(), "Aquest llibre està molt be!");
        try {
            sqlManager.comentaris.crearComentari(comentari);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLlistarComentarisLlibre() {
        // Obtindre una llista de comentaris per a un llibre específic
        int idLlibre = 1;
        try {
            List<Comentari> comentaris = sqlManager.comentaris.llistarComentarisLlibre(idLlibre);
            mostrarLlista(comentaris);
            // Comprovar si la llista de comentaris no és buida
            assertTrue(!comentaris.isEmpty());

            // Comprovar si els comentaris obtinguts tenen l'idLlibre esperat
            for (Comentari comentari : comentaris) {
                assertEquals(idLlibre, comentari.getIdLlibre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLlistarComentarisUsuari() {
        // Obtindre una llista de comentaris per a un usuari específic
        int idUsuari = 17;
        try {
            List<Comentari> comentaris = sqlManager.comentaris.llistarComentarisUsuari(idUsuari);
            mostrarLlista(comentaris);
            // Comprovar si la llista de comentaris no és buida
            assertTrue(!comentaris.isEmpty());

            // Comprovar si els comentaris obtinguts tenen l'idUsuari esperat
            for (Comentari comentari : comentaris) {
                assertEquals(idUsuari, comentari.getIdUsuari());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCrearAvis() {
        // Aquí pots escriure una prova per a la funció crearAvis()
        try {
            // Crea un objecte Avis per a la prova
            Avis avis = new Avis(17, "Títol de prova", "Missatge de prova", new java.util.Date(), false, 1);
            int idCreador = 17;

            // Crida la funció crearAvis()
            sqlManager.avisos.crearAvis(avis, idCreador);

            // Comprova si l'avís ha estat creat correctament a la base de dades

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }
    
        @Test
    public void testLlistarNous() {
        // Aquí pots escriure una prova per a la funció llistarNous()
        try {
            // Defineix l'ID de l'usuari per a la prova
            int idUsuari = 17;

            // Crida la funció llistarNous()
            List<Avis> avisosNous = sqlManager.avisos.llistarNous(idUsuari);
            mostrarLlista(avisosNous);
            // Comprova si la llista d'avisos nous és la que s'espera

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }

    @Test
    public void testMarcarLlegit() {
        // Aquí pots escriure una prova per a la funció marcarLlegit()
        try {
            // Defineix l'ID de l'avís a marcar com a llegit per a la prova
            int idAvis = 15;

            // Crida la funció marcarLlegit()
            sqlManager.avisos.marcarllegit(idAvis);

            // Comprova si l'avís ha estat marcat com a llegit a la base de dades

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }


    @Test
    public void testLlistarHistoric() {
        // Aquí pots escriure una prova per a la funció llistarHistoric()
        try {
            // Defineix l'ID de l'usuari per a la prova
            int idUsuari = 17;

            // Crida la funció llistarHistoric()
            List<Avis> avisosHistorics = sqlManager.avisos.llistarHistoric(idUsuari);
            mostrarLlista(avisosHistorics);
            // Comprova si la llista d'avisos històrics és la que s'espera

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }
    
    @Test
    public void testCrearPrestec()  {
        try {
            // Crea les dades per a la prova
            int idUsuari = 17;
            int idLlibre = 2;
            java.util.Date dataPrestec = new java.util.Date();
            java.util.Date dataVenciment = eines.convertirDataString(eines.diaRetorn());
            boolean prolongacio = false;
            // Crida la funció crearPrestec()
            sqlManager.prestec.crearPrestec(idUsuari, idLlibre, dataPrestec, dataVenciment, prolongacio);
            
            // Comprova si el préstec ha estat creat correctament a la base de dades
        } catch (ParseException ex) {
            ex.printStackTrace();
            Assert.fail("Error en l'execució de la prova: " + ex.getMessage());
        }
    }

    @Test
    public void testPrestecActiuUsuari() {
        // Defineix l'ID de l'usuari per a la prova
        int idUsuari = 17;
        // Crida la funció prestecActiuUsuari()
        List<Prestec> prestecsActius = sqlManager.prestec.prestecActiuUsuari(idUsuari);
        mostrarLlista(prestecsActius);
    }

    @Test
    public void testPrestecActiuLlibre() {
        // Defineix l'ID del llibre per a la prova
        int idLlibre = 1;
        // Crida la funció prestecActiuLlibre()
        List<Prestec> prestecsActius = sqlManager.prestec.prestecActiuLlibre(idLlibre);
        mostrarLlista(prestecsActius);
    }

    @Test
    public void testPrestecActiuLlibreUsuari() {
        // Defineix l'ID del llibre i de l'usuari per a la prova
        int idLlibre = 2;
        int idUsuari = 17;
        // Crida la funció prestecActiuLlibreUsuari()
        Prestec prestecActiu = sqlManager.prestec.prestecActiuLlibreUsuari(idLlibre, idUsuari);
        if (prestecActiu == null){
            System.out.println("No hi ha presctec atctiu");
        }else{
            System.out.println(prestecActiu.toString());
        }
    }

    @Test
    public void testModificarPrestec() {
        // Defineix l'ID del llibre per a la prova
        int idLlibre = 2;
        int idUsuari = 17;  
        int idPrestec = 31;
        // Defineix la data de renovació per a la prova
        Prestec prestecActiu = sqlManager.prestec.prestecActiuLlibreUsuari(idLlibre, idUsuari);
        System.out.println("Data venciment actual: " + prestecActiu.getdataVenciment());
        java.util.Date dataVenciment = eines.ampliacióRetorn(prestecActiu.getdataVenciment() );
        System.out.println("Nova data venciemnt " + dataVenciment);
        // Crida la funció modificarPrestec()
        sqlManager.prestec.modificarPrestec(idPrestec, dataVenciment);
    }

    @Test
    public void testTancarPrestec() {
        // Defineix l'ID del préstec i la data de retorn per a la prova
        int idPrestec = 31;
        java.sql.Date dataRetorn = new java.sql.Date(System.currentTimeMillis());
        // Crida la funció tancarPrestec()
        sqlManager.prestec.tancarPrestec(idPrestec, dataRetorn);
    }
    
    @Test
    public void testCrearReserva() {
        // Crea les dades per a la prova
        int idUsuari = 22;
        int idLlibre = 10;
        java.util.Date dataReserva = new java.util.Date();
        java.util.Date dataFinalitzacio = null;
        // Crida la funció crearReserva()
        sqlManager.reserves.crearReserva(idUsuari, idLlibre, dataReserva, dataFinalitzacio);
    }
    
        @Test
    public void testLlistarReservesUsuari() {
        // Aquí pots escriure una prova per a la funció llistarReservesUsuari()
        try {
            // Defineix l'ID de l'usuari per a la prova
            int idUsuari = 22;

            // Crida la funció llistarReservesUsuari()
            List<Reserva> reservesUsuari = sqlManager.reserves.llistarReservesUsuari(idUsuari);
            mostrarLlista(reservesUsuari);
            // Comprova si la llista de reserves de l'usuari és la que s'espera

        } catch (SQLException e) {
            e.printStackTrace();
            //Assertions.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }


    @Test
    public void testFinalitzarReserva() {
        // Aquí pots escriure una prova per a la funció finalitzarReserva()
        try {
            // Defineix l'ID de la reserva per a la prova
            int idReserva = 20;

            // Defineix la data de recollida per a la prova
            java.util.Date dataRecollida = new java.util.Date();

            // Crida la funció finalitzarReserva()
            sqlManager.reserves.finalitzarReserva(idReserva, dataRecollida);

            // Comprova si la reserva ha estat finalitzada correctament a la base de dades

        } catch (SQLException e) {
            e.printStackTrace();
           // Assertions.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }


    @Test
    public void testLlistarReservesLlibre() {
        // Aquí pots escriure una prova per a la funció llistarReservesLlibre()
        try {
            // Defineix l'ID del llibre per a la prova
            int idLlibre = 1;

            // Crida la funció llistarReservesLlibre()
            List<Reserva> reservesLlibre = sqlManager.reserves.llistarReservesLlibre(idLlibre);
            mostrarLlista(reservesLlibre);
            // Comprova si la llista de reserves del llibre és la que s'espera

        } catch (SQLException e) {
            e.printStackTrace();
            //Assertions.fail("Error en l'execució de la prova: " + e.getMessage());
        }
    }
    
    


    
}
