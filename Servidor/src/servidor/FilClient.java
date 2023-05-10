package servidor;


import BBDD.SqlManager;
import objectes.UsuariIntern;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectes.Avis;
import objectes.Comentari;
import objectes.Eines;
import objectes.Llibre;
import objectes.Prestec;
import objectes.Reserva;
import objectes.Usuari;

/**
 * Classe que representa un fil de client per gestionar la connexió amb el servidor.
 * Aquest fil s'encarrega d'establir una connexió amb el client i retornar al usuari 
 * les dades que sol·liciti.
 * 
 * @author Aleix
 */
public class FilClient extends Thread {
    Eines eines = new Eines();
    
    private final Socket clientSocket;
    private final SqlManager sqlManager;
    private final BufferUsuaris bufferUsuaris;
    private final UsuariIntern usuari;
    private final String codiEntrada;

    /**
     * Constructor de la classe FilClient que rep un objecte Socket del client, un objecte BufferUsuaris
     * per a gestionar el buffer d'usuaris, i el codi d'entrada de l'usuari.
     * 
     * @param clientSocket  Objecte Socket del client per a gestionar la connexió.
     * @param bufferUsuaris Objecte BufferUsuaris per a gestionar el buffer d'usuaris.
     * @param codiEntrada   Codi d'entrada de l'usuari associat a aquesta connexió.
     */
    public FilClient(Socket clientSocket, BufferUsuaris bufferUsuaris, String codiEntrada) {
        this.clientSocket = clientSocket;
        this.bufferUsuaris = bufferUsuaris;
        this.codiEntrada = codiEntrada;
        this.usuari = bufferUsuaris.recuperarUsuari(codiEntrada);
        this.sqlManager = new SqlManager();
    }

