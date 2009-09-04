package net.druppi.demo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 14, 2009
 */
@SuppressWarnings("serial")
public class TableDemoModel extends AbstractTableModel {

    private static final String[] columnNames = {"String", "Integer", "Long", "Float", "Double", "Boolean"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    private static final Class<?>[] columnTypes = {String.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class};
    private List<List<Object>> data = new ArrayList<List<Object>>();

    /**
     * Creates a new TableDemoModel.
     */
    public TableDemoModel() {
        for (int i = 0; i < 10; i++) {
            List<Object> row = new ArrayList<Object>();
            int columnCount = getColumnCount();

            row.add(Integer.toString(i * columnCount + 0 + 1));
            row.add(new Integer(i * columnCount + 1 + 1));
            row.add(new Long(i * columnCount + 2 + 1));
            row.add(new Float(i * columnCount + 3 + 1));
            row.add(new Double(i * columnCount + 4 + 1));
            row.add(((i * columnCount + 5 + 1) % 4) == 0);

            data.add(row);
        }
        List<Object> row = new ArrayList<Object>();
        for (int i = 0; i < getColumnCount(); i++) {
            row.add(null);
        }
        data.add(row);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnName(final int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        return columnTypes[columnIndex];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        List<Object> row = data.get(rowIndex);
        return row.get(columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
        List<Object> row = data.get(rowIndex);
        row.set(columnIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return columnIndex == 2;
    }
}