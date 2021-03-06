/*
 * XTableColumnModel.java
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

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * <code>XTableColumnModel</code> extends the DefaultTableColumnModel . It provides a
 * comfortable way to hide/show columns. Columns keep their positions when hidden and
 * shown again.
 *
 * In order to work with JTable it cannot add any events to
 * <code>TableColumnModelListener</code>. Therefore hiding a column will result in
 * <code>columnRemoved</code> event and showing it again will notify listeners of a
 * <code>columnAdded</code>, and possibly a <code>columnMoved</code> event. For the same
 * reason the following methods still deal with visible columns only: getColumnCount(),
 * getColumns(), getColumnIndex(), getColumn() There are overloaded versions of these
 * methods that take a parameter <code>onlyVisible</code> which let's you specify wether
 * you want invisible columns taken into account.
 * <p>
 * This class is based on the example of http://www.stephenkelvin.de/XTableColumnModel/
 *
 * @version 0.9 04/03/01
 * @author Stephen Kelvin, mail@StephenKelvin.de
 * @author Olivier Sechet
 * @see DefaultTableColumnModel
 */
@SuppressWarnings("serial")
public class XTableColumnModel extends DefaultTableColumnModel {

    /**
     * Array of TableColumn objects in this model. Holds all column objects, regardless of
     * their visibility
     */
    private Vector<TableColumn> allTableColumns = new Vector<TableColumn>();

    /**
     * Creates an extended table column model.
     */
    public XTableColumnModel() {
        // no op
    }

    /**
     * Sets the visibility of the specified TableColumn. The call is ignored if the
     * TableColumn is not found in this column model or its visibility status did not
     * change.
     * <p>
     *
     * @param column the column to show/hide
     * @param visible its new visibility status
     */
    // listeners will receive columnAdded()/columnRemoved() event
    public void setColumnVisible(final int modelColumnIndex, final boolean visible) {
        final TableColumn column =  getColumnByModelIndex(modelColumnIndex);

        if (visible) {
            // find the visible index of the column:
            // iterate through both collections of visible and all columns, counting
            // visible columns up to the one that's about to be shown again
            final int visibleCount = tableColumns.size();
            final int columnCount = allTableColumns.size();
            int visibleIndex = 0;

            for (int index = 0; index < columnCount; ++index) {

                final TableColumn testColumn = allTableColumns.get(index);
                final TableColumn visibleColumn = visibleIndex < visibleCount
                        ? (TableColumn) tableColumns.get(visibleIndex) : null;

                if (testColumn == column) {
                    if (visibleColumn != column) {
                        super.addColumn(column);
                        super.moveColumn(tableColumns.size() - 1, visibleIndex);
                    }
                    return; // ####################
                }
                if (testColumn == visibleColumn) {
                    ++visibleIndex;
                }
            }
        } else {
            super.removeColumn(column);
        }
    }