    /**
    * Mètode que s'executa quan es comença a executar el fil. 
    */
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            String opcio;
            while (true) {
                opcio = in.readUTF();

                switch (opcio) {
                    case "sortir":
                        System.out.println("L'usuari " + usuari.getUser() + " amb codi " + codiEntrada + " ha sortit");
                        bufferUsuaris.borrar(codiEntrada);
                        return;
                    case "dades_usuari":
                        enviarDadesUsuari();
                        break;
                    case "dades_usuari_imatge":
                        enviarDadesUsuariImatge();
                        break;
                    case "crear_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearUsuari();
                        } else {
                            out.writeUTF("no tens permisos");
                        }
                        break;
                    case "borrar_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            borrarUsuari();
                        }
                        break;
                    case "modificar_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            modificarUsuari();
                        }
                        break;
                    case "llistar_usuaris":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            consultarLlistaUsuaris();
                        }                       
                        break;
                    case "crear_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearLlibre();
                        }                       
                        break;
                    case "modificar_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            modificarllibre();
                        }                       
                        break;
                    case "borrar_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            borrarLlibre();
                        }                       
                        break;
                    case "llistar_llibres":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            consultarLlistaLlibres();
                        }
                        break; 
                    case "crear_prestec":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearPrestec();
                        }
                        break;
                    case "modificar_prestec":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            modificarPrestec();
                        }
                        break;
                    case "finalitzar_prestec":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            finalitzarPrestec();
                        }
                        break;      
                    case "buscar_prestec_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarPrestecUsuari();
                        }
                        if(usuari.getRol().equals("usuari")){
                            mevesReserves();
                        }
                        break;
                    case "buscar_prestec_isbn":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarPrestecIsbn();
                        }
                        break;
                    case "crear_reserva":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            crearReserva();
                        }
                        break;
                    case "finalitzar_reserva":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            finalitzarReserva();
                        }
                        break;
                    case "buscar_reserva_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarReservaUsuari();
                        }
                        break;
                    case "buscar_reserva_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarReservaLlibre();
                        }
                        break;
                    case "crear_comentari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            crearComentari();
                        }
                        break;
                    case "modificar_comentari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            modificarComentari();
                        }
                        break;
                    case "borrar_comentari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            borrarComentari();
                        }
                        break;
                    case "buscar_comentari_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarComentariLlibre();
                        }
                        break;
                    case "crear_avis":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            crearAvis();
                        }
                        break;
                    case "avis_llegit":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            avisLlegit();
                        }
                        break;
                    case "buscar_avisos_usuari":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            buscarAvisosUsuaris();
                        }
                        break;
                }
                in.close();
                out.close();
                clientSocket.close();
            }
            
        } catch (IOException e) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, e);
        } catch (ParseException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envia les dades de l'usuari connectat al client.
     */
    private void enviarDadesUsuari() {
        try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            System.out.println("enviarDadesUsuari " + usuari.getUser());
            Usuari ue = new Usuari(usuari);
            oos.writeObject(ue);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, "enviarDadesUsuari ha fallat", ex);
        }
    }
    
    private void enviarDadesUsuariImatge() {
        try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            System.out.println("enviar dades usuari " + usuari.getUser());
            Usuari u = new Usuari(usuari);
            oos.writeObject(u);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un usuari a partir de les dades rebudes del client.
     */
    private void crearUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Usuari u = (Usuari) ois.readObject();
            UsuariIntern ui = new UsuariIntern(u);
            sqlManager.uIntern.crearUsuari(ui);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Borra un usuari a partir de les dades rebudes del client.
     */
    private void borrarUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String userName = (String) ois.readObject();
            sqlManager.uIntern.eliminarUsuari(userName);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Modifica un usuari a partir de les dades rebudes del client.
     */
    private void modificarUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Usuari u = (Usuari) ois.readObject();
            UsuariIntern ui = new UsuariIntern(u);
            if (u.getMulta()!=0){
                ui.setMulta(u.getMulta());
            }
            sqlManager.uIntern.modificarUsuari(ui);
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaUsuaris() throws ParseException{
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String[] dadesConsulta = (String[]) ois.readObject();
            oos.writeObject(sqlManager.uIntern.buscarUsuaris(dadesConsulta));
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearLlibre(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            sqlManager.llibres.crearLlibre(llibre);
            ois.close();
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void modificarllibre(){
        System.out.println("modificar llibre: " );
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            System.out.println(llibre.toString() );
            sqlManager.llibres.modificarLlibre(llibre);   
            ois.close();
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void borrarLlibre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            sqlManager.llibres.esborrarLlibre(llibre);
            System.out.println("Borrar llibre: " + llibre.getIsbn() );
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaLlibres(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String[] dadesConsulta = (String[]) ois.readObject();
            System.out.println("Numero de llibres trobats: " + (int) sqlManager.llibres.buscarLlibres(dadesConsulta).size());
            oos.writeObject(sqlManager.llibres.buscarLlibres(dadesConsulta));
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearPresrec(){
        String missatge;
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            Usuari usuari = (Usuari) ois.readObject();
            List<Reserva> reserves = new ArrayList<>();
            reserves = sqlManager.reserves.llistarReservesLlibre(llibre.getId());
            if (reserves.size()==0 ){
                sqlManager.prestec.crearPrestec(usuari.getId(), llibre.getId(), eines.diaAvui(), eines.diaRetorn(), false);
                missatge = "Fet";
            }else{
                missatge = "No s'ha realitzat, hi ha reserves pendents";
            }
            oos.writeObject(missatge);
            ois.close();
            oos.close();
        
         }   catch (IOException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void ampliarPrestec(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            
            Prestec prestec = new Prestec((List<Prestec>) sqlManager.prestec.obtenirPrestecLlibre(llibre.getId()));
            System.out.println(llibre.toString() );
            sqlManager.prestec.modificarPrestec(llibre.getId(), (Date) eines.convertirDataString(eines.ampliacióRetorn(prestec.getDataRetorn())));
            ois.close();
        }catch (IOException | ClassNotFoundException | ParseException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tancarPrestec() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String idPrestec = (String) ois.readObject();
            sqlManager.prestec.tancarPrestec(Integer.parseInt(idPrestec), (Date) eines.convertirDataString(eines.diaAvui()));
        }catch (IOException | ClassNotFoundException | ParseException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaPrestecs(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String dadesConsulta = (String) ois.readObject();           
            oos.writeObject(sqlManager.prestec.llistaPrestecsActius(Integer.parseInt(dadesConsulta)));
        }catch (IOException | ClassNotFoundException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearReserva(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            Usuari usuari = (Usuari) ois.readObject();
            sqlManager.prestec.crearPrestec(usuari.getId(), llibre.getId(), eines.diaAvui(), eines.diaRetorn(), false);
            ois.close();
        }catch (IOException | ClassNotFoundException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tancarReserva() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String idPrestec = (String) ois.readObject();
            sqlManager.prestec.tancarPrestec(Integer.parseInt(idPrestec), (Date) eines.convertirDataString(eines.diaAvui()));
            ois.close();
        }catch (IOException | ClassNotFoundException | ParseException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaReserves(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String dadesConsulta = (String) ois.readObject();           
            oos.writeObject(sqlManager.prestec.llistaPrestecsActius(Integer.parseInt(dadesConsulta)));
            ois.close();
            oos.close();
        }catch (IOException | ClassNotFoundException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarAvisosUsuaris() {
       try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Usuari usuari = (Usuari) ois.readObject();           
            oos.writeObject(sqlManager.avisos.llistarNous(usuari.getId()));
            ois.close();
            oos.close();
        }catch (IOException | ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void avisLlegit() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String avis = (String) ois.readObject();           
            sqlManager.avisos.marcarllegit(Integer.parseInt(avis));
        }catch (IOException | ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearAvis() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Avis avis = (Avis) ois.readObject();   
            sqlManager.avisos.crearAvis(avis, usuari.getId());
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarComentariLlibre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();           
            oos.writeObject(sqlManager.comentaris.llistarComentarisLlibre(idLlibre));
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrarComentari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            //int idLlibre = (int) ois.readObject();           
            //sqlManager.comentaris
        }catch (IOException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void modificarComentari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();           
            //sqlManager.comentaris
        }catch (IOException | ClassNotFoundException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearComentari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Comentari com = (Comentari) ois.readObject();           
            sqlManager.comentaris.crearComentari(com);        
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarReservaLlibre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();
            oos.writeObject(sqlManager.reserves.llistarReservesLlibre(idLlibre));           
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void buscarReservaUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = (int) ois.readObject();           
            oos.writeObject(sqlManager.reserves.llistarReservesUsuari(idUsuari));
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mevesReserves() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = usuari.getId();
            oos.writeObject(sqlManager.reserves.llistarReservesUsuari(idUsuari));
        }catch (IOException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void finalitzarReserva() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idReserva = (int) ois.readObject();     
            java.util.Date dataRecollida = new java.util.Date();
            sqlManager.reserves.finalitzarReserva(idReserva, (Date) dataRecollida);
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPrestecIsbn() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int isbn = (int) ois.readObject();           
            oos.writeObject(sqlManager.prestec.obtenirPrestecLlibre(isbn));
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPrestecUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = (int) ois.readObject();           
            oos.writeObject(sqlManager.prestec.llistaPrestecsActius(idUsuari));
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void finalitzarPrestec() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = (int) ois.readObject();     
            java.util.Date dataRetorn = new java.util.Date();
            sqlManager.prestec.tancarPrestec(idUsuari, (Date) dataRetorn);
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarPrestec() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idPrestec = (int) ois.readObject();     
            java.util.Date dataRetorn = new java.util.Date();
            sqlManager.prestec.tancarPrestec(idPrestec, (Date) (dataRetorn));
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearPrestec() {
        
    }

}
