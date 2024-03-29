package objectes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


/**
 *
 * @author Aleix
 */
public class UsuariIntern {
    java.util.Date data = new Date();
    Eines eines = new Eines();
    
    private int id;
    private String user;
    //private String pass;
    private String rol;
    private String codi;//codi alfanumeric per si es torna a conectar no haver de buscar-lo a la BBDD
    private Date dataNaixement;
    private String nom;
    private String primerCognom;
    private String segonCognom;
    private String email;
    private Date dataAlta;
    private Date dataBaixa;
    private double multa;
    private boolean suspensio;
    private Date dataFinalSuspensio;
    private long ultimaActualitzacio;
    private byte[] imageData;
    private byte[] passX;
     
    public UsuariIntern(String user, byte[] passX, String rol, String codi, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, byte[] imageData) {
        this.user = user;
        //this.pass = pass;
        this.passX = passX;
        this.rol = rol;
        this.codi = codi;
        this.dataNaixement = dataNaixement;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        this.dataAlta = dataAlta;
        this.dataBaixa = dataBaixa;
        this.multa = multa;
        this.suspensio = suspensio;
        this.dataFinalSuspensio = dataFinalSuspensio;
        this.imageData = imageData;
    }

    
    
    public UsuariIntern(String user, byte[] passX, String rol, String nom, String primerCognom, String segonCognom, String email) {
        this.user = user;
        this.passX = passX;
        this.rol = rol;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        this.dataAlta = data;
        this.ultimaActualitzacio = System.currentTimeMillis();
    }

    public UsuariIntern() {
        
        this.ultimaActualitzacio = System.currentTimeMillis();
    }

    public UsuariIntern(int id, String user, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, double multa, boolean suspensio, Date dataFinalSuspensio) {
        this.id = id;
        this.user = user;
        this.rol = rol;
        this.dataNaixement = dataNaixement;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        this.dataAlta = dataAlta;
        this.multa = multa;
        this.suspensio = suspensio;
        this.dataFinalSuspensio = dataFinalSuspensio;

    }



    public UsuariIntern(Usuari u) {
        this.id = u.getId();
        this.user = u.getUser();
        this.passX = u.getPassX();
        this.rol = u.getRol();
        this.dataNaixement = u.getDataNaixement();
        this.nom = u.getNom();
        this.primerCognom = u.getPrimerCognom();
        this.segonCognom = u.getSegonCognom();
        this.email = u.getEmail();
        this.dataAlta = u.getDataAlta();
        this.multa = u.getMulta();
        this.suspensio = u.isSuspensio();
        this.dataFinalSuspensio = u.getDataFinalSuspensio();
        this.imageData = u.getImageData();
        this.ultimaActualitzacio = System.currentTimeMillis();
    }

    public UsuariIntern(int id, String user, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, float multa, boolean suspensio, Date dataFinalSuspensio, byte[] imageData) {
        this.id = id;
        this.user = user;
        this.rol = rol;
        this.dataNaixement = dataNaixement;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        this.dataAlta = dataAlta;
        this.multa = multa;
        this.suspensio = suspensio;
        this.dataFinalSuspensio = dataFinalSuspensio;
        this.imageData = imageData;
        
    }

    public UsuariIntern(int id, String user, String rol, Date dataNaixement, String nom, 
            String primerCognom, String segonCognom, String email, Date dataAlta, float multa,
            boolean suspensio, Date dataFinalSuspensio, byte[] imageData, byte[] passX) {
        this.id = id;
        this.user = user;
        this.rol = rol;
        this.dataNaixement = dataNaixement;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        this.dataAlta = dataAlta;
        this.multa = multa;
        this.suspensio = suspensio;
        this.dataFinalSuspensio = dataFinalSuspensio;
        this.imageData = imageData;
        this.passX = passX;
    }
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public byte[] getPass() {
        return passX;
    }

    public void setPass(byte[] passX) {
        this.passX = passX;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getPrimerCognom() {
        return primerCognom;
    }

    public void setPrimerCognom(String primerCognom) {
        this.primerCognom = primerCognom;
    }

    public String getSegonCognom() {
        return segonCognom;
    }

    public void setSegonCognom(String segonCognom) {
        this.segonCognom = segonCognom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
    
    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean isSuspensio() {
        return suspensio;
    }

    public void setSuspensio(boolean suspensio) {
        this.suspensio = suspensio;
    }

    public Date getDataFinalSuspensio() {
        return dataFinalSuspensio;
    }

    public void setDataFinalSuspensio(Date dataFinalSuspensio) {
        this.dataFinalSuspensio = dataFinalSuspensio;
    }

    public long getUltimaActualitzacio() {
        return ultimaActualitzacio;
    }

    public void setUltimaActualitzacio() {
        this.ultimaActualitzacio = System.currentTimeMillis();
    }

    public byte[] getImageData() {
        return imageData;
    }
    
    public void setImageData(byte[] imageData){
        this.imageData = imageData;
    }

    public void convertirImageData(String ruta) throws IOException {
        
        if (eines.comprovarRuta(ruta)) {
            this.imageData = Files.readAllBytes(Paths.get(ruta));
        }

    }

    @Override
    public String toString() {
        return "UsuariIntern{id=" + id + ", user=" + user + ", pass=" + passX + ", rol=" + rol + ", codi=" + codi + ", dataNaixement=" + dataNaixement + ", nom=" + nom + ", primerCognom=" + primerCognom + ", segonCognom=" + segonCognom + ", email=" + email + ", dataAlta=" + dataAlta + ", dataBaixa=" + dataBaixa + ", multa=" + multa + ", suspensio=" + suspensio + ", dataFinalSuspensio=" + dataFinalSuspensio + ", ultimaActualitzacio=" + ultimaActualitzacio + ", imageData=" + imageData + '}';
    }
    
       
   



}