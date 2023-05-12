package objectes;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable{
    private static final long serialVersionUID = 1436768778619850352L;
    private int id;
    private int idUsuari;
    private int idLlibre;
    private Date dataReserva;
    private Date dataRecollida;

    public Reserva(int id, int idUsuari, int idLlibre, Date dataReserva, Date dataRecollida) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataReserva = dataReserva;
        this.dataRecollida = dataRecollida;
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

    public Date getDataReserva() {
        return dataReserva;
    }

    public Date getDataRecollida() {
        return dataRecollida;
    }

    public void setDataRecollida(Date dataRecollida) {
        this.dataRecollida = dataRecollida;
    }
}
