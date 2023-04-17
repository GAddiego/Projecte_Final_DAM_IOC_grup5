package objectes;

import java.io.File;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Aleix
 */
public class UsuariIntern implements Serializable {
    java.util.Date data = new Date();
    Eines eines = new Eines();
    
    private int id;
    private String user;
    private String pass;
    private String rol;
    private String codi;//codi alfanumeric per si es torna a conectar no haver de buscar-lo a la BBDD
    private java.util.Date dataNaixement;
    private String nom;
    private String primerCognom;
    private String segonCognom;
    private String email;
    private Date dataAlta;
    private Date dataBaixa;
    private double multa;
    private boolean suspensio;
    private Date dataFinalSuspensio;
    private String rutaFoto;
    private long ultimaActualitzacio;
    private byte[] imageData;

    public UsuariIntern(String user, String pass, String rol, String nom, String primerCognom, String segonCognom, String email) {
        this.user = user;
        this.pass = pass;
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

    public UsuariIntern(int aInt, String string, String string0, Date convertirDataString, String string1, String string2, String string3, String string4, Date convertirDataString0, float aFloat, boolean aBoolean, Date convertirDataString1, String string5) {
    }

    public UsuariIntern(String username, String newpass, String newrol, String newnom, String newprimerCognom, String newsegonCognom, String newemail, String string) {
    }
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(java.util.Date dataNaixement) {
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
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

    public void setImageData(String ruta) {
        
        File file = new File(ruta);
        if (eines.comprovarRuta(ruta)) {
            byte[] imageData = new byte[(int) file.length()];
            this.imageData = imageData;
        }

    }
    
       
    @Override
    public String toString() {
        return "Usuari{" + "data=" + data + ", id=" + id + ", user=" + user + ", pass=" + pass + ", rol=" + rol + ", codi=" + codi + ", dataNaixement=" + dataNaixement + ", nom=" + nom + ", primerCognom=" + primerCognom + ", segonCognom=" + segonCognom + ", email=" + email + ", dataAlta=" + dataAlta + ", dataBaixa=" + dataBaixa + ", multa=" + multa + ", suspensio=" + suspensio + ", dataFinalSuspensio=" + dataFinalSuspensio + ", rutaFoto=" + rutaFoto + ", ultimaActualitzacio=" + ultimaActualitzacio + '}';
    }



}