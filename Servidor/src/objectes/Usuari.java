package objectes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aleix
 */
public class Usuari implements Serializable{
    private static long serialVersionUID = 1436768778619850352L;
    private int id;
    private String user;
    private byte[] passX;
    private String rol;
    private java.util.Date dataNaixement;
    private String nom;
    private String primerCognom;
    private String segonCognom;
    private String email;
    private Date dataAlta;
    private Date dataBaixa;
    private double multa;
    private boolean suspensio;
    private  Date dataFinalSuspensio;
    private long ultimaActualitzacio;
    private byte[] imageData;

    public Usuari(int id, String user, byte[] passX, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, long ultimaActualitzacio, byte[] imageData) {
        this.id = id;
        this.user = user;
        this.passX = passX;
        this.rol = rol;
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
        this.ultimaActualitzacio = ultimaActualitzacio;
        this.imageData = imageData;
    }
    
    public Usuari(int id, String user, byte[] passX, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, byte[] imageData) {
        this.id = id;
        this.user = user;
        this.passX = passX;
        this.rol = rol;
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

    

    public Usuari(UsuariIntern u) {
        this.id = u.getId();
        this.user = u.getUser();
        this.passX = u.getPass();
        this.rol = u.getRol();
        this.dataNaixement = u.getDataNaixement();
        this.nom = u.getNom();
        this.primerCognom = u.getPrimerCognom();
        this.segonCognom = u.getSegonCognom();
        this.email = u.getEmail();
        this.dataAlta = u.getDataAlta();
        this.dataBaixa = u.getDataBaixa();
        this.multa = u.getMulta() ;
        this.suspensio = u.isSuspensio();
        this.dataFinalSuspensio = u.getDataFinalSuspensio();
        this.ultimaActualitzacio = u.getUltimaActualitzacio();
        this.imageData = u.getImageData();
    }

    public Usuari(String user, byte[] passX, String rol, String nom, String primerCognom, String segonCognom, String email) {
        
        this.user = user;
        this.passX = passX;
        this.rol = rol;
        this.nom = nom;
        this.primerCognom = primerCognom;
        this.segonCognom = segonCognom;
        this.email = email;
        
        //Pq no dongui error al crear un usuari. No es grava a la base de dades.
        this.id = 0; 
        this.dataNaixement = null;
        this.dataAlta = null;
        this.dataBaixa = null;
        this.multa = 0;
        this.suspensio = false;
        this.dataFinalSuspensio = null;
        this.ultimaActualitzacio = 0;
        this.imageData = null;
    }
    
    public Usuari(){
        
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Usuari(int id, String user, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, long ultimaActualitzacio, byte[] imageData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }
    
    public byte[] getPassX() {
        return passX;
    }

    public String getRol() {
        return rol;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public String getNom() {
        return nom;
    }

    public String getPrimerCognom() {
        return primerCognom;
    }

    public String getSegonCognom() {
        return segonCognom;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
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

    public Date getDataFinalSuspensio() {
        return dataFinalSuspensio;
    }

    public long getUltimaActualitzacio() {
        return ultimaActualitzacio;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Usuari.serialVersionUID = serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassX(byte[] pass) {
        this.passX = pass;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrimerCognom(String primerCognom) {
        this.primerCognom = primerCognom;
    }

    public void setSegonCognom(String segonCognom) {
        this.segonCognom = segonCognom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public void setSuspensio(boolean suspensio) {
        this.suspensio = suspensio;
    }

    public void setDataFinalSuspensio(Date dataFinalSuspensio) {
        this.dataFinalSuspensio = dataFinalSuspensio;
    }

    public void setUltimaActualitzacio(long ultimaActualitzacio) {
        this.ultimaActualitzacio = ultimaActualitzacio;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    
    

    @Override
    public String toString() {
        return "Usuari{" + "id=" + id + ", user=" + user + ", passX=" + passX + ", rol=" + rol + ", dataNaixement=" + dataNaixement + ", nom=" + nom + ", primerCognom=" + primerCognom + ", segonCognom=" + segonCognom + ", email=" + email + ", dataAlta=" + dataAlta + ", dataBaixa=" + dataBaixa + ", multa=" + multa + ", suspensio=" + suspensio + ", dataFinalSuspensio=" + dataFinalSuspensio + ", ultimaActualitzacio=" + ultimaActualitzacio + ", imageData=" + imageData + '}';
    }
    
 
}
