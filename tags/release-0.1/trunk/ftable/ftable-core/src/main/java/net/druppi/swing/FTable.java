/*
 * FTable.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.swing;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.druppi.swing.table.format.FormatManager;


import ext.XTableColumnModel;

/**
 * A standard JTable with cell formatting.
 * 
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
public class FTable extends JTable {

    /** The serial version id. */
    private static final long serialVersionUID = -1518629652377949982L;

    /** The rendering manager. */
    private FormatManager formatManager;

    /**
     * Creates a new FTable.
     *
     * @see JTable#JTable()
     */
    public FTable() {
        this(null, null, null);
    }

    /**
     * Creates a new FTable.
     *
     * @param numRows the number of rows the table holds
     * @param numColumns the number of columns the table holds
     * @see JTable#JTable(int, int)
     */
    public FTable(final int numRows, final int numColumns) {
        this(new DefaultTableModel(numRows, numColumns));
    }

    /**
     * Creates a new FTable.
     *
     * @param rowData the data for the new table
     * @param columnNames names of each column
     * @see JTable#JTable(Vector, Vector)
     */
    public FTable(final Vector<?> rowData, final Vector<?> columnNames) {
        this(new DefaultTableModel(rowData, columnNames));
    }

    /**
     * Creates a new FTable.
     *
     * @param rowData the data for the new table
     * @param columnNames names of each column
     * @see JTable#JTable(Object[][], Object[])
     */
    @SuppressWarnings("serial")
    public FTable(final Object[][] rowData, final Object[] columnNames) {
        this(new AbstractTableModel() {
            public String getColumnName(final int column) {
                return columnNames[column].toString();
            }

            public int getRowCount() {
                return rowData.length;
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(final int row, final int col) {
                return rowData[row][col];
            }

            public boolean isCellEditable(final int row, final int column) {
                return true;
            }

            public void setValueAt(final Object value, final int row, final int col) {
                rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        });
    }

    /**
     * Creates a new FTable.
     *
     * @param dm the data model for the table
     * @see JTable#JTable(TableModel)
     */
    public FTable(final TableModel dm) {
        this(dm, null, null);
    }

    /**
     * Creates a new FTable.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     * @see JTable#JTable(TableModel, TableColumnModel)
     */
    public FTable(final TableModel dm, final TableColumnModel cm) {
        this(dm, cm, null);
    }

    /**
     * Creates a new FTable.
     *
     * @param dm the data model for the table
     * @param cm the column model for the table
     * @param sm the row selection model for the table
     * @see JTable#JTable(TableModel, TableColumnModel, ListSelectionModel)
     */
    public FTable(final TableModel dm, final TableColumnModel cm,
            final ListSelectionModel sm) {
        super(dm, cm, sm);

        formatManager = new FormatManager(this);

//        ListSelectionListener selectionRepainter = new ListSelectionListener() {
//
//            int firstRow = -1;
//            int lastRow = -1;
//            int firstColumn = -1;
//            int lastColumn = -1;
//
//            @Override
//            public void valueChanged(final ListSelectionEvent evt) {
//                if (evt.getValueIsAdjusting()) {
//                    if (evt.getSource().equals(selectionModel)) {
//                        firstRow = evt.getFirstIndex();
//                        lastRow = evt.getLastIndex();
//                    } else {
//                        firstColumn = evt.getFirstIndex();
//                        lastColumn = evt.getLastIndex();
//                    }
//
//                    if (firstRow >= 0 && lastRow >= 0 && firstColumn >= 0
//                            && lastColumn >= 0) {
//                        Rectangle rect1 = getCellRect(firstRow, firstColumn, true);
//                        Rectangle rect2 = getCellRect(lastRow, lastColumn, true);
//                        int x = rect1.x - 1;
//                        int width = rect2.x + rect2.width - rect1.x + 2;
//                        int y = rect1.y - 1;
//                        int height = rect2.y + rect2.height - rect1.y + 2;
//
//                        RepaintManager.currentManager(FTable.this).addDirtyRegion(
//                                FTable.this, x, y, width, height);
//                        System.out.println("repaint from " + firstRow + ", "
//                                + firstColumn + " to " + lastRow + ", " + lastColumn
//                                + " | " + x + ", " + y + " - " + width + ", " + height);
////                        lastRow = -1;
////                        firstRow = -1;
////                        firstColumn = -1;
////                        lastColumn = -1;
//                    }
//                }
//            }
//        };
//        selectionModel.addListSelectionListener(selectionRepainter);
//        columnModel.getSelectionModel().addListSelectionListener(selectionRepainter);
    }

    /**
     * Returns an appropriate renderer for the cell specified by this row and column. The
     * renderer is provided by the table's FormatManager.
     *
     * @param row the row of the cell to render, where 0 is the first row
     * @param column the column of the cell to render, where 0 is the first column
     * @return the assigned renderer; if <code>null</code> returns the default renderer
     *         for this type of object.
     * @see JTable#getCellRenderer(int, int)
     * @see FormatManager#getCellRenderer(int, int)
     */
    @Override
    public TableCellRenderer getCellRenderer(final int row, final int column) {
        return formatManager.getCellRenderer(row, column);
    }

    /**
     * Returns an appropriate editor for the cell specified by row and column. The editor
     * is provided by the table's FormatManager.
     *
     * @param row the row of the cell to edit, where 0 is the first row
     * @param column the column of the cell to edit, where 0 is the first column
     * @return the editor for this cell; if null return the default editor for this type
     *         of cell.
     * @see JTable#getCellEditor(int, int)
     * @see FormatManager#getCellEditor(int, int)
     */
    @Override
    public TableCellEditor getCellEditor(final int row, final int column) {
        return formatManager.getCellEditor(row, column);
    }

    /**
     * Returns the associated format manager.
     *
     * @return the table's format manager.
     */
    public FormatManager getFormatManager() {
        return formatManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TableColumnModel createDefaultColumnModel() {
        return new XTableColumnModel();
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void paintComponent(final Graphics g) {
//        super.paintComponent(g);
//
//        // Paint the grid around the focus cell in black
//        int row = selectionModel.getLeadSelectionIndex();
//        int column = columnModel.getSelectionModel().getLeadSelectionIndex();
//
//        if (row >= 0 && column >= 0) {
//            Rectangle rect = getCellRect(row, column, true);
//            Rectangle inner = (Rectangle) rect.clone();
//            rect.x = rect.x - 2;
//            rect.y = rect.y - 2;
//            rect.width = rect.width + 3;
//            rect.height = rect.height + 3;
//
//            inner.x = inner.x + 1;
//            inner.y = inner.y + 1;
//            inner.width = inner.width - 1;
//            inner.height = inner.height - 1;
//
//            Area area = new Area(rect);
//            area.subtract(new Area(inner));
//
//            g.setColor(new Color(0, 0, 0, 192));
//            ((Graphics2D) g).fill(area);
//        }
//    }
}
