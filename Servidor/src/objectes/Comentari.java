package objectes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aleix
 */
public class Comentari implements Serializable {
    private static final long serialVersionUID = 1436768778619850352L;
        private int id;
        private int idUsuari;
        private int idLlibre;
        private java.util.Date dataCreacio;
        private String comentari;

    public Comentari() {
    }

    public Comentari(int id, int idUsuari, int idLlibre, Date dataCreacio, String comentari) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataCreacio = dataCreacio;
        this.comentari = comentari;
    }

    public Comentari(int idUsuari, int idLlibre, Date dataCreacio, String comentari) {
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataCreacio = dataCreacio;
        this.comentari = comentari;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public String getComentari() {
        return comentari;
    }

    @Override
    public String toString() {
        return "Comentari{" + "id=" + id + ", idUsuari=" + idUsuari + ", idLlibre=" + idLlibre + ", dataCreacio=" + dataCreacio + ", comentari=" + comentari + '}';
    }
    
    
}


