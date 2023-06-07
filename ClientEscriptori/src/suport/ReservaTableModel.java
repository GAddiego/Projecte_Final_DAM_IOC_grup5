package suport;

import clientescriptori.Connection;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import objectes.Llibre;
import objectes.Reserva;

public class ReservaTableModel extends AbstractTableModel {

    private List<Reserva> reserves;
    private List<Llibre> llibres = new ArrayList<>();
    private String[] columnNames = {"Nom llibre", "Autor", "Id", "Data reserva"};

    public ReservaTableModel(List<Reserva> reserves) {
        this.reserves = reserves;
        for (Reserva r : reserves) {
            Llibre llibre = null;
            try {
                llibre = Connection.recuperarNomLlibrePerId(r.getIdLlibre());
                llibres.add(llibre);
            } catch (IOException ex) {
                Logger.getLogger(ReservaTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReservaTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public int getRowCount() {
        return reserves.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reserva reserva = reserves.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return llibres.get(rowIndex).getTitol();
            case 1:
                return llibres.get(rowIndex).getAutor();
            case 2:
                return llibres.get(rowIndex).getId();
            case 3:
                return reserva.getDataReserva();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
