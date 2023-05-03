package objectes;

import java.sql.Timestamp;
import java.util.Date;

public class Reserva {
    private int id;
    private int idUsuari;
    private int idLlibre;
    private Date dataReserva;
    private Date dataRecollida;

    public Reserva(int id, int idUsuari, int idLlibre, Timestamp dataReserva, Date dataRecollida) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataReserva = new Date(dataReserva.getTime());
        this.dataRecollida = dataRecollida != null ? new Date(dataRecollida.getTime()) : null;
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
