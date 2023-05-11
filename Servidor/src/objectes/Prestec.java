/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectes;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author aleix
 */
public class Prestec implements Serializable{
        private static final long serialVersionUID = 1436768778619850352L;
        private int id;
        private int idUsuari;
        private int idLlibre;
        private java.util.Date dataPrestec;
        private java.util.Date dataRetorn;
        private java.util.Date fiPrestec;
        private boolean prolongacio;
    public Prestec() {

    }

    public Prestec(int id, int idUsuari, int idLlibre, java.util.Date dataPrestec, java.util.Date dataRetorn, boolean prolongacio) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataRetorn = dataRetorn;
        this.prolongacio = prolongacio;
    }

    public Prestec(List<Prestec> obtenirPrestecsActius) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public java.util.Date getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(java.util.Date dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public java.util.Date getDataRetorn() {
        return dataRetorn;
    }

    public void setDataRetorn(java.util.Date dataRetorn) {
        this.dataRetorn = dataRetorn;
    }

    public java.util.Date getFiPrestec() {
        return fiPrestec;
    }

    public void setFiPrestec(java.util.Date fiPrestec) {
        this.fiPrestec = fiPrestec;
    }

    public boolean isProlongacio() {
        return prolongacio;
    }

    public void setProlongacio(boolean prolongacio) {
        this.prolongacio = prolongacio;
    }
    
}
