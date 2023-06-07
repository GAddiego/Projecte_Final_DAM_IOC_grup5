/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aleix
 */
public class Usuari implements Serializable {

    private static final long serialVersionUID =  -2326600962943963203L;
//    private static final long serialVersionUID = 1436768778619850352L;
    private int id;
    private String user;
    private String pass;
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
    private Date dataFinalSuspensio;
    private long ultimaActualitzacio;
    private byte[] imageData;
    private byte[] passX;

    public Usuari(int id, String user, String pass, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, long ultimaActualitzacio, byte[] imageData) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.passX = pass.getBytes();
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

    public Usuari(int id, String user, String pass, String rol, Date dataNaixement, String nom, String primerCognom, String segonCognom, String email, Date dataAlta, Date dataBaixa, double multa, boolean suspensio, Date dataFinalSuspensio, byte[] imageData) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.passX = pass.getBytes();
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
        this.pass = u.getPass();        
        this.passX = pass.getBytes();
        this.rol = u.getRol();
        this.dataNaixement = u.getDataNaixement();
        this.nom = u.getNom();
        this.primerCognom = u.getPrimerCognom();
        this.segonCognom = u.getSegonCognom();
        this.email = u.getEmail();
        this.dataAlta = u.getDataAlta();
        this.dataBaixa = u.getDataBaixa();
        this.multa = u.getMulta();
        this.suspensio = u.isSuspensio();
        this.dataFinalSuspensio = u.getDataFinalSuspensio();
        this.ultimaActualitzacio = u.getUltimaActualitzacio();
        this.imageData = u.getImageData();
    }

    public Usuari(String user, String pass, String rol, String nom, String primerCognom, String segonCognom, String email) {

        this.user = user;
        this.pass = pass;
        this.passX = pass.getBytes();
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

    public Usuari() {

    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
        this.passX = pass.getBytes();
    }

    public void setPassX(byte[] passx) {
        this.passX = passx;
    }

    public byte[] getPassX() {
        return passX;
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
        return "Usuari{" + "id=" + id + ", user=" + user + ", pass=" + pass + ", rol=" + rol + ", dataNaixement=" + dataNaixement + ", nom=" + nom + ", primerCognom=" + primerCognom + ", segonCognom=" + segonCognom + ", email=" + email + ", dataAlta=" + dataAlta + ", dataBaixa=" + dataBaixa + ", multa=" + multa + ", suspensio=" + suspensio + ", dataFinalSuspensio=" + dataFinalSuspensio + ", ultimaActualitzacio=" + ultimaActualitzacio + ", imageData=" + imageData + '}';
    }

}
