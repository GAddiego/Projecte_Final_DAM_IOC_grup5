 package servidor;


import BBDD.MissatgesPredefinits;
import BBDD.SqlManager;
import objectes.UsuariIntern;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
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
    private final MissatgesPredefinits mp ;
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
        this.mp = new MissatgesPredefinits();
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
                    case "dades_usuari_altre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                        enviarDadesUsuariAltre();
                        } else {
                            out.writeUTF("no tens permisos");
                        }
                        break;
                    case "qui_es":
                        enviarDadesAltreUsuari();
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
                            ampliarPrestec();
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
                        break;
                    case "buscar_prestec_llibre":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            buscarPrestecLlibre();
                        }
                        break;
                    case "buscar_prestec_isbn":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria")) {
                            //buscarPrestecIsbn();
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
                        System.out.println("buscar_avisos_usuari");
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            buscarAvisosUsuaris();
                        }
                        break;
                    case "buscar_llibre_id":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            buscarLlibreId();
                        }
                        break;
                    case "meves_reserves":
                        if (usuari.getRol().equals("admin") || usuari.getRol().equals("bibliotecaria") || usuari.getRol().equals("usuari")) {
                            mevesReserves();
                        }
                        break;
                }
                in.close();
                out.close();
                clientSocket.close();
            }
            
        } catch (IOException e) {
            //Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, e);
        } catch (ParseException ex) {
            //Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
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
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, "enviarDadesUsuari ha fallat", ex);
        }
    }
    
    private void enviarDadesUsuariAltre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String idUsuari = (String) ois.readObject();
            if(sqlManager.uIntern.existeixNomUsuari(idUsuari)){
                Usuari u = new Usuari(sqlManager.uIntern.getUsuariNomesUserName(idUsuari));
                oos.writeObject(u);
            }else{
                Usuari u = new Usuari();
                u = null;
                oos.writeObject(u);
            }
            oos.close();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un usuari a partir de les dades rebudes del client.
     */
    private void crearUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Usuari u = (Usuari) ois.readObject();
            System.out.println(u.toString());
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
            System.out.println("borrem usuari " + userName);
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
        }catch (IOException | ClassNotFoundException  ex) {
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
            //Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarLlistaLlibres(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String[] dadesConsulta = (String[]) ois.readObject();
            //System.out.println("Numero de llibres trobats: " + (int) sqlManager.llibres.buscarLlibres(dadesConsulta).size());
            oos.writeObject(sqlManager.llibres.buscarLlibres(dadesConsulta));
        }catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearPrestec(){
        System.out.println("crear prestec");
        String missatge;
        Eines eines = new Eines();
        java.util.Date date = new java.util.Date();
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            Usuari usuari = (Usuari) ois.readObject();
            UsuariIntern ui = sqlManager.uIntern.getUsuari(usuari.getUser());
            List<Reserva> reserves = sqlManager.reserves.llistarReservesLlibre(llibre.getId());
            System.out.println("Intent de prestec: " + ui.getId() + " " + llibre.getId()  );
            reserves = sqlManager.reserves.llistarReservesLlibre(llibre.getId());
            if (sqlManager.prestec.prestecActiuLlibre(llibre.getId()).size()>0){
                System.out.println("ja està prestat");
                missatge ="Ja està prestat";
            }else{              
                    sqlManager.prestec.crearPrestec(ui.getId(), llibre.getId(), date, eines.convertirDataString(eines.diaRetorn()), false);
                    missatge = "Prestec realitzat fins el dia " + eines.diaRetorn();
                    System.out.println("Prestec realitzat fins el dia " + eines.diaRetorn());
                
            }
            oos.writeObject(missatge);
            oos.flush();
            ois.close();
            oos.close();
            
         }   catch (IOException | ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void ampliarPrestec(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            Llibre llibre = (Llibre) ois.readObject();
            System.out.println(llibre.getId());
            List<Prestec> prestecsActius =  sqlManager.prestec.prestecActiuLlibre(llibre.getId());
            java.util.Date dataVenciment = eines.ampliacióRetorn(prestecsActius.get(0).getdataVenciment() );
            sqlManager.prestec.modificarPrestec(prestecsActius.get(0).getId(),dataVenciment);
            ois.close();
        }catch (IOException | ClassNotFoundException   ex) {
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
    /*
    private void consultarLlistaPrestecs(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String dadesConsulta = (String) ois.readObject();           
            oos.writeObject(sqlManager.prestec.llistaPrestecActiu(Integer.parseInt(dadesConsulta)));
        }catch (IOException | ClassNotFoundException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    private void crearReserva(){
        java.util.Date date = new java.util.Date();
        
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            int idLlibre = (int) ois.readObject();      
            System.out.println("id llibre " + idLlibre);
            sqlManager.reserves.crearReserva(usuari.getId(), idLlibre, date, null);
            if(sqlManager.prestec.prestecActiuLlibre(idLlibre).size()<=0){
                mp.enviarAvisLlibreDisponible(usuari.getId(), idLlibre);
            }
                ois.close();
        }catch (IOException | ClassNotFoundException | SQLException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tancarReserva() {
        System.out.println("Tancar reserva");
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String idPrestec = (String) ois.readObject();
            sqlManager.prestec.tancarPrestec(Integer.parseInt(idPrestec), (Date) eines.convertirDataString(eines.diaAvui()));
            ois.close();
        }catch (IOException | ClassNotFoundException | ParseException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    private void consultarLlistaReserves(){
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            String dadesConsulta = (String) ois.readObject();           
            oos.writeObject(sqlManager.prestec.Integer.parseInt(dadesConsulta)));
            ois.close();
            oos.close();
        }catch (IOException | ClassNotFoundException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    private void buscarAvisosUsuaris() {
        System.out.println("buscar avisos nous");
        try{ 
        List <Avis> a  = sqlManager.avisos.llistarNous(usuari.getId());
        String str = a.get(0).toString();
        System.out.println("Numero d'avisos "+a.size());
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        oos.writeObject(sqlManager.avisos.llistarNous(usuari.getId()));
        oos.flush();
        //oos.wait(1000);
        }catch (SQLException  ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void avisLlegit() {
        System.out.println("mòdul avis llegit");
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            String id_avis = (String) ois.readObject();         
            sqlManager.avisos.marcarllegit(Integer.parseInt(id_avis));
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
    

    private void modificarComentari() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            Comentari com = (Comentari) ois.readObject();           
            sqlManager.comentaris.modificarComentari(com);
        }catch (IOException | ClassNotFoundException | SQLException ex) {
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
        System.out.println("buscar reserva llibre");
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();
            oos.writeObject(sqlManager.reserves.llistarReservesLlibre(idLlibre));           
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void buscarReservaUsuari() {
        System.out.println("buscar reserva usuari");
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
        System.out.println("finalitzar reserva");
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())){
            int idReserva = (int) ois.readObject();     
            sqlManager.reserves.eliminarReserva(idReserva);
        }catch (IOException | ClassNotFoundException | SQLException   ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarPrestecUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = (int) ois.readObject();           
            if (("admin".equals(usuari.getRol()))||("bibliotecaria".equals(usuari.getRol()))){
                oos.writeObject(sqlManager.prestec.prestecActiuUsuari(idUsuari));
            }else{
                oos.writeObject(sqlManager.prestec.prestecActiuUsuari(usuari.getId()));
            }
            
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private void buscarPrestecLlibre() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();           
            oos.writeObject(sqlManager.prestec.prestecActiuLlibre(idLlibre));

            
        }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void finalitzarPrestec() {
        System.out.println("finalitzar prestec");
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            int idPrestec = (int) ois.readObject();     
            java.util.Date dataRetorn = new java.util.Date();
            sqlManager.prestec.tancarPrestec(idPrestec,(java.util.Date) dataRetorn);
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

    private void buscarLlibreId() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idLlibre = (int) ois.readObject();     
            java.util.Date dataRetorn = new java.util.Date();      
            oos.writeObject(sqlManager.llibres.buscarLlibreid(idLlibre));
            }catch (IOException | ClassNotFoundException    ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarDadesAltreUsuari() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            int idUsuari = (int) ois.readObject();
            Usuari u = new Usuari(sqlManager.uIntern.getUsuariRetornaReduit(idUsuari));
            oos.writeObject(u);
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }    }

}
