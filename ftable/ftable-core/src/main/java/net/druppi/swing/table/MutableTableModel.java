/*
 * MutableTableModel.java
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
package net.druppi.swing.table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A MutableTableModel can be attached to JTable in order to always have an empty line at
 * the end that can be used to add new data.
 *
 * @author Olivier Sechet
 * @version 1.0 - Mar 17, 2009
 */
public class MutableTableModel implements TableModel {

    /** The associated model. */
    private EditableTableModel model;

    /**
     * Creates a new MutableTableModel.
     *
     * @param model the encapsulated model.
     */
    public MutableTableModel(final EditableTableModel model) {
        this.model = model;
    }

    /**
     * Returns the encapsulated model.
     *
     * @return the encapsulated model.
     */
    public final EditableTableModel getModel() {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<?> getColumnClass(final int columnIndex) {
        return model.getColumnClass(columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getColumnCount() {
        return model.getColumnCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getColumnName(final int columnIndex) {
        return model.getColumnName(columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getRowCount() {
        return model.getRowCount() + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object getValueAt(final int rowIndex, final int columnIndex) {
        if (rowIndex == model.getRowCount()) {
            return null;
        }
        return model.getValueAt(rowIndex, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return model.isCellEditable(rowIndex, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
        if (rowIndex == model.getRowCount()) {
            model.insertRows(rowIndex, 1);
        }
        model.setValueAt(value, rowIndex, columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addTableModelListener(final TableModelListener l) {
        model.addTableModelListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeTableModelListener(final TableModelListener l) {
        model.removeTableModelListener(l);
    }

}
