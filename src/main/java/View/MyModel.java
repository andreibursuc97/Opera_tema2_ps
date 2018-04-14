package View;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by Andrei on 21/04/2017.
 */
public class MyModel extends AbstractTableModel {

    private  String[] columnNames;
    private ArrayList<String[]> list;


    public void setColumnNames(String[] columnNames)
    {
        this.columnNames=columnNames;
        fireTableStructureChanged();
    }

    public MyModel() {
        list = new ArrayList<String[]>();
        columnNames=new String[10];
    }

    public void addElement(String[] e) {
        // Adds the element in the last position in the list
        list.add(e);
        fireTableRowsInserted(list.size()-1, list.size()-1);
    }

    public void setList(ArrayList<String[]> list)
    {
        this.list=list;
    }


    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public int getRowCount() {
        return list.size();
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

            return list.get(rowIndex)[columnIndex];
    }

}
