package objectes;

import java.util.Date;

/**
 *
 * @author aleix
 */
public class Avis {
    private int id;
    private int id_usuari;
    private String titol;
    private String missatge;
    private Date dataCreacio;
    private boolean llegit;
    private int idCreador;

    public Avis(int idUsuari, String titol, String missatge, Date dataCreacio, boolean llegit, int idCreador) {
        this.id_usuari = idUsuari;
        this.titol = titol;
        this.missatge = missatge;
        this.dataCreacio = dataCreacio;
        this.llegit = llegit;
        this.idCreador= idCreador;
    }
    
    public Avis(int id, int idUsuari, String titol, String missatge, Date dataCreacio, boolean llegit, int idCreador) {
        this.id = id;
        this.id_usuari = idUsuari;
        this.titol = titol;
        this.missatge = missatge;
        this.dataCreacio = dataCreacio;
        this.llegit = llegit;
        this.idCreador= idCreador;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getId_usuari() {
        return id_usuari;
    }

    public void setId_usuari(int id_usuari) {
        this.id_usuari = id_usuari;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public boolean isLlegit() {
        return llegit;
    }

    public void setLlegit(boolean llegit) {
        this.llegit = llegit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }
    
}