    /**
     * Makes all columns in this model visible.
     */
    public void setAllColumnsVisible() {
        final int columnCount = allTableColumns.size();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
            final TableColumn invisibleColumn = allTableColumns.get(columnIndex);
            if (!tableColumns.contains(invisibleColumn)) {
                super.addColumn(invisibleColumn);
                super.moveColumn(tableColumns.size() - 1, columnIndex);
            }
        }
    }

    /**
     * Maps the index of the column in the table model at <code>modelColumnIndex</code> to
     * the TableColumn object. There may me multiple TableColumn objects showing the same
     * model column, though this is uncommon. This method will always return the first
     * visible or else the first invisible column with the specified index.
     *
     * @param modelColumnIndex index of column in table model
     * @return table column object or null if no such column in this column model
     */
    public TableColumn getColumnByModelIndex(final int modelColumnIndex) {
        for (int columnIndex = 0; columnIndex < allTableColumns.size(); ++columnIndex) {
            TableColumn column = allTableColumns.elementAt(columnIndex);
            if (column.getModelIndex() == modelColumnIndex) {
                return column;
            }
        }
        return null;
    }

    /**
     * Checks whether the specified column is currently visible.
     *
     * @param aColumn column to check
     * @return visibility of specified column (false if there is no such column at all.
     *         [It's not visible, right?])
     */
    public boolean isColumnVisible(final int modelColumnIndex) {
        final TableColumn column = getColumnByModelIndex(modelColumnIndex);
        return tableColumns.indexOf(column) >= 0;
    }

    /**
     * Append <code>column</code> to the right of existing columns. Posts
     * <code>columnAdded</code> event.
     *
     * @param column The column to be added
     * @throws IllegalArgumentException if <code>column</code> is <code>null</code>
     * @see #removeColumn
     */
    @Override
    public void addColumn(final TableColumn column) throws IllegalArgumentException {
        allTableColumns.addElement(column);
        super.addColumn(column);
    }

    /**
     * Removes <code>column</code> from this column model. Posts
     * <code>columnRemoved</code> event. Will do nothing if the column is not in this
     * model.
     *
     * @param column the column to be added
     * @see #addColumn
     */
    @Override
    public void removeColumn(final TableColumn column) {
        int allColumnsIndex = allTableColumns.indexOf(column);
        if (allColumnsIndex != -1) {
            allTableColumns.removeElementAt(allColumnsIndex);
        }
        super.removeColumn(column);
    }

    /**
     * Moves the column from <code>oldIndex</code> to <code>newIndex</code>. Posts
     * <code>columnMoved</code> event. Will not move any columns if <code>oldIndex</code>
     * equals <code>newIndex</code>.
     *
     * @param oldIndex index of column to be moved
     * @param newIndex new index of the column
     * @throws IllegalArgumentException if either <code>oldIndex</code> or
     *         <code>newIndex</code> are not in [0, getColumnCount() - 1]
     */
    @Override
    public void moveColumn(final int oldIndex, final int newIndex)
            throws IllegalArgumentException {
        if ((oldIndex < 0) || (oldIndex >= getColumnCount()) || (newIndex < 0)
                || (newIndex >= getColumnCount())) {
            throw new IllegalArgumentException("moveColumn() - Index out of range"); //$NON-NLS-1$
        }

        TableColumn fromColumn = tableColumns.get(oldIndex);
        TableColumn toColumn = tableColumns.get(newIndex);

        int allColumnsOldIndex = allTableColumns.indexOf(fromColumn);
        int allColumnsNewIndex = allTableColumns.indexOf(toColumn);

        if (oldIndex != newIndex) {
            allTableColumns.removeElementAt(allColumnsOldIndex);
            allTableColumns.insertElementAt(fromColumn, allColumnsNewIndex);
        }

        super.moveColumn(oldIndex, newIndex);
    }

    /**
     * Returns the total number of columns in this model.
     *
     * @param onlyVisible if set only visible columns will be counted
     * @return the number of columns in the <code>tableColumns</code> array
     * @see #getColumnCount
     */
    public int getColumnCount(final boolean onlyVisible) {
        return (onlyVisible ? tableColumns.size() : allTableColumns.size());
    }

    /**
     * Returns the number of visible columns in this model.
     *
     * @return the number of visible columns in this model.
     * @see #getColumnCount
     * @see #getHiddenColumnCount
     */
    public int getVisibleColumnCount() {
        return getColumnCount(true);
    }

    /**
     * Returns the number of hidden columns in this model.
     *
     * @return the number of hidden columns in this model.
     * @see #getColumnCount
     * @see #getVisibleColumnCount
     */
    public int getHiddenColumnCount() {
        return allTableColumns.size() - tableColumns.size();
    }

    /**
     * Returns an <code>Enumeration</code> of all the columns in the model.
     *
     * @param onlyVisible if set all invisible columns will be missing from the
     *        enumeration.
     * @return an <code>Enumeration</code> of the columns in the model
     */
    public Enumeration<TableColumn> getColumns(final boolean onlyVisible) {
        Vector<TableColumn> columns = (onlyVisible ? tableColumns : allTableColumns);

        return columns.elements();
    }

    /**
     * Returns an <code>Enumeration</code> of all the hidden columns in the model.
     *
     * @return an <code>Enumeration</code> of the hidden columns in the model
     */
    public Enumeration<TableColumn> getHiddenColumns() {
        Vector<TableColumn> columns = new Vector<TableColumn>();
        columns.addAll(allTableColumns);
        columns.removeAll(tableColumns);

        return columns.elements();
    }

    /**
     * Returns the position of the first column whose identifier equals
     * <code>identifier</code>. Position is the the index in all visible columns if
     * <code>onlyVisible</code> is true or else the index in all columns.
     *
     * @param identifier the identifier object to search for
     * @param onlyVisible if set searches only visible columns
     *
     * @return the index of the first column whose identifier equals
     *         <code>identifier</code>
     *
     * @throws IllegalArgumentException if <code>identifier</code> is <code>null</code>,
     *         or if no <code>TableColumn</code> has this <code>identifier</code>
     * @see #getColumn
     */
    public int getColumnIndex(final Object identifier, final boolean onlyVisible)
            throws IllegalArgumentException {
        if (identifier == null) {
            throw new IllegalArgumentException("Identifier is null"); //$NON-NLS-1$
        }

        Vector<TableColumn> columns = (onlyVisible ? tableColumns : allTableColumns);
        int noColumns = columns.size();
        TableColumn column;

        for (int columnIndex = 0; columnIndex < noColumns; ++columnIndex) {
            column = columns.get(columnIndex);

            if (identifier.equals(column.getIdentifier())) {
                return columnIndex;
            }
        }

        throw new IllegalArgumentException("Identifier not found"); //$NON-NLS-1$
    }

    /**
     * Returns the <code>TableColumn</code> object for the column at
     * <code>columnIndex</code>.
     *
     * @param columnIndex the index of the column desired
     * @param onlyVisible if set columnIndex is meant to be relative to all visible
     *        columns only else it is the index in all columns
     *
     * @return the <code>TableColumn</code> object for the column at
     *         <code>columnIndex</code>
     */
    public TableColumn getColumn(final int columnIndex, final boolean onlyVisible) {
        if (onlyVisible) {
            return tableColumns.elementAt(columnIndex);
        } else {
            return allTableColumns.elementAt(columnIndex);
        }
    }

}
