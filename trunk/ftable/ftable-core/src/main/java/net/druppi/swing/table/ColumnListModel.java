/*
 * ColumnListModel.java
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
package net.druppi.swing.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.table.TableColumn;

/**
 * This class is used by the OrganizeColumnForm class to manage the two lists of columns.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 16, 2009
 */
class ColumnListModel extends AbstractListModel {

    /** The default table comparator. */
    private static final TableColumnComparator COLUMN_COMPARATOR = new TableColumnComparator();

    /** The serial version id. */
    private static final long serialVersionUID = -7050510331042231935L;

    /** The associated model. */
    private final XTableColumnModel model;

    /** The columns managed by this model. */
    private final List<TableColumn> columns = new ArrayList<TableColumn>();

    /** <code>true</code> if this model manages visible columns. */
    private final boolean manageVisible;

    /**
     * Creates a new ColumnListModel.
     *
     * @param model the managed column model.
     * @param manageVisible true if this model manages the visible columns. false if it
     *        manages the hidden columns.
     */
    public ColumnListModel(final XTableColumnModel model, final boolean manageVisible) {
        this.model = model;
        this.manageVisible = manageVisible;
        fromColumnModel();
    }

    /**
     * Updates this model using the column model.
     */
    private void fromColumnModel() {
        Enumeration<TableColumn> en;
        if (manageVisible) {
            en = model.getColumns(true);
        } else {
            en = model.getHiddenColumns();
        }
        for (; en.hasMoreElements();) {
            TableColumn column = en.nextElement();
            columns.add(column);
        }
    }

    /**
     * Updates the associated column model using the data of this model. If this model
     * manages the visible columns of the column model, each columns of the list is set
     * visible and then moved to the correct index. If this model manages the hidden
     * columns, all the columns of the list are set "invisible".
     */
    public void updateColumnModel() {
        if (manageVisible) {
            for (int i = 0; i < columns.size(); i++) {
                TableColumn column = columns.get(i);
                model.setColumnVisible(column, true);
                int index = model.getColumnIndex(column.getIdentifier());
                model.moveColumn(index, i);
            }
        } else {
            for (TableColumn column : columns) {
                model.setColumnVisible(column, false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getElementAt(final int index) {
        return columns.get(index).getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return columns.size();
    }

    /**
     * Moves the specified rows to the specified index.
     *
     * @param rows the indices of the rows to move.
     * @param rowIndex the index of the row where the rows will be moved.
     */
    public final void moveRowsTo(final int[] rows, final int rowIndex) {
        int offset = rowIndex - rows[0];
        if (rowIndex < 0 || rows[rows.length - 1] + offset >= columns.size()) {
            throw new IllegalArgumentException(
                    "The destination index must be in the range."); //$NON-NLS-1$
        }
        if (offset < 0) {
            // Move up
            for (int i = 0; i < rows.length; i++) {
                int row = rows[i];
                TableColumn value = columns.remove(row);
                columns.add(row + offset, value);
            }
            fireContentsChanged(this, rows[0] + offset, rows[rows.length - 1]);
        } else if (offset > 0) {
            // Move down
            for (int i = rows.length - 1; i >= 0; i--) {
                int row = rows[i];
                TableColumn value = columns.remove(row);
                columns.add(row + offset, value);
            }
            fireContentsChanged(this, rows[0], rows[rows.length - 1] + offset);
        }
    }

    /**
     * Returns the column specified by the given index.
     *
     * @param index the column's index.
     * @return the column with the specified index.
     * @throw IndexOutOfBoundsException if the index is out of range (<code>index < 0 || index
     *        >= getSize()</code>)
     */
    public TableColumn getColumn(final int index) {
        return columns.get(index);
    }

    /**
     * Returns an array of all the columns in this model.
     *
     * @return all the columns in this model.
     */
    public TableColumn[] getColumns() {
        return columns.toArray(new TableColumn[columns.size()]);
    }

    /**
     * Adds the specified column to this model.
     *
     * @param column the column to add.
     */
    public void add(final TableColumn column) {
        if (manageVisible) {
            int index = columns.size();
            columns.add(column);
            fireIntervalAdded(this, index, index);
        } else {
            // The available list model is always sorted.
            int index = Collections.binarySearch(columns, column, COLUMN_COMPARATOR);
            if (index < 0) {
                index = -(index + 1);
            }
            columns.add(index, column);
            fireIntervalAdded(this, index, index);
        }
    }

    /**
     * Adds all the specified columns to this model.
     *
     * @param columns the columns to add.
     */
    public void addAll(final TableColumn[] columns) {
        int size = this.columns.size();
        for (TableColumn column : columns) {
            this.columns.add(column);
        }
        // The available columns list is always sorted
        if (manageVisible) {
            fireContentsChanged(this, size, this.columns.size() - 1);
        } else {
            Collections.sort(this.columns, COLUMN_COMPARATOR);
            fireContentsChanged(this, 0, this.columns.size() - 1);
        }

    }

    /**
     * Removes the specified column from this model.
     *
     * @param column the column to remove.
     */
    public void remove(final TableColumn column) {
        int index = columns.indexOf(column);
        if (index >= 0) {
            columns.remove(index);
            fireIntervalRemoved(this, index, index);
        }
    }

    /**
     * Removes all the columns from this model.
     */
    public void clear() {
        int size = this.columns.size();
        this.columns.clear();
        fireIntervalRemoved(this, 0, size - 1);
    }
}
