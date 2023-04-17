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
public class Usuari implements Serializable{
    private static final long serialVersionUID = 1436768778619850352L;
    private final int id;
    private final String user;
    private final String pass;
    private final String rol;
    private final java.util.Date dataNaixement;
    private final String nom;
    private final String primerCognom;
    private final String segonCognom;
    private final String email;
    private final Date dataAlta;
    private final Date dataBaixa;
    private final double multa;
    private final boolean suspensio;
    private final Date dataFinalSuspensio;
    private final long ultimaActualitzacio;
    private final byte[] imageData;

    public Usuari(UsuariIntern u) {
        this.id = u.getId();
        this.user = u.getUser();
        this.pass = u.getPass();
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
    
    
}
