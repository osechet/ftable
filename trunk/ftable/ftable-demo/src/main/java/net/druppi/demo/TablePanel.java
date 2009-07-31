/*
 * TablePanel.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.druppi.swing.FTable;
import net.druppi.swing.table.config.PopupConfigurator;
import net.druppi.swing.table.format.Alignment;
import net.druppi.swing.table.format.CellFormat;
import net.druppi.swing.table.format.Condition;
import net.druppi.swing.table.format.FormatManager;


/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 8, 2009
 */
@SuppressWarnings("serial")
public class TablePanel extends JPanel {

    private JTextField filterText;
    private TableRowSorter<TableModel> sorter;

    /**
     * Creates a new TablePanel.
     */
    public TablePanel() {
        initComponents();
    }

    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        final FTable table = new FTable(new Model());
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        add(new JScrollPane(table));
        filterText = new JTextField();
        add(filterText, BorderLayout.SOUTH);

        FormatManager formatManager = table.getFormatManager();
        // All the cells whose value is null have a blue background
        CellFormat format = formatManager.getCellFormat(null, null, null, null, new Color(224, 224, 255));
        formatManager.setConditionFormat(new Condition() {

            @Override
            public boolean matches(final FTable table, final int rowIndex, final int columnIndex) {
                return table.getModel().getValueAt(rowIndex, columnIndex) == null;
            }
        }, format);

        // All the cells of the second column are right-aligned
        CellFormat format2 = formatManager.getCellFormat(Alignment.RIGHT, null, null, null, null);
        formatManager.setColumnFormat(1, format2);

        // All the cells of the 4th and 7th rows have a green background
        CellFormat format3 = formatManager.getCellFormat(null, null, null, null, new Color(224, 255, 224));
        formatManager.setRowFormat(3, format3);
        formatManager.setRowFormat(6, format3);

        PopupConfigurator.createPopupMenu(table);

        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        filterText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
    }

    private void newFilter() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    /**
     * @author Olivier Sechet
     * @version 1.0 - Apr 14, 2009
     */
    private static class Model extends AbstractTableModel {

        private static final String[] columnNames = {"String", "Integer", "Long", "Float", "Double", "Boolean"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        private static final Class<?>[] columnTypes = {String.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class};
        private List<List<Object>> data = new ArrayList<List<Object>>();

        /**
         * Creates a new Model.
         */
        private Model() {
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
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }

        /*
         * @see javax.swing.table.TableModel#getRowCount()
         */
        @Override
        public int getRowCount() {
            return data.size();
        }

        /*
         * @see javax.swing.table.TableModel#getValueAt(int, int)
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            List<Object> row = data.get(rowIndex);
            return row.get(columnIndex);
        }

        /*
         * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
         */
        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            List<Object> row = data.get(rowIndex);
            row.set(columnIndex, value);
        }

        /*
         * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
         */
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 2;
        }
    }
}
