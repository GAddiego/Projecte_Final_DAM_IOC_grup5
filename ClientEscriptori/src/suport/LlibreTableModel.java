package suport;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import objectes.Llibre;

public class LlibreTableModel extends AbstractTableModel {

    private List<Llibre> llibres;
    private String[] columnNames = {"Nom", "Autor", "Id", "Editorial", "Idioma", "Any", "Nota", "Ilustrador"};

    public LlibreTableModel(List<Llibre> llibres) {
        this.llibres = llibres;
    }


    @Override
    public int getRowCount() {
        return llibres.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Llibre llibre = llibres.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return llibre.getTitol();
            case 1:
                return llibre.getAutor();
            case 2:
                return llibre.getId();
            case 3:
                return llibre.getEditorial();
            case 4:
                return llibre.getIdioma();
            case 5:
                return llibre.getAnyPublicacio();
            case 6:
                return llibre.getNota();
            case 7:
                return llibre.getIllustrador();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
